package com.digitalhouse.obtenerdiploma.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

public class StudentDTO {
    @NotNull
    @Size(min = 8, max = 50, message = "The name must contain a minimum of 8 characters " +
            "and a maximum of 50, check the information")
    @Pattern(regexp = "^[A-Za-z ]*$", message = "The name must contain only letters")
    private String name;

    @Valid
    private List<SubjectDTO> subjects;

    public StudentDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SubjectDTO> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectDTO> subjects) {
        this.subjects = subjects;
    }

}
