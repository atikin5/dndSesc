package edu.nsu.dnd.model.dto;

import lombok.Data;

@Data
public class MasterRequest {

    private String username;

    private String password;

    private String email;
}
