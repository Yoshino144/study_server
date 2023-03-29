package top.pcat.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.pcat.study.dao.ProblemDao;
import top.pcat.study.pojo.Problem;

import java.util.List;

@Transactional
@Service
public class ProblemService  {

    @Autowired
    ProblemDao problemDao;

    public List<Problem> getProblem(String subjectId, String chapterId) {
        return  this.problemDao.selectByExample(subjectId,chapterId);
    }
}
