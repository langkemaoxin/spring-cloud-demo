package com.lagou.edu.dao;

import com.lagou.edu.pojo.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * 创建一个接口，继承JpaRepository
 *
 */
public interface ResumeDao extends JpaRepository<Resume,Long> {
}
