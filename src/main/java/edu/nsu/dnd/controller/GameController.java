package edu.nsu.dnd.controller;

import edu.nsu.dnd.model.persistent.embeddable.Position;
import edu.nsu.dnd.service.GameService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Transactional
@RestController
@RequestMapping("/{campaignId}")
public class GameController {

    private final GameService gameService;

    @PostMapping("/move/{characterId}")
    public Object move(@PathVariable Long campaignId, @PathVariable Long characterId, @RequestBody List<Position> positions) {
        return gameService.moveCharacter(campaignId, characterId, positions);
    }

}
