package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Attendee.User;

public interface UserRepository extends JpaRepository<User, Long> {
//    User findByUsername(String username);
//    User findAll(String AttendeeEntity);

//	public boolean existsByEmail(String email);
    public User findByEmail(String email);
}