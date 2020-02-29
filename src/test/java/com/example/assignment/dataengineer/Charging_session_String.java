package com.example.assignment.dataengineer;

public class Charging_session_String {
    String id;
    String stationId;
    String startedAt;
    String stoppedAt;
    public Charging_session_String()
    {
        super();
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public void setStartedAt(String startedAt) {
        this.startedAt = startedAt;
    }

    public void setStoppedAt(String stoppedAt) {
        this.stoppedAt = stoppedAt;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStationId() {
        return stationId;
    }

    public String getStartedAt() {
        return startedAt;
    }

    public String getStoppedAt() {
        return stoppedAt;
    }

    public String getStatus() {
        return status;
    }

    public Charging_session_String(String id, String stationId, String startedAt, String stoppedAt, String status) {
        this.id = id;
        this.stationId = stationId;
        this.startedAt = startedAt;
        this.stoppedAt = stoppedAt;
        this.status = status;
    }

    String status;

    public String getId() {
        return id;
    }

}
