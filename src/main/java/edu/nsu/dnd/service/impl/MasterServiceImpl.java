package edu.nsu.dnd.service.impl;

import edu.nsu.dnd.model.dto.MasterRequest;
import edu.nsu.dnd.model.persistent.Master;
import edu.nsu.dnd.repository.MasterRepository;
import edu.nsu.dnd.service.MasterService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MasterServiceImpl implements MasterService {

    private final MasterRepository masterRepository;

    @Override
    public Master get(Long id) {
        return masterRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Master with id " + id + " not found"));
    }

    @Override
    public Master create(MasterRequest request) {
        Master master = new Master();
        master.setUsername(request.getUsername());
        master.setPassword(request.getPassword());
        master.setEmail(request.getEmail());
        Master savedMaster = masterRepository.save(master);
        return savedMaster;
    }

    @Override
    public Page<Master> page(Pageable pageable) {
        return masterRepository.findAll(pageable);
    }
}
