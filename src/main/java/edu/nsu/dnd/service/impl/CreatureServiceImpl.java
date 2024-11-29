package edu.nsu.dnd.service.impl;

import edu.nsu.dnd.repository.CreatureRepository;
import edu.nsu.dnd.service.CreatureService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CreatureServiceImpl implements CreatureService {

    private final CreatureRepository creatureRepository;
}
