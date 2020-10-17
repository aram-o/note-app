package com.aram.noteservice.mapper;

import com.aram.noteservice.domain.Note;
import com.aram.noteservice.domain.User;
import com.aram.noteservice.dto.NoteDTO;
import com.aram.noteservice.dto.UserDTO;
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
public class UserMapper {
    
    private final DozerBeanMapper mapper;
    
    public UserMapper() {
        List<String> mappingFiles = new ArrayList();
        mappingFiles.add("dozerJdk8Converters.xml");

        this.mapper = new DozerBeanMapper();
        this.mapper.setMappingFiles(mappingFiles);
    }

    /**
     * Map User to UserDTO.
     * @param user
     * @return
     */
    public UserDTO toUserDTO(User user) {
        UserDTO dto = this.mapper.map(user, UserDTO.class);
        return dto;
    }
    
}
