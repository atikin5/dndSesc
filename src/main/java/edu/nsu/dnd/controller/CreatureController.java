package edu.nsu.dnd.controller;

import edu.nsu.dnd.service.CreatureService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Transactional
@RestController
@CrossOrigin("*")
@RequestMapping("/creature")
public class CreatureController {

    private final CreatureService creatureService;

}
