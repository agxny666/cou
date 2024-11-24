package com.ustyn.courseproject.converters.readers;

import com.ustyn.courseproject.document.reader.Reader;
import com.ustyn.courseproject.document.reader.Scientist;
import com.ustyn.courseproject.document.reader.Student;
import org.bson.Document;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.time.ZoneId;

@ReadingConverter
public class DocumentToReaderConverter implements Converter<Document, Reader> {
    @Override
    public Reader convert(Document source) {
        String type = source.getString("_class"); // Assuming `_class` identifies the subclass
        if ("Scientist".equals(type)) {
            Scientist scientist = new Scientist();
            scientist.setSpecialty(source.getString("specialty"));
            setCommonFields(source, scientist);
            return scientist;
        } else if ("Student".equals(type)) {
            Student student = new Student();
            student.setInstitution(source.getString("institution"));
            setCommonFields(source, student);
            return student;
        }
        throw new IllegalArgumentException("Unsupported Reader type: " + type);
    }

    private void setCommonFields(Document source, Reader reader) {
        reader.setId(source.getObjectId("_id"));
        reader.setName(source.getString("name"));
        reader.setAddress(source.getString("address"));
        reader.setTicketId(source.getString("ticketId"));
        reader.setLastVisitDate(source.getDate("lastVisitDate").toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate());    }
}
