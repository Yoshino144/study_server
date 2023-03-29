package top.pcat.study.pojo;


import lombok.Data;

@Data
public class TSubjectChoose {

  private long id;
  private String userId;
  private long subjectId;
  private java.sql.Timestamp chooseTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }


  public long getSubjectId() {
    return subjectId;
  }

  public void setSubjectId(long subjectId) {
    this.subjectId = subjectId;
  }


  public java.sql.Timestamp getChooseTime() {
    return chooseTime;
  }

  public void setChooseTime(java.sql.Timestamp chooseTime) {
    this.chooseTime = chooseTime;
  }

}
