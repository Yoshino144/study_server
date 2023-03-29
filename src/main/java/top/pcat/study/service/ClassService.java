package top.pcat.study.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.pcat.study.dao.ClassDao;
import top.pcat.study.dao.RongDao;
import top.pcat.study.pojo.Clasp;

import java.util.List;

@Transactional
@Service
@Slf4j
public class ClassService {

    @Autowired
    ClassDao classDao;

    @Autowired
    RongDao rongDao;


    public int createClass(String adminId,String className) {
        try{
            classDao.createClass(adminId, className);
            int id= classDao.selectIdByName(className);
            rongDao.createGroup(adminId,String.valueOf(id),className);
            classDao.joinClass(adminId, String.valueOf(id));
            return id;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }

    }

    public List<Clasp> getClassList(String userId) {
        return classDao.getClassList(userId);
    }

    public int joinClass(String userId, String classId, String className) {

        try {
            if (className == null || "".equals(className)){
                className = classDao.selectNameById(Integer.parseInt(classId));
            }
            rongDao.joinClass(userId,classId,className);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classDao.joinClass(userId,classId);
    }

    public List<Clasp> getCreateClassList(String userId) {

        return classDao.getCreateClassList(userId);
    }

    public String getGroupInfoName(String groupId) {
        return classDao.getGroupInfoName(groupId);
    }
}
