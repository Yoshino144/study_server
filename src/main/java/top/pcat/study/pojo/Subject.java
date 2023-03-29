package top.pcat.study.pojo;


import lombok.Data;

import java.util.Date;

@Data
public class Subject {

  private long subjectId;
  private String subjectName;
  private String subjectFounder;

  private Date subjectTime;
  private String subjectPrivate;
  private String subjectDelete;
  private String subjectPic;
  private String subjectDesc;
  private long subjectSize;
  private Integer subjectOfficial;
  private String subjectAdmin;

  private String founderName;
  private boolean chooseFlag;


}
