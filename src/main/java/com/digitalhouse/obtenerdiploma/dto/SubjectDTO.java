package com.digitalhouse.obtenerdiploma.dto;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SubjectDTO {
  @NotNull
  @Size(min = 8, max = 50, message = "The name must contain a minimum of 8 characters " +
          "and a maximum of 50, check the information")
  @Pattern(regexp = "^[A-Za-z ]*$", message = "The name must contain only letters")
  private String subject;
  @NotNull
  @Size(min = 1, max = 2, message = "The note must contain a minimum of 1 characters " +
          "and a maximum of 2, check the information")
  @Pattern(regexp = "^[0-9]+$", message = "The name must contain only letters")
  private Integer note;

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public Integer getNote() {
    return note;
  }

  public void setNote(Integer note) {
    this.note = note;
  }

}
