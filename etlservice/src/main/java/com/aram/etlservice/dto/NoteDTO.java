package com.aram.etlservice.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author aram
 */
@Data
public class NoteDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    
    private String user_email;
    
    private String title;
    
    private String note;

    private Date createDate;
    
    private Date updateDate;
    
}
