package edu.nsu.dnd.model.dto.responses;

import edu.nsu.dnd.model.enums.CampaignStatus;
import edu.nsu.dnd.model.persistent.Campaign;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampaignShortResponse {
    private Long id;
    private String code;
    private CampaignStatus status;
    private String title;
    private Date createdAt;
    private Date startedAt;
    private Date completedAt;

    public CampaignShortResponse(Campaign campaign) {
        id = campaign.getId();
        code = campaign.getCode();
        status = campaign.getStatus();
        title = campaign.getTitle();
        createdAt = campaign.getCreatedAt();
        startedAt = campaign.getStartedAt();
        completedAt = campaign.getCompletedAt();
    }
}
