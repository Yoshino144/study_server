package top.pcat.study.service;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.pcat.study.dao.AccountDao;
import top.pcat.study.dao.RongDao;
import top.pcat.study.dao.UserDao;
import top.pcat.study.dao.UserInfoDao;
import top.pcat.study.pojo.UserInfo;
import top.pcat.study.entity.Perms;
import top.pcat.study.entity.User;
import top.pcat.study.utils.Msg;
import top.pcat.study.utils.SaltUtils;
import top.pcat.study.utils.UUIDUtils;

import java.util.Date;
import java.util.List;

@Transactional(rollbackFor=Exception.class)
@Slf4j
@Service("userService")
public class UserService  {

    @Autowired
    private UserDao userDAO;
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private RongDao rongDao;
    @Autowired
    private AccountDao accountDao;

    public User getAllByPhone(String phone){
        return userDAO.getAllByPhone(phone);
    }

    public String getIdByPhone(String phone){
        return userDAO.getIdByPhone(phone);
    }

    public int register(String phone, String password, String name) {
        String salt = SaltUtils.getSalt(8);
        Md5Hash md5Hash = new Md5Hash(password,salt,1024);
        String uuid= UUIDUtils.getUUID();
        int flag= 0;
        //插入账号
        flag += userDAO.insert(new User(uuid,md5Hash.toString(),salt,null));
        //插入用户数据
        flag += userInfoDao.insert(new UserInfo(uuid,phone,name));
        try {
            String rongToken = rongDao.register(uuid,name);
            int res = accountDao.insert(uuid, rongToken, "rong");
            if (res >0){
                return flag;
            }
            else{
                throw new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public User login(String username, String password){
        return userDAO.login(username, password);
    }

    public List<Perms> findPermsByRoleId(String id) {
        return userDAO.findPermsByRoleId(id);
    }

    public User findRolesById(String id) {
        return userDAO.findRolesById(id);
    }

    public User findByUserName(String username) {
        return userDAO.findByUserName(username);
    }

    public String getPassword(String phone) {
        return userDAO.getPassword(phone);
    }

    public String getRole(String username) {
        return null;
    }

    public void updatePassword(String username, String newPassword) {

    }

    public List<String> getUser() {
        return null;
    }

    public void banUser(String username) {

    }

    public int checkUserBanStatus(String username) {
        return 0;
    }

    public String getRolePermission(String username) {
        return null;
    }

    public String getPermission(String username) {
        return null;
    }

    public String getAllUsers() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        return gson.toJson(userDAO.getAllUsers());
    }

    public int register(String phone, String password, String name, Date birthday, String position) {
        String salt = SaltUtils.getSalt(8);
        Md5Hash md5Hash = new Md5Hash(password,salt,1024);
        String uuid= UUIDUtils.getUUID();
        int flag= 0;
        //插入账号
        flag += userDAO.insert(new User(uuid,md5Hash.toString(),salt,null));
        //插入用户数据
        flag += userInfoDao.insert(new UserInfo(uuid,phone,name,birthday,position));
        try {
            String rongToken = rongDao.register(uuid,name);
            int res = accountDao.insert(uuid, rongToken, "rong");
            if (res >0){
                return flag;
            }
            else{
                throw new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public int pageDeleteUser(UserInfo userInfo) {
        userInfo.setDelFlag(1);
        return userInfoDao.updateById(userInfo);
    }

    public int pageModifyUser(UserInfo userInfo) {
        return userInfoDao.updateById(userInfo);
    }
}
