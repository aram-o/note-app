package com.aram.noteservice.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author aram
 */
@Data
public class UserDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
        
    private String email;

    private Date createDate;
    
    private Date updateDate;
    
}
