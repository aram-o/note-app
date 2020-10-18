package com.aram.noteservice.service.impl;

import com.aram.noteservice.dao.NoteDAO;
import com.aram.noteservice.dao.UserDAO;
import com.aram.noteservice.domain.Note;
import com.aram.noteservice.domain.User;
import com.aram.noteservice.dto.NoteCreateDTO;
import com.aram.noteservice.dto.NoteDTO;
import com.aram.noteservice.dto.NoteUpdateDTO;
import com.aram.noteservice.dto.UserDTO;
import com.aram.noteservice.mapper.NoteMapper;
import com.aram.noteservice.mapper.UserMapper;
import com.aram.noteservice.service.NoteService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author aram
 */
@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private UserDAO userDAO;
    
    @Autowired
    private NoteDAO noteDAO;
    
    @Autowired
    private NoteMapper noteMapper;
    
    @Autowired
    private UserMapper userMapper;
    
    /**
     * Method to get particular note with given id.
     * @param id
     * @return
     */
    @Override
    public Optional<NoteDTO> getNoteById(Long id) {
//        If id is null do not continue.
        if(id == null) {
            return Optional.empty();
        }
        Optional<Note> note = this.noteDAO.getNoteById(id);
//        If note is not present do not continue.
        if(!note.isPresent()) {
            return Optional.empty();
        }
        NoteDTO noteDTO = this._makeNote(note.get());
        return Optional.ofNullable(noteDTO);
    }

    /**
     * Get all notes.
     * @param userEmail
     * @return
     */
    @Override
    public List<NoteDTO> getAllNotesByUserEmail(String userEmail) {
        List<Note> noteList = this.noteDAO.getAllNotesByUserEmail(userEmail);
//        If empty or null do not continue.
        if(noteList == null || noteList.isEmpty()) {
            return new ArrayList<>();
        }
        List<NoteDTO> noteDTOList = noteList.stream().map(note -> this._makeNote(note)).collect(Collectors.toList());
        return noteDTOList;
    }

    /**
     * Update Note.
     * @param noteDTO
     * @return
     */
    @Override
    public Optional<NoteDTO> updateNote(NoteUpdateDTO noteDTO) {
        Note note = this.noteMapper.toNote(noteDTO);
        Optional<Note> savedNote = this.noteDAO.saveNote(note);
//        if savedNote is not present do not continue.
        if(!savedNote.isPresent()) {
            return Optional.empty();
        }
        return Optional.ofNullable(this._makeNote(savedNote.get()));
    }

    /**
     * Create Note.
     * @param noteDTO
     * @return
     */
    @Override
    public Optional<NoteDTO> createNote(NoteCreateDTO noteDTO) {
        Note note = this.noteMapper.toNote(noteDTO);
        Optional<Note> savedNote = this.noteDAO.saveNote(note);
//        if savedNote is not present do not continue.
        if(!savedNote.isPresent()) {
            return Optional.empty();
        }
        return Optional.ofNullable(this._makeNote(savedNote.get()));
    }

    /**
     * Delete Note.
     * @param id
     * @return
     */
    @Override
    public boolean deleteNoteById(Long id) {
//        If id is null do not continue.
        if(id == null) {
            return false;
        }
        return this.noteDAO.deleteById(id);
    }

    private NoteDTO _makeNote(Note note) {
        
        NoteDTO noteDTO = this.noteMapper.toNoteDTO(note);
        
        Optional<User> user = this.userDAO.getUserByEmail(note.getUserEmail());
        if(user.isPresent()) {
            UserDTO userDTO = this.userMapper.toUserDTO(user.get());
            noteDTO.setUser(userDTO);
        }
        return noteDTO;
    }
    
}
