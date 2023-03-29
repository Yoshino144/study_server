package top.pcat.study.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import top.pcat.study.dao.ChapterDao;
import top.pcat.study.dao.SubjectDao;
import top.pcat.study.dao.UserChooseDao;
import top.pcat.study.pojo.Subject;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
@Transactional
@Slf4j
@Service
public class SubjectService  {

    public IPage<Subject> selectPageText(Page<Subject> page) {
        return subjectMapper.selectpage(page);
    }

    @Autowired
    SubjectDao subjectMapper;

    @Autowired
    UserChooseDao userChooseDao;


    @Autowired
    ChapterDao chapterDao;

//    public String upYixuan(Integer id,String all){
//        UserChoose userChoose = new UserChoose();
////        userChoose.setSize();
////        this.userChooseMapper.insert()
//    }

    public List<Subject> getSubject(int official,String userId) {
        return this.subjectMapper.selectByExample(official,userId);
    }

    public List<Subject> getYixuan(String userId) {
        return this.userChooseDao.selectByExample(userId);
    }

    public int upUserChoose(String subjectId, String userId) {
        Date date = new Date();

        Timestamp timestamp = new Timestamp(date.getTime());

        return this.userChooseDao.upUserChoose(subjectId,userId,timestamp);
    }

    public int delUserChoose(String subjectId, String userId) {
        Date date = new Date();

        Timestamp timestamp = new Timestamp(date.getTime());
        return this.userChooseDao.delUserChoose(subjectId,userId,timestamp);
    }

    public String getAllSubject() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        return gson.toJson(subjectMapper.getAllSubject());
    }

    public void addSubject(String subjectDesc,String subjectName,String subjectPrivate) {
        Subject subject = new Subject();
        subject.setSubjectDesc(subjectDesc);
        subject.setSubjectName(subjectName);
        subject.setSubjectTime(new Date());
        subject.setSubjectPrivate(subjectPrivate);
        this.subjectMapper.addSubject(subject);
    }

//    @Override
//    public String getSize(String userId) {
//
//        Integer yixSize = this.userChooseMapper.yixSize(userId);
//        System.out.println("已选题数："+yixSize);
//        List<Integer> yixSub = this.userChooseMapper.yixSubject(userId);
//        int all = 0;
//        for(Integer yi:yixSub){
//            Integer allSize = this.userChooseMapper.allSize(yi);
//            if(allSize!=null){
//                all += allSize;
//            }
//        }
//
//        return String.valueOf(all)+"&"+String.valueOf(yixSize);
//
//    }



}
