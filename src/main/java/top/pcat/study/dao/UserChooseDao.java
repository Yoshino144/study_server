package top.pcat.study.dao;


import org.apache.ibatis.annotations.*;
import top.pcat.study.pojo.Subject;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface UserChooseDao {

//    @Select("select sum(chapter_size) from chapter where id_subject = #{yi}")
//    Integer allSize(Integer yi);
//
//    @Select("SELECT sum(size) FROM choose_subject where id_user = #{userId}")
//    Integer yixSize(String userId);
//
//    @Select("SELECT subject_id FROM choose_subject where id_user = #{userId}")
//    List<Integer> yixSubject(String userId);

    @Select("SELECT\n" +
            "\tt_subject.* ,( SELECT t_user_info.NAME FROM t_user_info WHERE t_user_info.id = t_subject.subject_founder ) founder_name \n" +
            "FROM\n" +
            "\tt_subject_choose\n" +
            "\tINNER JOIN t_subject ON t_subject_choose.subject_id = t_subject.subject_id \n" +
            "WHERE\n" +
            "\tt_subject_choose.user_id = #{userId}")
    List<Subject> selectByExample(String userId);

    @Insert("INSERT INTO t_subject_choose(user_id,subject_id,choose_time) VALUES( #{userId},#{subjectId},#{timestamp})")
    int upUserChoose(@Param("subjectId") String subjectId, @Param("userId") String userId, @Param("timestamp") Timestamp timestamp);

    @Delete("DELETE FROM t_subject_choose WHERE subject_id = #{subjectId} AND user_id = #{userId}")
    int delUserChoose(@Param("subjectId") String subjectId, @Param("userId") String userId, @Param("timestamp") Timestamp timestamp);
}