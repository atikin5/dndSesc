package edu.nsu.dnd.controller;

import edu.nsu.dnd.model.dto.SessionResponse;
import edu.nsu.dnd.service.SessionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/sessions")
public class SessionController {

    private final SessionService sessionService;

//    @GetMapping()
//    public Page<MasterResponse> page(Pageable pageable) {
//        return masterService.page(pageable).map(MasterResponse::new);
//    }
//
//    @GetMapping("/{id}")
//    public MasterResponse get(@PathVariable Long id) {
//        return new MasterResponse(masterService.get(id));
//    }
//
    @PostMapping("/{masterId}")
    public SessionResponse create(@PathVariable Long masterId) {
        return new SessionResponse(sessionService.create(masterId));
    }
}
