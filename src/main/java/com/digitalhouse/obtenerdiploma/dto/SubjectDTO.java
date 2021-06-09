package com.digitalhouse.obtenerdiploma.dto;

import org.hibernate.validator.constraints.Range;

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
  @Range(min = 1, max = 10, message = "The note must contain a minimum of 1 characters " +
          "and a maximum of 2, check the information")
  //@Pattern(regexp = "[0-9]", message = "The name must contain only letters")
  private Integer note;

  public SubjectDTO(@NotNull @Size(min = 8, max = 50, message = "The name must contain a minimum of 8 characters " +
          "and a maximum of 50, check the information") @Pattern(regexp = "^[A-Za-z ]*$", message = "The name must contain only letters") String subject, @NotNull @Range(min = 1, max = 10, message = "The note must contain a minimum of 1 characters " +
          "and a maximum of 2, check the information") Integer note) {
    this.subject = subject;
    this.note = note;
  }

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
