package com.college.attendance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.college.attendance.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{
	Optional<Users> findByIdentifier(String identifier);
	

}
