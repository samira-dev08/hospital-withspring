package com.company.repository;

import com.company.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Long> {

Users findUsersByEmailAndActive(String email,Integer active);
}
