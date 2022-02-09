package hu.progmatic.adventuregame.NPC;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NPCRepository extends JpaRepository<NPC, Integer> {
    Optional<NPC> findByName(String name);
    List<NPC> findAllByFriendly(Boolean friendly);


}
