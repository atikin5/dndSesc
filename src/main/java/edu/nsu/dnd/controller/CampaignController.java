package edu.nsu.dnd.controller;

import edu.nsu.dnd.model.dto.requests.CampaignRequest;
import edu.nsu.dnd.model.dto.responses.CampaignShortResponse;
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
        public Page<CampaignShortResponse> page(Pageable pageable) {
            return campaignService.page(pageable).map(CampaignShortResponse::new);
        }

        @GetMapping("/{id}")
        public CampaignShortResponse get(@PathVariable Long id) {
            return new CampaignShortResponse(campaignService.get(id));
        }

        @PostMapping()
        public CampaignShortResponse create(@RequestBody CampaignRequest request) {
            return new CampaignShortResponse(campaignService.create(request));
        }

        @PutMapping("/{id}")
        public CampaignShortResponse update(@PathVariable Long id, @RequestBody CampaignRequest request) {
            return new CampaignShortResponse(campaignService.update(id, request));
        }

        /**
         * Меняет статус кампании, устанавливает дату начала и генерирует код доступа.
         *
         * @param id идентифиактор компании.
         * @return данные компании.
         */
        @PostMapping("/{id}/start")
        public CampaignShortResponse start(@PathVariable Long id) {
            return new CampaignShortResponse(campaignService.start(id));
        }

        /**
         * Меняет статус кампании, устанавливает дату окончания и удаляет код доступа.
         *
         * @param id идентифиактор компании.
         * @return данные компании.
         */
        @PostMapping("/{id}/complete")
        public CampaignShortResponse complete(@PathVariable Long id) {
            return new CampaignShortResponse(campaignService.complete(id));
        }

        /**
         * Гененрирует новый код доступа.
         *
         * @param id идентифиактор компании.
         * @return данные компании.
         */
        @PostMapping("/{id}/refresh")
        public CampaignShortResponse refresh(@PathVariable Long id) {
            return new CampaignShortResponse(campaignService.refresh(id));
        }

        @ResponseStatus(HttpStatus.NO_CONTENT)
        @DeleteMapping("/{id}")
        public void delete(@PathVariable Long id) {
            campaignService.delete(id);
        }

}
