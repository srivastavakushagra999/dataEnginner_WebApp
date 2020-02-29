package com.example.assignment.dataengineer.Session;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SessionService {
    LocalDateTime now;
    private static ConcurrentHashMap<String, ChargingSession> sessions = new ConcurrentHashMap<>();

    // Get status of all the exsisting sessions on Get Call
    public List<ChargingSession> findAll()
    {
        return new ArrayList<>(sessions.values());
    }

    // Get Summary of the session according to the problem statement on getSummary call
    public synchronized GetSummaryCollection getSummary()
    {
        now = LocalDateTime.now();
        GetSummaryCollection value = new GetSummaryCollection(0,0,0);
        if(!sessions.isEmpty()) {
            for (ChargingSession x : sessions.values()) {
                if (ChronoUnit.MINUTES.between(x.startedAt, now) < 1 && x.getStatus().equals(ChargingSession.StatusEnum.INPROGRESS)) {
                    value.totalCount++;
                    value.startedCount++;
                } else if (x.stoppedAt != null && ChronoUnit.MINUTES.between(x.stoppedAt, now) < 1 && x.getStatus().equals(ChargingSession.StatusEnum.FINISHED)) {
                        value.totalCount++;
                        value.stoppedCount++;
                }
            }
        }
        return value;
    }

    // Creates the new session on the post call
    public synchronized ChargingSession save(ChargingSession session) {
        if (session.getId() == null ) {
            session.setId(UUID.randomUUID());
            session.setStartedAt(LocalDateTime.now());
            session.setStatus(ChargingSession.StatusEnum.INPROGRESS);
        }
        sessions.put(session.getId().toString(), session);
        return session;
    }

    // It updates the session id on the put call
        public  synchronized ChargingSession updateCall(String id) {

        if (sessions.containsKey(id)){
                    ChargingSession update = new ChargingSession();
                    update = sessions.get(id);
                    update.setStoppedAt(LocalDateTime.now());
                    update.setStatus(ChargingSession.StatusEnum.FINISHED);
                    sessions.replace(id, update);
                    return update;
                }
            return null;
        }
    }

