package edu.nsu.dnd.service;

import edu.nsu.dnd.model.dto.requests.MasterRequest;
import edu.nsu.dnd.model.persistent.Master;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MasterService {

    Master get(Long id);

    Master create(MasterRequest request);

    Page<Master> page(Pageable pageable);
}
