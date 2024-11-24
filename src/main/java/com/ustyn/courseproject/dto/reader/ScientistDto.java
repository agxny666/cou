package com.ustyn.courseproject.dto.reader;

import com.ustyn.courseproject.document.reader.Scientist;
import com.ustyn.courseproject.validation.inPast.InPast;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class ScientistDto {
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
    private String specialty;

    public ScientistDto(Scientist scientist) {
        this.id = scientist.getId().toString();
        this.name = scientist.getName();
        this.address = scientist.getAddress();
        this.ticketId = scientist.getTicketId();
        this.lastVisitDate = scientist.getLastVisitDate();
        this.specialty = scientist.getSpecialty();
    }
}
