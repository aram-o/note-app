package com.aram.noteservice.repository;

import com.aram.noteservice.domain.Note;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author aram
 */
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
}
