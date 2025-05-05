package com.segittur.auditing.downstream;

import com.segittur.auditing.model.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.GitProperties;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class responsible for handling downstream logic.
 * <p>
 * This service interacts with the {@link DownStreamRepository} for data retrieval and the {@link GitProperties}
 * for fetching build version information.
 */
@Service
public class DownStreamService {
    @Autowired
    private DownStreamRepository repository;

    @Autowired
    private GitProperties gitProperties;

    /**
     * Retrieves all log entries from the downstream repository.
     *
     * @return a list of {@link Log} entries from the repository
     */
    public List<Log> findAll() {
        return repository.findAll();
    }

    /**
     * Retrieves the current build version of the application from Git properties.
     * <p>
     * This version is typically the git tag or build version that is used to identify
     * the deployed version of the application.
     *
     * @return the build version as a {@link String}
     */
    public String getBuildVersion() {
        return gitProperties.get("build.version");
    }
}
