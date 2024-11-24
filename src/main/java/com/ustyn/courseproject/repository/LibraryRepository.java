package com.ustyn.courseproject.repository;

import com.ustyn.courseproject.document.library.Library;
import com.ustyn.courseproject.document.library.LibraryStaff;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryRepository extends MongoRepository<Library, String> {
    boolean existsByName(String name);

    @Aggregation(pipeline = {
            "{ $match: { _id: ?0 } }",
            "{ $lookup: { from: 'libraryStaffs', localField: 'libraryStaffs', foreignField: '_id', as: 'staff' } }",
            "{ $unwind: '$staff' }",
            "{ $replaceRoot: { newRoot: '$staff' } }"
    })
    List<LibraryStaff> findStaffByLibraryId(ObjectId libraryId);
}
