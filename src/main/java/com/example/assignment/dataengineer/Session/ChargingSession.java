package com.example.assignment.dataengineer.Session;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Class for Charging Session : It defines the various fields for Charging Session
 */
public class ChargingSession {
    UUID id;
    String stationId;
    LocalDateTime startedAt;
    LocalDateTime stoppedAt;
    StatusEnum status;
    public enum StatusEnum   {
        INPROGRESS,
        FINISHED}

    public UUID getId() {
        return id;
    }

    public String getStationId() {
        return stationId;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public void setStoppedAt(LocalDateTime stoppedAt) {
        this.stoppedAt = stoppedAt;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public LocalDateTime getStoppedAt() {
        return stoppedAt;
    }

    public StatusEnum getStatus() {
        return status;
    }
    public ChargingSession() {

    }

    public ChargingSession(UUID id, String stationId, LocalDateTime startedAt, LocalDateTime stoppedAt, StatusEnum status) {
        this.id = id;
        this.stationId = stationId;
        this.startedAt = startedAt;
        this.stoppedAt = stoppedAt;
        this.status = status;
    }
    @Override
    public String toString()
    {
        return String.format("Session [id = %s, stationId=%s, startedAt=%s, stoppedAt=%s,status=%s]"
                ,id, stationId, startedAt, stoppedAt, status);
    }
}
