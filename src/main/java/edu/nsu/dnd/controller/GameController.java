package edu.nsu.dnd.controller;

import edu.nsu.dnd.service.GameService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/{sessionId}")
public class GameController {

    private final GameService gameService;

    @PostMapping("/move/{characterId}")
    public Object move(@PathVariable Long sessionId, @PathVariable Long characterId, @RequestBody Object o) {
        return gameService.moveCharacter(sessionId, characterId, 1);
    }
}
