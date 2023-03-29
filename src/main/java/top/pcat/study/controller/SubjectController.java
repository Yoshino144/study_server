package top.pcat.study.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.pcat.study.pojo.Subject;
import top.pcat.study.service.SubjectService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/subjects")
public class SubjectController {


    @Autowired
    SubjectService subjectService;

    @PostMapping("/add")
    public void addSubject(@RequestParam("subjectDesc") String subjectDesc,
                           @RequestParam("subjectName") String subjectName,
                           @RequestParam("subjectPrivate") String subjectPrivate){
        subjectService.addSubject(subjectDesc, subjectName, subjectPrivate);
    }

    @GetMapping("/all")
    public String getAllSubject() {
        return "{\n" +
                "\t\"data\": {\n" +
                "\t\t\"list\": " +
                this.subjectService.getAllSubject() +
                "\t}\n" +
                "}";
    }

    @GetMapping("/{userId}/un_official")
    public List<Subject> getUnOfficialSubject(@PathVariable String userId) {
        return this.subjectService.getSubject(0,userId);
    }

    @GetMapping("/{userId}/official")
    public List<Subject> getSubject(@PathVariable String userId) {
        return this.subjectService.getSubject(1,userId);
    }


    @GetMapping("/{userId}")
    public List<Subject> getYixuan(@PathVariable String userId) {
        return this.subjectService.getYixuan(userId);
    }

    @PostMapping("/{subjectId}/users/{userId}")
    public int upUserChoose(@PathVariable String subjectId,@PathVariable String userId){

        return this.subjectService.upUserChoose(subjectId,userId);
    }

    @DeleteMapping("/{subjectId}/users/{userId}")
    public int delUserChoose(@PathVariable String subjectId,@PathVariable String userId){

        return this.subjectService.delUserChoose(subjectId,userId);
    }

    @GetMapping("?page={current}&limit={size}")
    public List<Subject> textPage(@PathVariable Integer current,@PathVariable Integer size) {
        Page<Subject> userPage = new Page<>(current, size);
        IPage<Subject> iPage = subjectService.selectPageText(userPage);
        if (iPage.getRecords().size() != 0) {
            List<Subject> mpUserList1 = iPage.getRecords();
            mpUserList1.forEach(System.out::println);
            return mpUserList1;
        } else {
            return null;
        }
    }
}
