package top.pcat.study.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.pcat.study.pojo.*;

import java.util.List;

/**
 * @program: Study_Server
 * @description:
 * @author: PCat
 * @create: 2022-02-16 21:23
 **/

@Mapper
public interface UserAnswerDao {

    @Select("SELECT\n" +
            "\tt_user_problem_data.*,\n" +
            "\tt_chapter.chapter_name,\n" +
            "\tt_subject.subject_name \n" +
            "FROM\n" +
            "\tt_user_problem_data\n" +
            "\tINNER JOIN t_chapter ON t_user_problem_data.chapter_id = t_chapter.chapter_id\n" +
            "\tINNER JOIN t_subject ON t_user_problem_data.subject_id = t_subject.subject_id \n" +
            "WHERE\n" +
            "\tt_user_problem_data.true_flag =#{trueFlag} \n" +
            "\tAND t_user_problem_data.user_id =#{userId}")
    List<UserAnswerData> getWrongProblem(@Param("userId") String userId,@Param("trueFlag") Integer trueFlag);

    @Select("SELECT\n" +
            "\tt_user_problem_data.*,\n" +
            "\tt_chapter.chapter_name,\n" +
            "\tt_subject.subject_name \n" +
            "FROM\n" +
            "\tt_user_problem_data\n" +
            "\tINNER JOIN t_chapter ON t_user_problem_data.chapter_id = t_chapter.chapter_id\n" +
            "\tINNER JOIN t_subject ON t_user_problem_data.subject_id = t_subject.subject_id \n" +
            "WHERE\n" +
            "\tt_user_problem_data.user_id =#{userId}")
    List<UserAnswerData> getAnswerProblem(@Param("userId") String userId);

    @Insert("REPLACE INTO t_user_problem_data (user_id, subject_id, chapter_id, problem_id, answer, true_flag)\n" +
            "VALUES (#{user_id},#{subject_id},#{chapter_id},#{problem_id},#{answer},#{true_flag})")
    int upProblemData(@Param("user_id") String user_id,@Param("subject_id") int subject_id,@Param("chapter_id")  int chapter_id,
                      @Param("problem_id") int problem_id,@Param("answer") String answer,@Param("true_flag") int true_flag);

    @Select("SELECT\n" +
            "\tt_user_problem_data.subject_id as subject_id,\n" +
            "\tt_subject.subject_name as subject_name,\n" +
            "\tCOUNT( t_user_problem_data.problem_id ) AS problemSize \n" +
            "FROM\n" +
            "\tt_user_problem_data\n" +
            "\tINNER JOIN t_subject ON t_user_problem_data.subject_id = t_subject.subject_id \n" +
            "WHERE\n" +
            "\tt_user_problem_data.true_flag = 0 AND t_user_problem_data.user_id = #{userId}\n" +
            "GROUP BY\n" +
            "\tt_user_problem_data.subject_id")
    List<Wrong> getWrongSbuject(String userId);

    @Select("SELECT\n" +
            "t_user_problem_data.subject_id,\n" +
            "\tt_user_problem_data.chapter_id,\n" +
            "\tt_chapter.chapter_name,\n" +
            "\tCOUNT( t_user_problem_data.problem_id ) AS problem_size \n" +
            "FROM\n" +
            "\tt_user_problem_data\n" +
            "\tINNER JOIN t_chapter ON t_user_problem_data.chapter_id = t_chapter.chapter_id \n" +
            "WHERE\n" +
            "\tt_user_problem_data.true_flag = 0 \n" +
            "\tAND t_user_problem_data.subject_id = #{subjectId} \n" +
            "\tAND t_user_problem_data.user_id = #{userId}\n" +
            "GROUP BY\n" +
            "\tt_user_problem_data.chapter_id\n" +
            "ORDER BY t_user_problem_data.chapter_id ")
    List<WrongChapter> getWrongChapter(@Param("userId") String userId,@Param("subjectId") String subjectId);

    @Select("SELECT\n" +
            "\tt_user_problem_data.subject_id,\n" +
            "\tt_user_problem_data.chapter_id,\n" +
            "\tt_user_problem_data.problem_id,\n" +
            "\tt_problem.problem_content,\n" +
            "\tt_problem.problem_type,\n" +
            "\tt_problem.problem_answer,\n" +
            "\tt_problem.A,\n" +
            "\tt_problem.B,\n" +
            "\tt_problem.C,\n" +
            "\tt_problem.D,\n" +
            "\tt_problem.problem_analysis,\n" +
            "\tt_subject.subject_name,\n" +
            "\tt_chapter.chapter_name \n" +
            "FROM\n" +
            "\tt_user_problem_data\n" +
            "\tINNER JOIN t_subject ON t_user_problem_data.subject_id = t_subject.subject_id\n" +
            "\tINNER JOIN t_chapter ON t_user_problem_data.chapter_id = t_chapter.chapter_id\n" +
            "\tINNER JOIN t_problem ON t_user_problem_data.problem_id = t_problem.problem_id \n" +
            "WHERE\n" +
            "\tt_user_problem_data.subject_id = #{subjectId} \n" +
            "\tAND t_user_problem_data.chapter_id = #{chapterId}\n" +
            "\tAND t_user_problem_data.user_id = #{userId} " +
            " and t_user_problem_data.true_flag = 0")
    List<WrongProblem> getWrongPm(@Param("userId") String userId,@Param("chapterId")  String chapterId,@Param("subjectId")  String subjectId);

    @Select("SELECT\n" +
            "\tt_user_problem_data.subject_id,\n" +
            "\tt_user_problem_data.chapter_id,\n" +
            "\tt_user_problem_data.problem_id,\n" +
            "\tt_problem.problem_content,\n" +
            "\tt_problem.problem_type,\n" +
            "\tt_problem.problem_answer,\n" +
            "\tt_problem.A,\n" +
            "\tt_problem.B,\n" +
            "\tt_problem.C,\n" +
            "\tt_problem.D,\n" +
            "\tt_problem.problem_analysis,\n" +
            "\tt_subject.subject_name,\n" +
            "\tt_chapter.chapter_name \n" +
            "FROM\n" +
            "\tt_user_problem_data\n" +
            "\tINNER JOIN t_subject ON t_user_problem_data.subject_id = t_subject.subject_id\n" +
            "\tINNER JOIN t_chapter ON t_user_problem_data.chapter_id = t_chapter.chapter_id\n" +
            "\tINNER JOIN t_problem ON t_user_problem_data.problem_id = t_problem.problem_id \n" +
            "WHERE\n" +
            "\tt_user_problem_data.user_id = #{userId} and t_user_problem_data.true_flag = 0\n" +
            "LIMIT 2")
    List<WrongProblem> getRandomProblem(String userId);
}
