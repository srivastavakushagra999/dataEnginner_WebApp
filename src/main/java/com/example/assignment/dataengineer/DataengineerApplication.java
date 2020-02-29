package com.example.assignment.dataengineer;

import com.example.assignment.dataengineer.Session.ChargingSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;


@SpringBootApplication
public class DataengineerApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(DataengineerApplication.class, args);
		ChargingSession cs = new ChargingSession(UUID.randomUUID(),"Amsterdam",
				LocalDateTime.now(), LocalDateTime.now(), ChargingSession.StatusEnum.FINISHED);
		System.out.println(cs.toString());

	}

}
