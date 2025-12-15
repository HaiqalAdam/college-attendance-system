package com.college.attendance.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.college.attendance.entity.Attendance;
import com.college.attendance.entity.Users;
import com.college.attendance.exception.BusinessException;
import com.college.attendance.repository.AttendanceRepository;
import com.college.attendance.repository.UsersRepository;
import com.college.attendance.service.AttendanceService;

@Service 
public class AttendanceServiceImpl implements AttendanceService {
	private final UsersRepository userRepo;
	private final AttendanceRepository attendanceRepo;
	
	public AttendanceServiceImpl(UsersRepository userRepo, AttendanceRepository attendanceRepo) {
		this.userRepo = userRepo;
		this.attendanceRepo = attendanceRepo;
	}
	
//	@Override
//	public void checkIn(String identifier) {
//		
//		//Find user
//		Users users = userRepo.findByIdentifier(identifier)
//				.orElseThrow(() -> new BusinessException("User not found"));
//		
//		//Get today's date (Server time)
//		LocalDate today = LocalDate.now();
//		
//		//check id already check in today
//		boolean alreadyCheckedIn = attendanceRepo.findByUsersAndAttendanceDate(users, today).isPresent();
//		
//		//create attendance record
//		if (alreadyCheckedIn) {
//			Attendance attendance = new Attendance();
//			attendance.setUsers(users);
//			attendance.setAttendanceDate(today);
//			attendance.setCheckInTime(LocalDateTime.now());
//			
//			//save
//			attendanceRepo.save(attendance);
//		
//		}
//	}
	
	@Override
	public void checkIn(String identifier) {

	    Users users = userRepo.findByIdentifier(identifier)
	            .orElseThrow(() -> new BusinessException("User not found"));

	    LocalDate today = LocalDate.now();

	    boolean alreadyCheckedIn =
	            attendanceRepo.findByUsersAndAttendanceDate(users, today).isPresent();

	    // ✅ If already checked in → ERROR
	    if (alreadyCheckedIn) {
	        throw new BusinessException("User already checked in today");
	    }

	    // ✅ Create attendance ONLY if not checked in
	    Attendance attendance = new Attendance();
	    attendance.setUsers(users);
	    attendance.setAttendanceDate(today);
	    attendance.setCheckInTime(LocalDateTime.now());

	    attendanceRepo.save(attendance);
	}

		
		@Override
		public void checkOut(String identifier) {
			Users users = userRepo.findByIdentifier(identifier)
					.orElseThrow(() -> new BusinessException("User not found"));
			
			LocalDate today = LocalDate.now();
			
			Attendance attendance = attendanceRepo
	                .findByUsersAndAttendanceDate(users, today)
	                .orElseThrow(() -> new BusinessException("User has not checked in today"));

	        // 4. Prevent duplicate check-out
	        if (attendance.getCheckOutTime() != null) {
	            throw new BusinessException("User already checked out today");
		}
	        
	        attendance.setCheckOutTime(LocalDateTime.now());

	        // 6. Save
	        attendanceRepo.save(attendance);
		}

}
