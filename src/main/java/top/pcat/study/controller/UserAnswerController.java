package top.pcat.study.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.pcat.study.pojo.*;
import top.pcat.study.service.UserAnswerService;

import java.util.List;

/**
 * @program: Study_Server
 * @description:
 * @author: PCat
 * @create: 2022-02-16 20:56
 **/

@Slf4j
@RestController
@RequestMapping("/userAnswers")
public class UserAnswerController {

    @Autowired
    UserAnswerService userAnswerService;

    /**
     * 获取错题列表
     * @param userId
     * @param trueFlag
     * @return
     */

    @GetMapping("/{userId}/{trueFlag}")
    public List<UserAnswerData> getWrongProblem(@PathVariable String userId, @PathVariable Integer trueFlag) {
        return this.userAnswerService.getWrongProblem(userId,trueFlag);
    }

    @GetMapping("/{userId}")
    public List<UserAnswerData> getWrongProblem(@PathVariable String userId) {
        return this.userAnswerService.getAnswerProblem(userId);
    }

    /**
     * 上传做题记录
     * @param userId
     * @return
     */
    @PostMapping("/{userId}")
    public int upProblemData(@PathVariable String userId,
                             @RequestParam("subject_id") int subject_id,
                             @RequestParam("chapter_id") int chapter_id,
                             @RequestParam("problem_id") int problem_id,
                             @RequestParam("answer") String answer,
                             @RequestParam("true_flag") String true_flag) {
        return this.userAnswerService.upProblemData(userId, subject_id, chapter_id, problem_id, answer, Integer.parseInt(true_flag));
    }

    @GetMapping("/subject/{userId}")
    public List<Wrong> getWrongSbuject(@PathVariable String userId){
        return userAnswerService.getWrongSbuject(userId);
    }

    @GetMapping("/subject/{subjectId}/{userId}")
    public List<WrongChapter> getWrongChapter(@PathVariable String subjectId, @PathVariable String userId){
        return userAnswerService.getWrongChapter(userId,subjectId);
    }

    @GetMapping("/subject/{subjectId}/{chapterId}/{userId}")
    public List<WrongProblem> getWrongProblem(@PathVariable String subjectId,@PathVariable String chapterId, @PathVariable String userId){
        return userAnswerService.getWrongPm(userId,chapterId,subjectId);
    }

    @GetMapping("/subject/random/{userId}")
    public List<WrongProblem> getRandomProblem(@PathVariable String userId){
        return userAnswerService.getRandomProblem(userId);
    }

}
