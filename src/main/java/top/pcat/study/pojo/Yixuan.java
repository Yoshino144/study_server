package top.pcat.study.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Yixuan {
    private String subjectId;
    private String subjectName;
    private Date chooseTime;
    private String subjectFounder;

    private Date subjectTime;

    private long subjectPrivate;
    private long subjectSize;
    private long subjectOfficial;
    private long subjectAdmin;
}
