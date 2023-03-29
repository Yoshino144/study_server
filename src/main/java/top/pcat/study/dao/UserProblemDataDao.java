package top.pcat.study.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserProblemDataDao extends BaseMapper {

    @Select("SELECT COUNT(*)  from t_user_problem_data where user_id = #{userId}")
    String getConunt(String userId);
}
