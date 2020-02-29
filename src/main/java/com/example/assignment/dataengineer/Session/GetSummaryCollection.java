package com.example.assignment.dataengineer.Session;

/**
 * Class for Getting the summary : It defines the structure for getting the summary
 */

public class GetSummaryCollection {
    int totalCount;
    int startedCount;
    int stoppedCount;


    public GetSummaryCollection(int totalCount, int startedCount, int stoppedCount) {
        this.totalCount = totalCount;
        this.startedCount = startedCount;
        this.stoppedCount = stoppedCount;
    }

    public GetSummaryCollection() {
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getStartedCount() {
        return startedCount;
    }

    public int getStoppedCount() {
        return stoppedCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setStartedCount(int startedCount) {
        this.startedCount = startedCount;
    }

    public void setStoppedCount(int stoppedCount) {
        this.stoppedCount = stoppedCount;
    }
}
