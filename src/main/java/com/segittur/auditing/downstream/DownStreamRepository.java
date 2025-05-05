package com.segittur.auditing.downstream;

import com.segittur.auditing.model.Log;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Db manager for outgoing/read-only/query request
 */
@Repository
public interface DownStreamRepository extends MongoRepository<Log, String> {
}
