package io.github.marcoantoniossilva.blog.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Problem {

  private Integer status;

  private LocalDateTime dateTime;

  private String title;

  private List<Field> fields;

  public Problem() {
  }

  public Problem(Integer status, LocalDateTime dateTime, String title, List<Field> fields) {
    this.status = status;
    this.dateTime = dateTime;
    this.title = title;
    this.fields = fields;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public LocalDateTime getDateTime() {
    return dateTime;
  }

  public void setDateTime(LocalDateTime dateTime) {
    this.dateTime = dateTime;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<Field> getFields() {
    return fields;
  }

  public void setFields(List<Field> fields) {
    this.fields = fields;
  }

  public static class Field {
    private String name;
    private String message;

    public Field(String name, String message) {
      this.name = name;
      this.message = message;
    }
  }



}