package com.aram.noteservice.repository;

import com.aram.noteservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author aram
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}
