package com.maruchekas.todolist.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "tasks")
public class Task {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(nullable = false)
  private String title, text;
  @Column(nullable = false, length = 15, name = "end_datetime")
  @Temporal(TemporalType.DATE)
  private Date endDate;
  @Column(nullable = false, length = 15, name = "created_datetime")
  private Date createdDateTime;
  private int views;

  public Task() {
  }

  public Task(String title, String text, Date endDate, Date createdDateTime) {

  }
}
