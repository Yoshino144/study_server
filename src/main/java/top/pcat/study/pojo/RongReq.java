package top.pcat.study.pojo;

import lombok.Data;

@Data
public class RongReq {
    private String token;
    private String userId;
    private String reqBody;
    private int code;
    private String requestId;
}
