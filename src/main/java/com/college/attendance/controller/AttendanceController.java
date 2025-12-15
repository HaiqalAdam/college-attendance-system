package com.college.attendance.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.college.attendance.service.AttendanceService;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {
	private final AttendanceService attendanceService;
	
	public AttendanceController(AttendanceService attendanceService) {
		this.attendanceService = attendanceService;
	}
	
	@PostMapping("/check-in")
	public ResponseEntity<String> checkIn(@RequestParam String identifier){
		attendanceService.checkIn(identifier);
		return ResponseEntity.ok("Check-in successful");
	}
	
	@PostMapping("/check-out")
	public ResponseEntity<String> checkOut(@RequestParam String identifier){
		attendanceService.checkOut(identifier);
		return ResponseEntity.ok("Check-out successful");
	}
}
