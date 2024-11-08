package edu.nsu.dnd.model.dto;

import edu.nsu.dnd.model.persistent.Session;
import lombok.Data;

import java.util.Date;

@Data
public class SessionResponse {

    private Long id;
    private String code;
    private String state;
    private Date createDate;
    private Date startDate;

    public SessionResponse(Session session) {
        id = session.getId();
        code = session.getCode();
        state = session.getState();
        createDate = session.getCreateDate();
        startDate = session.getStartDate();
    }
}
