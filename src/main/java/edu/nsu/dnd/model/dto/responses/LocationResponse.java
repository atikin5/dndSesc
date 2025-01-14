package edu.nsu.dnd.model.dto.responses;

import edu.nsu.dnd.model.persistent.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationResponse {
    private Long id;
    private String name;

    public LocationResponse(Location location) {
        id = location.getId();
        name = location.getName();
    }
}
