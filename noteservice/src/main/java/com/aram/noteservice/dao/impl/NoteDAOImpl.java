package com.aram.noteservice.dao.impl;

import com.aram.noteservice.dao.NoteDAO;
import com.aram.noteservice.domain.Note;
import com.aram.noteservice.repository.NoteRepository;
import java.util.Date;
import java.util.List;
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
public class NoteDAOImpl implements NoteDAO {
    
    @Autowired
    private NoteRepository noteRepository;
    
    /**
     * Method to return particular note object by given id.
     * @param id
     * @return
     */
    @Override
    public Optional<Note> getNoteById(Long id) {
//        If id is null do not continue.
        if( id == null ) {
            return Optional.empty();
        }
        
        Optional<Note> note = this.noteRepository.findById(id);
        return note;
    }

    /**
     * Method to return all notes.
     * @return
     */
    @Override
    public List<Note> getAllNotes() {
        List<Note> noteList = this.noteRepository.findAll();
        return noteList;
    }

    /**
     * Save the particular note and return saved one if success.
     * @param note
     * @return
     */
    @Override
    public Optional<Note> saveNote(Note note) {
        
//        If note is null do not continue.
        if( note == null ) {
            return Optional.empty();
        }
        
        Date date = new Date();
        if(note.getId() == null) {
            note.setCreateDate(date);
        }
        note.setUpdateDate(date);
        
        Note savedNote = this.noteRepository.saveAndFlush(note);
        return Optional.ofNullable(savedNote);
    }

    /**
     * Remove the note by the given id.
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(Long id) {
//        If id is null do not continue.
        if( id == null ) {
            return false;
        }
        this.noteRepository.deleteById(id);
        return true;
    }
    
}
