package com.ustyn.courseproject.dto.reader;

import com.ustyn.courseproject.document.reader.Student;
import com.ustyn.courseproject.validation.inPast.InPast;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class StudentDto {
    private String id;

    @NotNull(message = "обов'язково")
    private String name;

    @NotNull(message = "обов'язково")
    private String address;

    private String ticketId;

    @NotNull(message = "обов'язково")
    @InPast
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastVisitDate;

    @NotNull(message = "обов'язково")
    private String institution;

    public StudentDto(Student student) {
        this.id = student.getId().toString();
        this.name = student.getName();
        this.address = student.getAddress();
        this.ticketId = student.getTicketId();
        this.lastVisitDate = student.getLastVisitDate();
        this.institution = student.getInstitution();
    }
}
