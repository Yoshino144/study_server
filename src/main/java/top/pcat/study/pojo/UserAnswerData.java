package top.pcat.study.pojo;

import lombok.Data;

@Data
public class UserAnswerData {

  private long id;
  private String userId;
  private long subjectId;
  private String subjectName;
  private long chapterId;
  private String chapterName;
  private long problemId;
  private String answer;
  private Integer trueFlag;



}
