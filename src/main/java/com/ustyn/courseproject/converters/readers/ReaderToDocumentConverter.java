package com.ustyn.courseproject.converters.readers;

import com.ustyn.courseproject.document.reader.Reader;
import com.ustyn.courseproject.document.reader.Scientist;
import com.ustyn.courseproject.document.reader.Student;
import org.bson.Document;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import java.time.ZoneId;
import java.util.Date;

@WritingConverter
public class ReaderToDocumentConverter implements Converter<Reader, Document> {
    @Override
    public Document convert(Reader source) {
        Document document = new Document();
        document.put("_id", source.getId());
        document.put("name", source.getName());
        document.put("address", source.getAddress());
        document.put("ticketId", source.getTicketId());
        document.put("lastVisitDate", Date.from(source.getLastVisitDate()
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant()));
        if (source instanceof Scientist) {
            document.put("_class", "Scientist");
            document.put("specialty", ((Scientist) source).getSpecialty());
        } else if (source instanceof Student) {
            document.put("_class", "Student");
            document.put("institution", ((Student) source).getInstitution());
        } else {
            throw new IllegalArgumentException("Unsupported Reader type: " + source.getClass().getName());
        }

        return document;
    }
}
