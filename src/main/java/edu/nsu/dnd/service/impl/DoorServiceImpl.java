package edu.nsu.dnd.service.impl;

import edu.nsu.dnd.repository.DoorRepository;
import edu.nsu.dnd.service.DoorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DoorServiceImpl implements DoorService {

    private final DoorRepository doorRepository;
}
