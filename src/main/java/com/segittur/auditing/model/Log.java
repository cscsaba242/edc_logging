package com.segittur.auditing.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

/**
 * The main class for convey information about process between
 * the actors db and apis
 */
@Document(collection = "logs")
public class Log {
    /**
     * id unique identifier
     */
    @Id
    private String id;
    /**
     * EDC unique identifier
     */
    private String edcId;
    /**
     * time of the process or event to be recorded
     */
    private long timestamp;
    /**
     * data provider id
     */
    private String providerId;
    /**
     * data consumer id
     */
    private String consumerId;
    /**
     * process name
     */
    private String process;
    /**
     * process step name
     */
    private String processStep;
    /**
     * state of the process step
     */
    private String stepState;
    /**
     * process data payload
     */
    private Map<String, Object> data;

    /**
     * Constructor to initialize a new {@link Log} entry.
     * <p>
     * This constructor allows setting all the essential properties of a log entry:
     * timestamp, provider ID, consumer ID, process details, and any additional data.
     *
     * @param timestamp   the timestamp when the log entry was created
     * @param providerId  the ID of the provider system
     * @param consumerId  the ID of the consumer system
     * @param process     the name of the process associated with this log entry
     * @param processStep the specific step of the process
     * @param stepState   the state of the current process step
     * @param data        additional information stored as key-value pairs related to the log entry
     */
    public Log(long timestamp, String edcId, String providerId, String consumerId, String process, String processStep, String stepState, Map<String, Object> data) {
        this.timestamp = timestamp;
        this.edcId = edcId;
        this.providerId = providerId;
        this.consumerId = consumerId;
        this.process = process;
        this.processStep = processStep;
        this.stepState = stepState;
        this.data = data;
    }

    // Getters and setters

    /**
     * Returns the unique identifier of the log entry.
     *
     * @return the ID of the log entry
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the log entry.
     *
     * @param id the ID to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the ID of the provider system.
     *
     * @return the provider ID
     */
    public String getProviderId() {
        return providerId;
    }

    /**
     * Sets the ID of the provider system.
     *
     * @param paramProvider the provider ID to set
     */
    public void setProviderId(String paramProvider) {
        this.providerId = paramProvider;
    }

    /**
     * Returns the ID of the consumer system.
     *
     * @return the consumer ID
     */
    public String getConsumerId() {
        return consumerId;
    }

    /**
     * Sets the ID of the consumer system.
     *
     * @param paramConsumer the consumer ID to set
     */
    public void setConsumerId(String paramConsumer) {
        this.consumerId = paramConsumer;
    }

    /**
     * Returns additional data associated with the log entry.
     *
     * @return the log data as a map
     */
    public Map<String, Object> getData() {
        return data;
    }

    /**
     * Sets additional data for the log entry.
     *
     * @param paramData the data to set
     */
    public void setData(Map<String, Object> paramData) {
        this.data = paramData;
    }

    /**
     * Returns the timestamp of the log entry.
     *
     * @return the timestamp
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp for the log entry.
     *
     * @param paramTimestamp the timestamp to set
     */
    public void setTimestamp(long paramTimestamp) {
        this.timestamp = paramTimestamp;
    }

    /**
     * Returns the name of the process this log entry belongs to.
     *
     * @return the process name
     */
    public String getProcess() {
        return process;
    }

    /**
     * Sets the name of the process for this log entry.
     *
     * @param paramProcess the process name to set
     */
    public void setProcess(String paramProcess) {
        this.process = paramProcess;
    }

    /**
     * Returns the step within the process this log refers to.
     *
     * @return the process step name
     */
    public String getProcessStep() {
        return processStep;
    }

    /**
     * Sets the step within the process for this log entry.
     *
     * @param paramStep the process step name to set
     */
    public void setProcessStep(String paramStep) {
        this.processStep = paramStep;
    }

    /**
     * Returns the state of the process step.
     *
     * @return the step state
     */
    public String getStepState() {
        return stepState;
    }

    /**
     * Sets the state of the process step.
     *
     * @param paramState the step state to set
     */
    public void setStepState(String paramState) {
        this.stepState = paramState;
    }

    /**
     * Returns a string representation of the log entry for debugging and logging purposes.
     *
     * @return a string describing the log entry
     */
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Log{");
        sb.append(", timestamp=").append(timestamp);
        sb.append(", providerId='").append(providerId).append('\'');
        sb.append(", consumerId='").append(consumerId).append('\'');
        sb.append(", process='").append(process).append('\'');
        sb.append(", processStep='").append(processStep).append('\'');
        sb.append(", state='").append(stepState).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
