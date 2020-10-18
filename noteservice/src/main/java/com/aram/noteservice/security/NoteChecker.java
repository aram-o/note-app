package com.aram.noteservice.security;

import com.aram.noteservice.dao.NoteDAO;
import com.aram.noteservice.domain.Note;
import com.aram.noteservice.dto.NoteCreateDTO;
import com.aram.noteservice.dto.NoteUpdateDTO;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 *
 * @author aram
 */
@Component
public class NoteChecker {

    @Autowired
    private NoteDAO noteDAO;

    /**
     * Check note owner.
     * @param note
     * @param authentication
     * @return
     */
    public boolean check(NoteCreateDTO note, Authentication authentication) {
//        If note or authentication is null do not continue.
        if(note == null || authentication == null) {
            return false;
        }
        return note.getUserEmail().equals(authentication.getName());
    }

    /**
     * Check note owner.
     * @param note
     * @param authentication
     * @return
     */
    public boolean check(NoteUpdateDTO note, Authentication authentication) {
//        If note or authentication is null do not continue.
        if(note == null || authentication == null) {
            return false;
        }
        return note.getUserEmail().equals(authentication.getName());
    }

    /**
     * Check note owner by id.
     * @param id
     * @param authentication
     * @return
     */
    public boolean check(Long id, Authentication authentication) {
//        If id or authentication is null do not continue.
        if(id == null || authentication == null) {
            return false;
        }
        Optional<Note> note = this.noteDAO.getNoteById(id);
        if (!note.isPresent() || !note.get().getUserEmail().equals(authentication.getName())) {
            return false;
        }
        return true;
    }

}
