package com.aram.noteservice.service;

import com.aram.noteservice.dto.NoteCreateDTO;
import com.aram.noteservice.dto.NoteDTO;
import com.aram.noteservice.dto.NoteUpdateDTO;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author aram
 */
public interface NoteService {
    
    Optional<NoteDTO> getNoteById(Long id);
    List<NoteDTO> getAllNotes();
    Optional<NoteDTO> updateNote(NoteUpdateDTO note);
    Optional<NoteDTO> createNote(NoteCreateDTO note);
    boolean deleteNoteById(Long id);
    
}
