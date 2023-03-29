package top.pcat.study.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.pcat.study.pojo.Chapter;
import top.pcat.study.service.ChapterService;
import top.pcat.study.utils.Msg;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/chapters")
public class ChapterController {


    @Autowired
    ChapterService chapterService;

    @GetMapping("/{subjectId}")
    public List<Chapter> getChapterById(@PathVariable String subjectId) {
        return this.chapterService.getChapterById(subjectId);
    }

    @GetMapping("/page/{subjectId}")
    public String pageGetChapterById(@PathVariable String subjectId) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        return "{\n" +
                "\t\"data\": "+gson.toJson(this.chapterService.getChapterById(subjectId))+",\n" +
                "\t\"total\": 100,\n" +
                "\t\"success\": true,\n" +
                "\t\"pageSize\": 20,\n" +
                "\t\"current\": 1\n" +
                "}";
    }

    @PostMapping("/pageAddChapter")
    public Msg pageAddChapter(@RequestBody Map map) throws ParseException {

            if (chapterService.pageAddChapter((String)map.get("chapterName"))>0) {

                return Msg.success().mes("注册成功");
            } else {
                return Msg.fail().mes("注册失败");
            }
    }
}
