package hu.progmatic.adventuregame.npc;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActionRepository extends JpaRepository<Action, Integer> {
}
