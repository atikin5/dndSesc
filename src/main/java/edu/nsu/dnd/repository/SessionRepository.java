package edu.nsu.dnd.repository;

import edu.nsu.dnd.model.persistent.Master;
import edu.nsu.dnd.model.persistent.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    List<Session> findTop20ByMasterOrderByCreateDateDesc(Master master);
    Page<Session> findByMaster(Master master, Pageable pageable);

}
