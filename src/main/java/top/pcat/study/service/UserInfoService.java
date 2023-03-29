package top.pcat.study.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.pcat.study.dao.UserInfoDao;
import top.pcat.study.pojo.UserInfo;

@Transactional
@Slf4j
@Service
public class UserInfoService  {

    @Autowired
    private UserInfoDao userDao;

    public UserInfo getUserInfo(String userId){
        return userDao.selectById(userId);
    }

    public int update(UserInfo userInfo){
        return userDao.updateById(userInfo);
    }

    public String getUserInfoName(String userId) {
        return userDao.getUserInfoName(userId);
    }
}
