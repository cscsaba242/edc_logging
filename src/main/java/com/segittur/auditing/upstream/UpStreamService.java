package com.segittur.auditing.upstream;

import com.segittur.auditing.model.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.GitProperties;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for handling upstream operations related to logs.
 * This service interacts with the {@link UpStreamRepository} to save logs.
 */
@Service
public class UpStreamService {

    /**
     * Repository responsible for performing CRUD operations on log data.
     */
    @Autowired
    private UpStreamRepository repository;

    /**
     * Git commit details
     */
    @Autowired
    private GitProperties gitProperties;

    /**
     * Saves the provided log data using the {@link UpStreamRepository}.
     *
     * @param paramLog The log data to be saved.
     */
    public void save(Log paramLog) {
        this.repository.save(paramLog);
    }
}
