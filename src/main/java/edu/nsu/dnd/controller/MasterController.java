package edu.nsu.dnd.controller;

import edu.nsu.dnd.model.dto.MasterRequest;
import edu.nsu.dnd.model.dto.MasterResponse;
import edu.nsu.dnd.service.MasterService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/masters")
public class MasterController {

    private final MasterService masterService;

    @GetMapping()
    public Page<MasterResponse> page(Pageable pageable) {
        return masterService.page(pageable).map(MasterResponse::new);
    }

    @GetMapping("/{id}")
    public MasterResponse get(@PathVariable Long id) {
        return new MasterResponse(masterService.get(id));
    }

    @PostMapping()
    public MasterResponse create(@RequestBody MasterRequest request) {
        return new MasterResponse(masterService.create(request));
    }
}
