package edu.nsu.dnd.model.dto.requests;

import lombok.Data;

@Data
public class MasterRequest {

    private String username;
    private String password;
    private String email;
}
