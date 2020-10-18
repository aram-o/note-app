package com.aram.noteservice.dao;

import com.aram.noteservice.domain.Note;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author aram
 */
public interface NoteDAO {
    
    Optional<Note> getNoteById(Long id);
    List<Note> getAllNotesByUserEmail(String userEmail);
    Optional<Note> saveNote(Note note);
    Boolean deleteById(Long id);
    
}
