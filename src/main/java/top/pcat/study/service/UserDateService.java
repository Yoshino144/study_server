package top.pcat.study.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.pcat.study.dao.UserDateDao;
import top.pcat.study.dao.UserProblemDataDao;
import top.pcat.study.pojo.UserDateSize;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Transactional
@Service
public class UserDateService  {
    static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @Autowired
    UserDateDao userDayMapper;

    @Autowired
    UserProblemDataDao userProblemDataDao;

    public String getSize(String userId){
        String yiXuanAllSize= userDayMapper.getYiXuanAllSize(userId);
        if(yiXuanAllSize == null) yiXuanAllSize = "0";
        String yiXuanDoSize= userDayMapper.getYiXuanDoSize(userId);
        if(yiXuanDoSize == null) yiXuanDoSize = "0";
        String todaySizeSingle= userDayMapper.getTodaySizeSingle(userId);
        if(todaySizeSingle == null) todaySizeSingle = "0";

        String a = userProblemDataDao.getConunt(userId);

        return  "{\"yiXuanAllSize\":"+yiXuanAllSize + ","+
                "\"yiXuanDoSize\":"+yiXuanDoSize + ","+
                "\"todaySize\":"+todaySizeSingle + "}";

//        return  "{\"yiXuanAllSize\":"+yiXuanDoSize + ","+
//                "\"yiXuanDoSize\":"+yiXuanDoSize + ","+
//                "\"todaySize\":"+todaySizeSingle + "}";
    }



    public long getTodaySize(String userId) {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        logger.info("data:" + date.format(formatter) + "  userId:" + userId);

        if (userId != null) {
            List<UserDateSize> todaySize = this.userDayMapper.getTodaySize(userId, date.toString());

            if (todaySize.size() > 0) {
                System.out.print("service===");
                System.out.println(todaySize.get(0).getSize());
                return todaySize.get(0).getSize();
            } else {
                return 0;
            }
        } else {
            return 9999;
        }
    }

    public String getWeekSize(String userId) {

        LocalDate[] date = new LocalDate[31];
        for (int i = 0; i < 31; i++) {
            LocalDate dateTemp = LocalDate.now().plusDays(-i);
            date[i] = dateTemp;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (int i = 0; i < 31; i++) {
            date[i].format(formatter);
        }
        List<UserDateSize> weekSize = this.userDayMapper.getWeekSize(userId);

        logger.info(weekSize.get(0).getSize());

        StringBuilder res = new StringBuilder("{");
        int flag = 0;
        for(int i = 0 ; i < weekSize.size() ;i++){
            Date data = weekSize.get(i).getDate();
            //LocalDate localDate =  data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Calendar cal = Calendar.getInstance();
            cal.setTime(data);
            LocalDate localDate = LocalDate.of(cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH) + 1,
                    cal.get(Calendar.DAY_OF_MONTH));

            localDate.format(formatter);
            if(flag >=31) break;
            if(localDate.toString().equals(date[flag].toString())){
                res.append("\"").append(date[flag].toString()).append("\":\"").append(weekSize.get(i).getSize()).append("\",");
                flag++;
            }else{
                res.append("\"").append(date[flag].toString()).append("\":\"").append(0).append("\",");
                flag++;
                i--;
            }
        }
        if(flag !=31){
            for(;flag < 30;flag++){
                res.append("\"").append(date[flag].toString()).append("\":\"").append(0).append("\",");
            }
            res.append("\"").append(date[30].toString()).append("\":\"").append(0).append("\"}");
        }else{
            res.deleteCharAt(res.length() - 1);
            res.append("}");
        }

        System.out.println(res);
return res.toString();
    }


}
