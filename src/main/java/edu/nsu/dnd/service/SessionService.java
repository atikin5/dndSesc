package edu.nsu.dnd.service;

import edu.nsu.dnd.model.persistent.Session;

public interface SessionService {

    Session create(Long masterId);
}
