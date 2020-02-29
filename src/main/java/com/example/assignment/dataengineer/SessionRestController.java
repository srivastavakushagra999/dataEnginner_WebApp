package com.example.assignment.dataengineer;

import com.example.assignment.dataengineer.Session.ChargingSession;
import com.example.assignment.dataengineer.Session.GetSummaryCollection;
import com.example.assignment.dataengineer.Session.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

// Rest controller for the class for executing the rest commands

@RestController
public class SessionRestController {
    @Autowired
    private SessionService utils = new SessionService();

    @GetMapping("/chargingSessions")
    public List<ChargingSession> retrieveAllUsers() {
        return utils.findAll();
    }

    @GetMapping("/chargingSessions/summary")
    public GetSummaryCollection retrieveSummary() {
        return utils.getSummary();
    }

    @PutMapping("/chargingSessions/{id}")
    public ChargingSession update(@RequestBody @PathVariable String id) {
        return utils.updateCall(id);

    }


    @PostMapping("/chargingSessions")
    ResponseEntity<?> add(@Valid @RequestBody ChargingSession newSessions) {
        ChargingSession user = utils.save(newSessions);
        if (user == null) {
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }



}
