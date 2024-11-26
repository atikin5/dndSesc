package edu.nsu.dnd.repository;

import edu.nsu.dnd.model.persistent.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
