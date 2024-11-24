package com.ustyn.courseproject.converters.literatures;

import com.ustyn.courseproject.document.literature.Article;
import com.ustyn.courseproject.document.literature.Book;
import com.ustyn.courseproject.document.literature.Literature;
import org.bson.Document;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.time.ZoneId;

@ReadingConverter
public class DocumentToLiteratureConverter implements Converter<Document, Literature> {
    @Override
    public Literature convert(Document source) {
        String type = source.getString("_class");
        if ("Book".equals(type)) {
            Book book = new Book();
            book.setPages(source.getInteger("pages"));
            setCommonFields(source, book);
            return book;
        } else if ("Article".equals(type)) {
            Article article = new Article();
            article.setPublishDate(source.getDate("publishDate").toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate());
            setCommonFields(source, article);
            return article;
        }
        throw new IllegalArgumentException("Unsupported type: " + type);
    }

    private void setCommonFields(Document source, Literature literature) {
        literature.setId(source.getObjectId("_id"));
        literature.setTitle(source.getString("title"));
        literature.setAuthor(source.getString("author"));
        literature.setInventoryNumber(source.getString("inventoryNumber"));
        literature.setAvailable(source.getBoolean("available"));
    }
}