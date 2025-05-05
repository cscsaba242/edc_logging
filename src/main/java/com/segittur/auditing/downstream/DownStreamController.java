package com.segittur.auditing.downstream;

import com.segittur.auditing.model.Log;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller responsible for handling HTTP requests related to downstream operations.
 * <p>
 * This controller provides endpoints under the <code>/downstream</code> path, and typically
 * delegates business logic to the {@link DownStreamService}.
 */
@RestController
@RequestMapping("/downstream")
public class DownStreamController {
    /**
     * Default logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DownStreamController.class);
    /**
     * Service responsible for handling downstream operations.
     */
    @Autowired
    private DownStreamService service;

    /**
     * Retrieves all log entries.
     *
     * @return A {@link ResponseEntity} containing a list of {@link Log} objects and an HTTP status code.
     *         - {@code 200 OK} if the logs were successfully retrieved.
     *         - {@code 500 Internal Server Error} if an exception occurred during retrieval.
     */
    @GetMapping(path = "/findAll")
    @Operation(summary = "Get all logs",
            description = "Returns a list of all Log entries.")
    @ResponseBody
    public ResponseEntity<List<Log>> findAll() {
        try {
            List<Log> result = service.findAll();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Error /findAll, message:" + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Retrieves the build version of the currently running application.
     * <p>
     * The build version includes details such as the Git tag, tag distance, and short commit hash.
     * This information is typically extracted from Git metadata.
     *
     * @return A {@link ResponseEntity} containing:
     *         - The build version string and {@code 200 OK} on success.
     *         - {@code 500 Internal Server Error} if an exception occurs during retrieval.
     */
    @GetMapping("/getBuildVersion")
    @Operation(summary = "Get build version",
            description = "Get build version (tag,tag distance, short hash) of current application code")
    @ResponseBody
    public ResponseEntity<String> getBuildVersion() {
        try {
            String result = service.getBuildVersion();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Error /getBuildVersion, message:" + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
