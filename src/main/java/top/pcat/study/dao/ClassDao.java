package top.pcat.study.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.pcat.study.pojo.Clasp;

import java.util.List;

@Mapper
public interface ClassDao {

    @Insert("INSERT INTO t_class (t_class.class_name, t_class.class_admin_id, t_class.create_time )\n" +
            "VALUES(#{className},#{adminId},NOW())")
    int createClass(@Param("adminId") String adminId,@Param("className") String className);

    @Select("SELECT t_class.class_id, t_class.class_name, t_class.class_admin_id, t_class.create_time \n" +
            "FROM t_class INNER JOIN t_class_user ON t_class.class_id = t_class_user.class_id \n" +
            "WHERE t_class_user.user_id = #{userId}")
    List<Clasp> getClassList(@Param("userId") String userId);

    @Insert("INSERT t_class_user (class_id,user_id) " +
            "values (#{classId}, #{userId})\n")
    int joinClass(@Param("userId") String userId, @Param("classId") String classId);

    @Select("SELECT t_class.class_id, t_class.class_name, t_class.class_admin_id, t_class.create_time \n" +
            "FROM t_class \n" +
            "WHERE t_class.class_admin_id =#{userId}")
    List<Clasp> getCreateClassList(@Param("userId") String userId);

    @Select("SELECT t_class.class_id\n" +
            "FROM t_class\n" +
            "WHERE t_class.class_name = #{className}")
    int selectIdByName(@Param("className") String className);

    @Select("SELECT class_name FROM t_class WHERE class_id = #{id}")
    String selectNameById(int id);


    @Select("SELECT\n" +
            "\tt_class.class_name\n" +
            "FROM\n" +
            "\tt_class\n" +
            "WHERE\n" +
            "\tt_class.class_id = #{groupId}")
    String getGroupInfoName(String groupId);
}
