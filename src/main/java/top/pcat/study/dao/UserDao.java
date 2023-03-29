package top.pcat.study.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.pcat.study.entity.Perms;
import top.pcat.study.entity.User;
import top.pcat.study.pojo.UserInfo;

import java.util.List;

@Mapper
public interface UserDao extends BaseMapper<User> {
@Select("SELECT ur.password " +
        "FROM t_user ur,t_user_info ui " +
        "WHERE ui.phone = #{phone} " +
        "AND ur.id = ui.id")
    String getPassword(String phone);

@Select("SELECT t_user.* " +
        "FROM t_user " +
        "INNER JOIN t_user_info " +
        "ON t_user.id = t_user_info.id " +
        "WHERE t_user_info.phone = #{phone} ")
    User getAllByPhone(String phone);

    @Select("select id from t_user_info where phone = #{phone}")
    String getIdByPhone(String phone);

    @Select("SELECT *, t_user.id, t_user.username, t_user_info.* \n" +
            "FROM t_user\n" +
            "INNER JOIN t_user_info ON t_user.id = t_user_info.id \n" +
            "WHERE t_user_info.phone = #{username}\n" +
            "AND t_user.`password` = #{password}")
    User login(String username, String password);

    @Insert("insert into t_user values(#{id},#{username},#{password},#{salt})")
    void register(String id, String password, String salt);

    @Select("select id,username,password,salt from t_user where username = #{username}")
    User findByUserName(String username);

    //根据用户名查询所有角色
    @Select("SELECT u.id uid,u.username,r.id,r.NAME rname\n" +
            "      FROM t_user u\n" +
            "      LEFT JOIN t_user_role ur\n" +
            "      ON u.id=ur.userid\n" +
            "      LEFT JOIN t_role r\n" +
            "      ON ur.roleid=r.id\n" +
            "      WHERE u.id=#{id}")
    User findRolesById(String id);

    //根据角色id查询权限集合
    @Select("SELECT p.id,p.NAME,p.url,r.NAME\n" +
            "      FROM t_role r\n" +
            "      LEFT JOIN t_role_perms rp\n" +
            "      ON r.id=rp.roleid\n" +
            "      LEFT JOIN t_perms p ON rp.permsid=p.id\n" +
            "      WHERE r.id=#{id}")
    List<Perms> findPermsByRoleId(String id);

    @Select("SELECT\n" +
            "\tt_user_info.*\n" +
            "FROM\n" +
            "\tt_user_info where del_flag = 0")
    List<UserInfo> getAllUsers();
}

