package com.aram.noteservice.controller;

import com.aram.noteservice.dto.NoteCreateDTO;
import com.aram.noteservice.dto.NoteDTO;
import com.aram.noteservice.dto.NoteUpdateDTO;
import com.aram.noteservice.service.NoteService;
import com.aram.noteservice.util.ResponseUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
public class NoteController {
    
    @Autowired
    private NoteService noteService;
    
    /**
     * List all notes.
     * @return
     */
    @RequestMapping(value = "notes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE) 
    public List<NoteDTO> list() {
        return this.noteService.getAllNotes();
    }
    
    /**
     * Create a Note.
     * @param note
     * @return
     */
    @RequestMapping(value = "notes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE) 
    public ResponseEntity<?> create(@RequestBody NoteCreateDTO note) {
        return ResponseUtil.wrapOrNotFound(this.noteService.createNote(note));
    }
    
    /**
     * Update the Note.
     * @param note
     * @return
     */
    @RequestMapping(value = "notes", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE) 
    public ResponseEntity<?> update(@RequestBody NoteUpdateDTO note) {
        return ResponseUtil.wrapOrNotFound(this.noteService.updateNote(note));
    }
    
    /**
     * Get the particular Note by the given id.
     * @param id
     * @return
     */
    @RequestMapping(value = "notes/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE) 
    public ResponseEntity<?> get(@PathVariable Long id) {
        return ResponseUtil.wrapOrNotFound(this.noteService.getNoteById(id));
    }
    
    /**
     * Delete the particular Note by the given id.
     * @param id
     */
    @RequestMapping(value = "notes/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE) 
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        this.noteService.deleteNoteById(id);
    }
}
