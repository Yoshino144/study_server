package top.pcat.study.pojo;

import lombok.Data;

/**
 * @program: study
 * @description:
 * @author: PCat
 * @create: 2022-02-14 23:23
 **/
@Data
public class LoginReq {
    private String token;
    private String uuid;

    public LoginReq(String uuid, String token) {
        this.token = token;
        this.uuid = uuid;
    }
}
