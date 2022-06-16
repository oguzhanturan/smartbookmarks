package com.smartbookmarks.repository;

import com.smartbookmarks.model.bookmark.Bookmark;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface BookmarksRepository extends MongoRepository<Bookmark, String> {
    @Query("{'url' : ?0, 'userId' : ?1}")
    Optional<Bookmark> findByUrl(String url, String userId);

    @Query("{'userId' : ?0}")
    Optional<Set<Bookmark>> findAllByUserId(String userId);

    @Query("{'_id' : ?0 ,'userId' : ?1}")
    Optional<Bookmark> findByIdAndUserId(String id, String userId);
}
