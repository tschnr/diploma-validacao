package com.digitalhouse.obtenerdiploma.semMock;

import com.digitalhouse.obtenerdiploma.dto.CertificateDTO;
import com.digitalhouse.obtenerdiploma.dto.StudentDTO;
import com.digitalhouse.obtenerdiploma.dto.SubjectDTO;
import com.digitalhouse.obtenerdiploma.service.CertificateServiceImpl;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CertificateServiceImplTest {

    private final CertificateServiceImpl certificateService = new CertificateServiceImpl();

    @Test
    void shouldAnalyseNotesWithoutStudent(){
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setSubjects(List.of(
                new SubjectDTO("Fisica Quantica", 2),
                new SubjectDTO("Geografia avancada", 10),
                new SubjectDTO("Ciencias contabeis", 9))
        );

        CertificateDTO certificateDTO = certificateService.analyzeNotes(studentDTO);

        assertNotEquals(certificateDTO.getName(), "Thiago Schnr");
    }

    @Test
    public void shouldAnalyseNotesCalculateAverage() {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName("Thiago Schnr");
        studentDTO.setSubjects(List.of(
                new SubjectDTO("Fisica Quantica", 2),
                new SubjectDTO("Geografia avancada", 10),
                new SubjectDTO("Ciencias contabeis", 9))
        );

        CertificateDTO certificateDTO = certificateService.analyzeNotes(studentDTO);

        assertEquals(certificateDTO.getAverage(), 7);

    }

    @Test
    public void shouldCalculateAverageNote() {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName("Thiago Schnr");
        studentDTO.setSubjects(List.of(
                new SubjectDTO("Fisica Quantica", 2),
                new SubjectDTO("Geografia avancada", 10),
                new SubjectDTO("Ciencias contabeis", 9))
        );

        Double certificateDTO = certificateService.calculateAverage(studentDTO);

        assertEquals(certificateDTO, 7);

    }

    @Test
    public void shouldCalculateAverageNoteResultDouble() {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName("Thiago Schnr");
        studentDTO.setSubjects(List.of(
                new SubjectDTO("Fisica Quantica", 2),
                new SubjectDTO("Geografia avancada", 10),
                new SubjectDTO("Ciencias contabeis", 9))
        );

        Double certificateDTO = certificateService.calculateAverage(studentDTO);

        assertNotEquals(certificateDTO, "7");

    }

    @Test
    public void shouldWriteDiplomaIsOk(){
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName("Thiago Schnr");
        studentDTO.setSubjects(List.of(
                new SubjectDTO("Fisica Quantica", 10),
                new SubjectDTO("Geografia avancada", 10),
                new SubjectDTO("Ciencias contabeis", 10))
        );

        String certificateDTO = certificateService.writeDiploma(studentDTO);

        assertEquals(certificateDTO, "¡Felicitaciones Thiago Schnr! Usted tiene el gran promedio de 10.0");
    }


    @Test
    public void shouldWriteDiplomaFail(){
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName("Thiago Schnr");
        studentDTO.setSubjects(List.of(
                new SubjectDTO("Fisica Quantica", 8),
                new SubjectDTO("Geografia avancada", 10),
                new SubjectDTO("Ciencias contabeis", 9))
        );

        String certificateDTO = certificateService.writeDiploma(studentDTO);

        assertNotEquals(certificateDTO, "¡Felicitaciones Thiago Schnr! Usted tiene el gran promedio de 9");
    }

    @Test
    public void shouldWithHonors(){
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName("Thiago Schnr");
        studentDTO.setSubjects(List.of(
                new SubjectDTO("Fisica Quantica", 8),
                new SubjectDTO("Geografia avancada", 10),
                new SubjectDTO("Ciencias contabeis", 9))
        );

        String certificateDTO = certificateService.withHonors(9.0, studentDTO.getName());

        assertEquals(certificateDTO, "¡Felicitaciones Thiago Schnr! Usted tiene el gran promedio de 9.0");

    }

}
