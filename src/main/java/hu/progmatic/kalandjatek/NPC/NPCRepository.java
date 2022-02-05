package hu.progmatic.kalandjatek.NPC;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NPCRepository extends JpaRepository<NPCEntity, Integer> {
    Optional<NPCEntity> findByName(String name);
    List<NPCEntity> findAllByFriendly(Boolean friendly);


}
