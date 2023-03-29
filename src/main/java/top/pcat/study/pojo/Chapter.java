package top.pcat.study.pojo;

public class Chapter {

  private long chapterId;
  private String chapterName;
  private long subjectId;
  private long chapterSize;
  private long chapterVersion;
  private String chapterFounder;
  private java.sql.Timestamp chapterTime;
  private String chapterDelete;

  public Chapter(String chapterName) {
    this.chapterName = chapterName;
  }

  public long getChapterId() {
    return chapterId;
  }

  public void setChapterId(long chapterId) {
    this.chapterId = chapterId;
  }


  public String getChapterName() {
    return chapterName;
  }

  public void setChapterName(String chapterName) {
    this.chapterName = chapterName;
  }


  public long getSubjectId() {
    return subjectId;
  }

  public void setSubjectId(long subjectId) {
    this.subjectId = subjectId;
  }


  public long getChapterSize() {
    return chapterSize;
  }

  public void setChapterSize(long chapterSize) {
    this.chapterSize = chapterSize;
  }


  public long getChapterVersion() {
    return chapterVersion;
  }

  public void setChapterVersion(long chapterVersion) {
    this.chapterVersion = chapterVersion;
  }


  public String getChapterFounder() {
    return chapterFounder;
  }

  public void setChapterFounder(String chapterFounder) {
    this.chapterFounder = chapterFounder;
  }


  public java.sql.Timestamp getChapterTime() {
    return chapterTime;
  }

  public void setChapterTime(java.sql.Timestamp chapterTime) {
    this.chapterTime = chapterTime;
  }


  public String getChapterDelete() {
    return chapterDelete;
  }

  public void setChapterDelete(String chapterDelete) {
    this.chapterDelete = chapterDelete;
  }

}
