package com.aram.noteservice.dto;

import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author aram
 */
@Data
public class NoteCreateDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @NotNull(message = "userEmail is mandatory field")
    @Email
    private String userEmail;
    
    @Size(max = 50)
    @NotNull(message = "Title is mandatory field")
    private String title;
    
    @Size(max = 1000)
    private String note;
    
}
