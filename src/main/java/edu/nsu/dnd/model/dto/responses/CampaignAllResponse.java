package edu.nsu.dnd.model.dto.responses;

import edu.nsu.dnd.model.enums.CampaignStatus;
import edu.nsu.dnd.model.persistent.Campaign;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampaignAllResponse {
    private Long id;
    private String code;
    private CampaignStatus status;
    private String title;
    private Date createdAt;
    private Date startedAt;
    private Date completedAt;
    private List<DndCharacterResponse> dndCharacters = new ArrayList<>();
    private List<CreatureResponse> creatures = new ArrayList<>();
    private List<DestructibleObjectResponse> destructibleObjects = new ArrayList<>();
    private List<LocationResponse> locations = new ArrayList<>();

    public CampaignAllResponse(Campaign campaign) {
        id = campaign.getId();
        code = campaign.getCode();
        status = campaign.getStatus();
        title = campaign.getTitle();
        createdAt = campaign.getCreatedAt();
        startedAt = campaign.getStartedAt();
        completedAt = campaign.getCompletedAt();
        dndCharacters = campaign.getDndCharacters().stream().map(DndCharacterResponse::new).collect(Collectors.toList());
        creatures = campaign.getCreatures().stream().map(CreatureResponse::new).collect(Collectors.toList());
        destructibleObjects = campaign.getDestructibleObjects().stream().map(DestructibleObjectResponse::new).collect(Collectors.toList());
        locations = campaign.getLocations().stream().map(LocationResponse::new).collect(Collectors.toList());
    }
}
