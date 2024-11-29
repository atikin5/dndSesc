package edu.nsu.dnd.service.impl;

import edu.nsu.dnd.repository.WallRepository;
import edu.nsu.dnd.service.WallService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class WallServiceImpl implements WallService {

    private final WallRepository wallRepository;
}
