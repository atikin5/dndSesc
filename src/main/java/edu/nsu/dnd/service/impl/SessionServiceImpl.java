package edu.nsu.dnd.service.impl;

import edu.nsu.dnd.model.persistent.Session;
import edu.nsu.dnd.repository.MasterRepository;
import edu.nsu.dnd.repository.SessionRepository;
import edu.nsu.dnd.service.SessionService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@AllArgsConstructor
@Service
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;
    private final MasterRepository masterRepository;

    @Override
    public Session create(Long masterId) {
        Session session = new Session();
        session.setCode(RandomStringUtils.randomNumeric(6));
        session.setState("DRAFT");
        session.setCreateDate(new Date());
        session.setMaster(masterRepository.getReferenceById(masterId));
        return sessionRepository.save(session);
    }
}
