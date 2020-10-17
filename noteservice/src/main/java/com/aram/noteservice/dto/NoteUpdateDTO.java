package com.aram.noteservice.dto;

import java.io.Serializable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author aram
 */
@Data
public class NoteUpdateDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @NotNull(message = "Id is mandatory field")
    @Min(value = 1)
    private Long id;
    
    @NotNull(message = "UserId is mandatory field")
    @Min(value = 1)
    private Long userId;
    
    @Size(max = 50)
    @NotNull(message = "Title is mandatory field")
    private String title;
    
    @Size(max = 1000)
    private String note;
    
}
