package top.pcat.study.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.pcat.study.pojo.Subject;

import java.util.List;
import java.util.Map;

@Mapper
public interface SubjectDao {

    @Select("SELECT * FROM t_subject ORDER BY `subject_id` ASC")
    IPage<Subject> selectpage(Page<Subject> page);

    @Select("SELECT t_subject.*,( SELECT t_user_info.NAME FROM t_user_info WHERE t_user_info.id = t_subject.subject_founder ) founder_name,\n" +
            "CASE\n" +
            "\t\tWHEN ( SELECT count(*) FROM t_subject_choose WHERE subject_id = t_subject.subject_id AND user_id = #{userId} )> 0 THEN\n" +
            "\tTRUE ELSE FALSE \n" +
            "\tEND AS choose_flag \n" +
            "FROM\n" +
            "\tt_subject \n" +
            "WHERE\n" +
            "\tsubject_official = #{official}")
    List<Subject> selectByExample(@Param("official") int official,@Param("userId") String userId);

    @Select("SELECT * FROM t_subject")
    List<Subject> getAllSubject();

    @Insert("INSERT INTO t_subject(subject_name, subject_time, subject_desc, subject_private) " +
            "VALUES(#{subjectName},#{subjectTime},#{subjectDesc},#{subjectPrivate})")
    void addSubject(Subject subject);
}