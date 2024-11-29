package edu.nsu.dnd.service.impl;

import edu.nsu.dnd.repository.ItemRepository;
import edu.nsu.dnd.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

}
