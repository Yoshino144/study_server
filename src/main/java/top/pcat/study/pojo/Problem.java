package top.pcat.study.pojo;

import lombok.Data;

@Data
public class Problem {

  private long problemId;
  private String problemContent;
  private String problemType;
  private String problemAnswer;
  private String A;
  private String B;
  private String C;
  private String D;
  private String problemAnalysis;
  private String problemFounder;
  private java.sql.Timestamp problemTime;
  private String problemDelete;
  private long chapterId;
  private long subjectId;


  public long getProblemId() {
    return problemId;
  }

  public void setProblemId(long problemId) {
    this.problemId = problemId;
  }


  public String getProblemContent() {
    return problemContent;
  }

  public void setProblemContent(String problemContent) {
    this.problemContent = problemContent;
  }


  public String getProblemType() {
    return problemType;
  }

  public void setProblemType(String problemType) {
    this.problemType = problemType;
  }


  public String getProblemAnswer() {
    return problemAnswer;
  }

  public void setProblemAnswer(String problemAnswer) {
    this.problemAnswer = problemAnswer;
  }


  public String getA() {
    return A;
  }

  public void setA(String A) {
    this.A = A;
  }


  public String getB() {
    return B;
  }

  public void setB(String B) {
    this.B = B;
  }


  public String getC() {
    return C;
  }

  public void setC(String C) {
    this.C = C;
  }


  public String getD() {
    return D;
  }

  public void setD(String D) {
    this.D = D;
  }


  public String getProblemAnalysis() {
    return problemAnalysis;
  }

  public void setProblemAnalysis(String problemAnalysis) {
    this.problemAnalysis = problemAnalysis;
  }


  public String getProblemFounder() {
    return problemFounder;
  }

  public void setProblemFounder(String problemFounder) {
    this.problemFounder = problemFounder;
  }


  public java.sql.Timestamp getProblemTime() {
    return problemTime;
  }

  public void setProblemTime(java.sql.Timestamp problemTime) {
    this.problemTime = problemTime;
  }


  public String getProblemDelete() {
    return problemDelete;
  }

  public void setProblemDelete(String problemDelete) {
    this.problemDelete = problemDelete;
  }


  public long getChapterId() {
    return chapterId;
  }

  public void setChapterId(long chapterId) {
    this.chapterId = chapterId;
  }


  public long getSubjectId() {
    return subjectId;
  }

  public void setSubjectId(long subjectId) {
    this.subjectId = subjectId;
  }

}
