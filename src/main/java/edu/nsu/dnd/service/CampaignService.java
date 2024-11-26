package edu.nsu.dnd.service;

import edu.nsu.dnd.model.dto.requests.CampaignRequest;
import edu.nsu.dnd.model.persistent.Campaign;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CampaignService {

    Campaign get(Long id);

    Campaign create(CampaignRequest campaignRequest);

    Campaign update(Long id, CampaignRequest campaignRequest);

    Page<Campaign> page(Pageable pageable);

    void delete(Long id);

    Campaign start(Long id);

    Campaign pause(Long id);

    Campaign resume(Long id);

    Campaign refresh(Long id);

    Campaign complete(Long id);
}
