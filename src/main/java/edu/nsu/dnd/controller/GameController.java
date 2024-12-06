package edu.nsu.dnd.controller;

import edu.nsu.dnd.service.GameService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Transactional
@RestController
@CrossOrigin("*")
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;



}
