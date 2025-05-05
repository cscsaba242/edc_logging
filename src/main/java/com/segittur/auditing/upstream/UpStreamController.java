package com.segittur.auditing.upstream;

import com.segittur.auditing.downstream.DownStreamController;
import com.segittur.auditing.model.Log;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * web api front controller for upstream
 * to load logs into the db
 */
@RestController
@RequestMapping("/upstream")
public class UpStreamController {
    /**
     * Logger instance used for logging events, errors, and informational messages
     * related to {@link DownStreamController}.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DownStreamController.class);
    /**
     * Service that handles upstream-related business logic.
     */
    @Autowired
    private UpStreamService service;

    /**
     * Saves a log entry to the upstream repository.
     * <p>
     * Accepts a JSON representation of a {@link Log} object in the request body
     * and persists it to the database.
     *
     * @param paramLog The {@link Log} object to be saved. Must not be {@code null}.
     * @return A {@link ResponseEntity} containing the saved log and an HTTP status code:
     *         - {@code 200 OK} if saving was successful.
     *         - {@code 500 Internal Server Error} if an exception occurred.
     */
    @PostMapping(path = "/save")
    @Operation(summary = "Save a log entry",
            description = "This endpoint saves a log entry provided in the request body as a JSON object.")
    public ResponseEntity<Log> save(@Parameter(description = "Log object to be saved", required = true) @RequestBody Log paramLog) {
        try {
            service.save(paramLog);
            return new ResponseEntity<>(paramLog, HttpStatus.CREATED);
        } catch (Exception e) {
            LOGGER.error("Error /save, message:" + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
