package com.ustyn.courseproject.repository;

import com.ustyn.courseproject.document.reader.Reader;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReaderRepository extends MongoRepository<Reader, String> {
    boolean existsByName(String name);

    @Aggregation(pipeline = {
            "{ $lookup: { from: 'tickets', localField: 'ticketId', foreignField: '_id', as: 'ticketDetails' } }",
            "{ $unwind: '$ticketDetails' }",
            "{ $match: { 'ticketDetails.borrowedLiteratures._id': ?0 } }"
    })
    List<Reader> findReadersByBorrowedLiterature(String literatureId);

    @Aggregation(pipeline = {
            "{ $match: { $or: [ " +
                    "   { 'institution': ?0 }, " +
                    "   { 'specialty': ?0 } " +
                    "] } }"
    })
    List<Reader> findReadersByInstitutionOrSpecialty(String value);

    @Query("{ 'lastVisitDate': { $lt: ?0 } }")
    List<Reader> findReadersNotVisitedSince(LocalDate date);

}
