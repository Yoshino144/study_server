package top.pcat.study.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.pcat.study.dao.ChapterDao;
import top.pcat.study.pojo.Chapter;

import java.util.List;
@Transactional
@Slf4j
@Service
public class ChapterService  {

    @Autowired
    ChapterDao chapterDao;
    public List<Chapter> getChapterById(String subjectId){
        return chapterDao.getChapterById(subjectId);
    }

    public int pageAddChapter(String chapterName) {
        return chapterDao.insert(new Chapter(chapterName));
    }
}
