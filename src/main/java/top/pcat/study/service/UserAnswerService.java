package top.pcat.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.pcat.study.dao.UserAnswerDao;
import top.pcat.study.dao.UserDateDao;
import top.pcat.study.pojo.*;

import java.util.List;

/**
 * @program: Study_Server
 * @description:
 * @author: PCat
 * @create: 2022-02-16 21:23
 **/
@Transactional
@Service
public class UserAnswerService {
    @Autowired
    UserAnswerDao userAnswerDao;

    @Autowired
    UserDateDao userDateDao;

    public List<UserAnswerData> getWrongProblem(String userId, Integer trueFlag){
        return userAnswerDao.getWrongProblem(userId, trueFlag);
    }

    public List<UserAnswerData> getAnswerProblem(String userId){
        return userAnswerDao.getAnswerProblem(userId);
    }

    /**
     * 上传做题记录
     * @return
     */
    public int upProblemData(String user_id,int subject_id, int chapter_id,int problem_id,String answer,int true_flag) {
        userDateDao.updatesize(user_id);
        return userAnswerDao.upProblemData(user_id, subject_id, chapter_id, problem_id, answer, true_flag);
    }

    public List<Wrong> getWrongSbuject(String userId) {
        return userAnswerDao.getWrongSbuject(userId);
    }

    public List<WrongChapter> getWrongChapter(String userId, String subjectId) {
        return userAnswerDao.getWrongChapter(userId,subjectId);
    }

    public List<WrongProblem> getWrongPm(String userId, String chapterId, String subjectId) {
        return userAnswerDao.getWrongPm(userId,chapterId,subjectId);
    }

    public List<WrongProblem> getRandomProblem(String userId) {
        return userAnswerDao.getRandomProblem(userId);
    }
}
