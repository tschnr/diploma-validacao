package com.digitalhouse.obtenerdiploma;


import com.digitalhouse.obtenerdiploma.dto.StudentDTO;
import com.digitalhouse.obtenerdiploma.dto.SubjectDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CertificateServiceImplTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    //001
    @Test
    public void analyseNotesWithoutStudent() throws Exception {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setSubjects(List.of(
                new SubjectDTO("Matematica", 2),
                new SubjectDTO("Geografia", 10),
                new SubjectDTO("Ciencias", 9))
        );
        byte[] payload = objectMapper.writeValueAsBytes(studentDTO);

        this.mockMvc.perform(
                post("/analyzeNotes").content(payload).contentType(MediaType.APPLICATION_JSON)

        ).andExpect(status().isBadRequest());
    }

    @Test
    public void analyseNotesNameStudentShort() throws Exception {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName("Thiago");
        studentDTO.setSubjects(List.of(
                new SubjectDTO("Matematica", 2),
                new SubjectDTO("Geografia", 10),
                new SubjectDTO("Ciencias", 9))
        );
        byte[] payload = objectMapper.writeValueAsBytes(studentDTO);

        this.mockMvc.perform(
                post("/analyzeNotes").content(payload).contentType(MediaType.APPLICATION_JSON)

        ).andExpect(status().isBadRequest());
    }

    @Test
    public void analyseNotesWithoutSubjects() throws Exception {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName("Thiago Schnr");

        byte[] payload = objectMapper.writeValueAsBytes(studentDTO);

        this.mockMvc.perform(
                post("/analyzeNotes").content(payload).contentType(MediaType.APPLICATION_JSON)

        ).andExpect(status().isBadRequest());
    }

    @Test
    public void analyseNotesNoteString() throws Exception {
        String request = "{\"name\": \"Rodrigo Díaz de Vivar\", \"subjects\": [" +
                "        {\"subject\":\"Paradigmas de Programación\",\"note\": 8}," +
                "        {\"subject\":\"Diseño\",\"note\": 5}," +
                "{\"subject\":\"Programación Avanzada\",\"note\": \"Abc\"}," +
                "{\"subject\":\"Inteligencia Artificial\",\"note\": 7}," +
                "{\"subject\":\"Matemática Superior\",\"note\": 10}" +
                "]" +
                "}" ;

        this.mockMvc.perform(
                post("/analyzeNotes").content(request).contentType(MediaType.APPLICATION_JSON)

        ).andExpect(status().isBadRequest());
    }

    @Test
    public void analyseNotesNameSubjectShort() throws Exception {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName("Thiago Schnr");
        studentDTO.setSubjects(List.of(
                new SubjectDTO("Fisica", 2),
                new SubjectDTO("Geografia avancada", 10),
                new SubjectDTO("Ciencias contabeis", 9))
        );
        byte[] payload = objectMapper.writeValueAsBytes(studentDTO);

        this.mockMvc.perform(
                post("/analyzeNotes").content(payload).contentType(MediaType.APPLICATION_JSON)

        ).andExpect(status().isBadRequest());
    }

    @Test
    public void analyseNotesIsOk() throws Exception {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName("Thiago Schnr");
        studentDTO.setSubjects(List.of(
                new SubjectDTO("Fisica Quantica", 2),
                new SubjectDTO("Geografia avancada", 10),
                new SubjectDTO("Ciencias contabeis", 9))
        );
        byte[] payload = objectMapper.writeValueAsBytes(studentDTO);

        this.mockMvc.perform(
                post("/analyzeNotes").content(payload).contentType(MediaType.APPLICATION_JSON)

        ).andExpect(status().isCreated());
    }


    //002
    @Test
    public void analyseNotesCalculateAverageIsOk() throws Exception {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName("Thiago Schnr");
        studentDTO.setSubjects(List.of(
                new SubjectDTO("Fisica Quantica", 2),
                new SubjectDTO("Geografia avancada", 10),
                new SubjectDTO("Ciencias contabeis", 9))
        );
        byte[] payload = objectMapper.writeValueAsBytes(studentDTO);

        this.mockMvc.perform(
                post("/analyzeNotes").content(payload).contentType(MediaType.APPLICATION_JSON)

        ).andExpect(status().isCreated()).andExpect(jsonPath("$.average").value(7));
    }

    //003
    @Test
    void analyzeNotesReprovedMessage() throws Exception {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName("Thiago Schnr");
        studentDTO.setSubjects(List.of(
                new SubjectDTO("Fisica Quantica", 2),
                new SubjectDTO("Geografia avancada", 10),
                new SubjectDTO("Ciencias contabeis", 9))
        );
        byte[] payload = objectMapper.writeValueAsBytes(studentDTO);
        this.mockMvc.perform(
                post("/analyzeNotes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Sua média foi de 7.0"));
    }

    @Test
    void analyzeNotesApprovedMessage() throws Exception {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName("Thiago Schnr");
        studentDTO.setSubjects(List.of(
                new SubjectDTO("Fisica Quantica", 10),
                new SubjectDTO("Geografia avancada", 10),
                new SubjectDTO("Ciencias contabeis", 10))
        );
        byte[] payload = objectMapper.writeValueAsBytes(studentDTO);
        this.mockMvc.perform(
                post("/analyzeNotes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("¡Felicitaciones Thiago Schnr! Usted tiene el gran promedio de 10.0"));
    }

}
