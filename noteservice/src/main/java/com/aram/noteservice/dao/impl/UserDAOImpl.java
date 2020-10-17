package com.aram.noteservice.dao.impl;

import com.aram.noteservice.dao.UserDAO;
import com.aram.noteservice.domain.User;
import com.aram.noteservice.repository.UserRepository;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author aram
 */
@Component
@Slf4j
public class UserDAOImpl implements UserDAO {
    
    @Autowired
    private UserRepository userRepository;
    
    /**
     * Method to return particular user object by given id.
     * @param id
     * @return
     */
    @Override
    public Optional<User> getUserById(
        Long id
    ) {
        Optional<User> user = this.userRepository.findById(id);
        return user;
    }
    
}
