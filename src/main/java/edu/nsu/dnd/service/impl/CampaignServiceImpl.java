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
    public Campaign create(CampaignRequest request) {
        Campaign campaign = new Campaign();
        campaign.setTitle(request.getTitle());
        campaign.setStatus(CampaignStatus.DRAFT);
        campaignRepository.save(campaign);
        return null;
    }

    @Override
    public Campaign update(Long id, CampaignRequest request) {
        Campaign campaign = get(id);
        campaign.setTitle(request.getTitle());
        campaignRepository.save(campaign);
        return null;
    }

    @Override
    public Page<Campaign> page(Pageable pageable) {
        return null;
    }

    @Override
    public Campaign delete(Long id) {
        Campaign campaign = get(id);
        campaignRepository.delete(campaign);
        return null;
    }

    @Override
    public Campaign start(Long id) {
        Campaign campaign = get(id);
        campaign.setStatus(CampaignStatus.ACTIVE);
        campaignRepository.save(campaign);
        return null;
    }

    @Override
    public Campaign pause(Long id) {
        Campaign campaign = get(id);
        campaign.setStatus(CampaignStatus.PAUSED);
        campaignRepository.save(campaign);
        return null;
    }

    @Override
    public Campaign resume(Long id) {
        Campaign campaign = get(id);
        campaign.setStatus(CampaignStatus.ACTIVE);
        campaignRepository.save(campaign);
        return null;
    }

    @Override
    public Campaign refresh(Long id) {
        Campaign campaign = get(id);
        campaign.setCode(RandomStringUtils.randomNumeric(6));
        campaignRepository.save(campaign);
        return null;
    }

    @Override
    public Campaign complete(Long id) {
        Campaign campaign = get(id);
        campaign.setStatus(CampaignStatus.COMPLETED);
        campaignRepository.save(campaign);
        return null;
    }
}
