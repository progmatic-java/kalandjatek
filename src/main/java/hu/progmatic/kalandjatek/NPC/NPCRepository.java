package hu.progmatic.kalandjatek.NPC;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NPCRepository extends JpaRepository<NPCEntity, Integer> {
    List<NPCEntity> findAllByNameContains(String name);
    List<NPCEntity> findAllByIsItFriendly(Boolean isItFriendly);

}
