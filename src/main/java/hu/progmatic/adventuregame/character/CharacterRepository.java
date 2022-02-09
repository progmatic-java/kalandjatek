package hu.progmatic.adventuregame.character;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CharacterRepository extends JpaRepository<Character, Integer> {
    List<Character> findAllByNameContains(String name);
    List<Character> findAllByRace(Race race);
    Optional<Character> findByRace(Race race);
}
