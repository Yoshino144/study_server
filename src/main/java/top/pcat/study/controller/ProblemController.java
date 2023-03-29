package top.pcat.study.controller;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.pcat.study.pojo.Problem;
import top.pcat.study.service.ProblemService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/problems")
public class ProblemController {

    @Autowired
    ProblemService problemService;

    @GetMapping("/{subjectId}/{chapterId}")
    public List<Problem> getProblem(@PathVariable String subjectId, @PathVariable  String chapterId) {
        return this.problemService.getProblem(subjectId,chapterId);
    }

    @GetMapping("/page/{subjectId}/{chapterId}")
    public String getPageProblem(@PathVariable String subjectId, @PathVariable  String chapterId) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        return "{\n" +
                "\t\"data\": "+gson.toJson(this.problemService.getProblem(subjectId,chapterId))+",\n" +
                "\t\"total\": 100,\n" +
                "\t\"success\": true,\n" +
                "\t\"pageSize\": 20,\n" +
                "\t\"current\": 1\n" +
                "}";
    }

}

