package top.pcat.study.dao;

import org.apache.ibatis.annotations.*;
import top.pcat.study.pojo.UserDateSize;

import java.util.List;

@Mapper
public interface UserDateDao {

    @Select("SELECT SUM(chapter_size) as yiXuanAllSize from t_subject_choose as cs , t_chapter as cp where cs.subject_id = cp.subject_id and cs.user_id = #{userId}")
    String getYiXuanAllSize(String userId);

    @Select("SELECT COUNT(*) as yiXuanDoSize from t_user_problem_data where user_id = #{userId}")
    String getYiXuanDoSize(String userId);

    @Select("select size from t_user_daily_data where date=  curdate() and user_id = #{userId}")
    String getTodaySizeSingle(String userId);

    @Select("select * from t_user_daily_data where user_id = #{userId} and date = #{date}")
    List<UserDateSize> getTodaySize(@Param("userId")String userId,@Param("date")String date);

    @Select("select date , size from t_user_daily_data where user_id = #{userId} ORDER BY date DESC")
    List<UserDateSize> getWeekSize(String userId);

    @Insert("insert into t_user_daily_data (user_id,date,size) values(#{userId},now(),1) on DUPLICATE key update size=size+1;\n")
    int updatesize(String userId);

//jjj
}