package com.aram.noteservice.mapper;

import com.aram.noteservice.domain.Note;
import com.aram.noteservice.dto.NoteCreateDTO;
import com.aram.noteservice.dto.NoteDTO;
import com.aram.noteservice.dto.NoteUpdateDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;

/**
 *
 * @author aram
 */
@Component
@Slf4j
public class NoteMapper {
    
    private final DozerBeanMapper mapper;
    
    public NoteMapper() {
        List<String> mappingFiles = new ArrayList();
        mappingFiles.add("dozerJdk8Converters.xml");

        this.mapper = new DozerBeanMapper();
        this.mapper.setMappingFiles(mappingFiles);
    }

    /**
     * Map Note to NoteDTO.
     * @param note
     * @return
     */
    public NoteDTO toNoteDTO(Note note) {
        NoteDTO dto = this.mapper.map(note, NoteDTO.class);
        return dto;
    }
    
    /**
     * Map NoteUpdateDTO to Note.
     * @param noteDTO
     * @return
     */
    public Note toNote(NoteUpdateDTO noteDTO) {
        Note note = this.mapper.map(noteDTO, Note.class);
        return note;
    }
    
    /**
     * Map NoteCreateDTO to Note.
     * @param noteDTO
     * @return
     */
    public Note toNote(NoteCreateDTO noteDTO) {
        Note note = this.mapper.map(noteDTO, Note.class);
        return note;
    }
}
