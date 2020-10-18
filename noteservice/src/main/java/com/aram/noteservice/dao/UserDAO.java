package com.aram.noteservice.dao;

import com.aram.noteservice.domain.User;
import java.util.Optional;

/**
 *
 * @author aram
 */
public interface UserDAO {
    
    Optional<User> getUserByEmail(String email);
    
}
