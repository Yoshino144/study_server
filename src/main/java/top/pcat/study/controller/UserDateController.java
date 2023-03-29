package top.pcat.study.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.pcat.study.service.UserDateService;


@Slf4j
@RestController
@RequestMapping("/userdates")
public class UserDateController {

    @Autowired
    UserDateService userDayService;

    @GetMapping("/three/{userId}")
    public String getSize(@PathVariable String userId) {
        return this.userDayService.getSize(userId);
    }

    /**
     * 获取今日数据
     * @param userId
     * @return
     */
    @GetMapping("/day/{userId}")
    public String getTodaySize(@PathVariable String userId) {
        try {
            Integer res = Math.toIntExact(this.userDayService.getTodaySize(userId));
            System.out.print("res===");
            System.out.print(res);
            return String.valueOf(res);
        } catch (Exception e) {
            System.out.println(e);
        }
        return "";
    }

    /**
     * 获取周数据
     * @param userId
     * @return
     */
    @GetMapping("/week/{userId}")
    public String getWeekSize(@PathVariable String userId) {
        try {
            String res = this.userDayService.getWeekSize(userId);
            return res;
        } catch (Exception e) {
            System.out.println(e);
        }
        return "";
    }
}
