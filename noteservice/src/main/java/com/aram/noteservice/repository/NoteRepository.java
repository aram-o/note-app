package com.aram.noteservice.repository;

import com.aram.noteservice.domain.Note;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author aram
 */
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    
    Optional<Note> findOneById(Long id);
    List<Note> findAllByUserEmail(String userEmail);
}
