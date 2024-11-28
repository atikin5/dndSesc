package edu.nsu.dnd.controller;

import edu.nsu.dnd.model.dto.requests.CampaignRequest;
import edu.nsu.dnd.model.dto.responses.CampaignResponse;
import edu.nsu.dnd.service.CampaignService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Transactional
@RestController
@CrossOrigin("*")
@RequestMapping("/campaign")
public class CampaignController {

    private final CampaignService campaignService;

    @GetMapping()
        public Page<CampaignResponse> page(Pageable pageable) {
            return campaignService.page(pageable).map(CampaignResponse::new);
        }

        @GetMapping("/{id}")
        public CampaignResponse get(@PathVariable Long id) {
            return new CampaignResponse(campaignService.get(id));
        }

        @PostMapping()
        public CampaignResponse create(@RequestBody CampaignRequest request) {
            return new CampaignResponse(campaignService.create(request));
        }

        @PutMapping("/{id}")
        public CampaignResponse update(@PathVariable Long id, @RequestBody CampaignRequest request) {
            return new CampaignResponse(campaignService.update(id, request));
        }

        /**
         * Меняет статус кампании, устанавливает дату начала и генерирует код доступа.
         *
         * @param id идентифиактор компании.
         * @return данные компании.
         */
        @PostMapping("/{id}/start")
        public CampaignResponse start(@PathVariable Long id) {
            return new CampaignResponse(campaignService.start(id));
        }

        /**
         * Меняет статус кампании, устанавливает дату окончания и удаляет код доступа.
         *
         * @param id идентифиактор компании.
         * @return данные компании.
         */
        @PostMapping("/{id}/complete")
        public CampaignResponse complete(@PathVariable Long id) {
            return new CampaignResponse(campaignService.complete(id));
        }

        /**
         * Гененрирует новый код доступа.
         *
         * @param id идентифиактор компании.
         * @return данные компании.
         */
        @PostMapping("/{id}/refresh")
        public CampaignResponse refresh(@PathVariable Long id) {
            return new CampaignResponse(campaignService.refresh(id));
        }

        @ResponseStatus(HttpStatus.NO_CONTENT)
        @DeleteMapping("/{id}")
        public void delete(@PathVariable Long id) {
            campaignService.delete(id);
        }

}
