package edu.nsu.dnd.model.dto.responses;

import edu.nsu.dnd.model.persistent.Master;
import lombok.Data;

@Data
public class MasterResponse {

    private Long id;
    private String username;
    private String email;

    public MasterResponse(Master master) {
        id = master.getId();
        username = master.getUsername();
        email = master.getEmail();
    }
}
