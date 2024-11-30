package edu.nsu.dnd.service.impl;

import edu.nsu.dnd.model.dto.requests.CampaignRequest;
import edu.nsu.dnd.model.enums.CampaignStatus;
import edu.nsu.dnd.model.persistent.Campaign;
import edu.nsu.dnd.repository.CampaignRepository;
import edu.nsu.dnd.service.CampaignService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@AllArgsConstructor
@Service
public class CampaignServiceImpl implements CampaignService {

    private final CampaignRepository campaignRepository;

    @Override
    public Campaign get(Long id) {
        return campaignRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Campaign with id " + id + " not found"));
    }

    @Override
    public Campaign create(CampaignRequest campaignRequest) {
        return campaignRepository.save(new Campaign(campaignRequest.getTitle()));
    }

    @Override
    public Campaign update(Long id, CampaignRequest campaignRequest) {
        Campaign campaign = get(id);
        campaign.setTitle(campaignRequest.getTitle());
        return campaignRepository.save(campaign);
    }

    @Override
    public Page<Campaign> page(Pageable pageable) {
        return campaignRepository.findAll(pageable);
    }

    @Override
    public void delete(Long id) {
        Campaign campaign = get(id);
        campaignRepository.delete(campaign);
    }

    @Override
    public Campaign start(Long id) {
        Campaign campaign = get(id);
        campaign.setStatus(CampaignStatus.ACTIVE);
        campaign.setStartedAt(new Date());
        campaign.setCode(RandomStringUtils.randomNumeric(6));
        return campaignRepository.save(campaign);
    }

    @Override
    public Campaign pause(Long id) {
        Campaign campaign = get(id);
        campaign.setStatus(CampaignStatus.PAUSED);
        return campaignRepository.save(campaign);
    }

    @Override
    public Campaign resume(Long id) {
        Campaign campaign = get(id);
        campaign.setStatus(CampaignStatus.ACTIVE);
        return campaignRepository.save(campaign);
    }

    @Override
    public Campaign refresh(Long id) {
        Campaign campaign = get(id);
        campaign.setCode(RandomStringUtils.randomNumeric(6));
        return campaignRepository.save(campaign);
    }

    @Override
    public Campaign complete(Long id) {
        Campaign campaign = get(id);
        campaign.setStatus(CampaignStatus.COMPLETED);
        campaign.setCompletedAt(new Date());
        return campaignRepository.save(campaign);
    }
}
