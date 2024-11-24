package com.ustyn.courseproject.config;

import com.ustyn.courseproject.converters.literatures.DocumentToLiteratureConverter;
import com.ustyn.courseproject.converters.literatures.LiteratureToDocumentConverter;
import com.ustyn.courseproject.converters.readers.DocumentToReaderConverter;
import com.ustyn.courseproject.converters.readers.ReaderToDocumentConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import java.util.Arrays;

// TODO: test convertors
@Configuration
public class MongoConvertersConfig {

    @Bean
    public MongoCustomConversions customConversions() {
        return new MongoCustomConversions(Arrays.asList(
                // literatures
                new DocumentToLiteratureConverter(),
                new LiteratureToDocumentConverter(),

                // readers
                new DocumentToReaderConverter(),
                new ReaderToDocumentConverter()
        ));
    }
}