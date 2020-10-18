package com.aram.noteservice.controller;

import com.aram.noteservice.dto.NoteCreateDTO;
import com.aram.noteservice.dto.NoteDTO;
import com.aram.noteservice.dto.NoteUpdateDTO;
import com.aram.noteservice.security.NoteChecker;
import com.aram.noteservice.service.NoteService;
import com.aram.noteservice.util.ResponseUtil;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author aram
 */
@RestController
@RequestMapping("api/v1/")
@Slf4j
public class NoteController {
    
    @Autowired
    private NoteService noteService;
    
    @Autowired
    private NoteChecker noteChecker;
    
    /**
     * List all notes.
     * @param authentication
     * @return
     */
    @RequestMapping(value = "notes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE) 
    public List<NoteDTO> list(Authentication authentication) {
        return this.noteService.getAllNotesByUserEmail(authentication.getName());
    }
    
    /**
     * Create a Note.
     * @param note
     * @param authentication
     * @return
     */
    @RequestMapping(value = "notes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE) 
    @PreAuthorize("@noteChecker.check(#note, authentication)")
    public ResponseEntity<?> create(@RequestBody NoteCreateDTO note, Authentication authentication) {
        return ResponseUtil.wrapOrNotFound(this.noteService.createNote(note));
    }
    
    /**
     * Update the Note.
     * ToDo find a way to check not found before permission checker.
     * @param note
     * @param authentication
     * @return
     */
    @RequestMapping(value = "notes", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE) 
    @PreAuthorize("@noteChecker.check(#note, authentication)")
    public ResponseEntity<?> update(@RequestBody NoteUpdateDTO note, Authentication authentication) {
        return ResponseUtil.wrapOrNotFound(this.noteService.updateNote(note));
    }
    
    /**
     * Get the particular Note by the given id.
     * ToDo find a way to check not found before permission checker.
     * @param id
     * @param authentication
     * @return
     */
    @RequestMapping(value = "notes/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE) 
    @PreAuthorize("@noteChecker.check(#id, authentication)")
    public ResponseEntity<?> get(@PathVariable Long id, Authentication authentication) {
        return ResponseUtil.wrapOrNotFound(this.noteService.getNoteById(id));
    }
    
    /**
     * Delete the particular Note by the given id.
     * ToDo find a way to check not found before permission checker.
     * @param id
     * @param authentication 
     */
    @RequestMapping(value = "notes/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("@noteChecker.check(#id, authentication)")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id, Authentication authentication) {
        this.noteService.deleteNoteById(id);
    }
}
