package com.lagou.edu.dao;

import com.lagou.edu.pojo.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Long> {

    Users findByUsername(String username);
}
