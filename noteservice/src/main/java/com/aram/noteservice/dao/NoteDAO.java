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
    List<Note> getAllNotes();
    Optional<Note> saveNote(Note note);
    boolean deleteById(Long id);
    
}
