package edu.nsu.dnd.repository;

import edu.nsu.dnd.model.persistent.DndCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<DndCharacter, Long> {

}
