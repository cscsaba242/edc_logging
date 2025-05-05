package com.segittur.auditing.upstream;

import com.segittur.auditing.model.Log;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link Log} entities in MongoDB.
 * <p>
 * Extends {@link MongoRepository} to provide standard CRUD operations
 * for logs identified by a {@code String} ID.
 */
@Repository
public interface UpStreamRepository extends MongoRepository<Log, String> {
}
