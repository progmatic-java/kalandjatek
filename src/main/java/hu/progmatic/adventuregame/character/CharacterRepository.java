package hu.progmatic.adventuregame.character;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CharacterRepository extends JpaRepository<CharacterEntity, Integer> {
    List<CharacterEntity> findAllByNameContains(String name);
    List<CharacterEntity> findAllByRace(Race race);
    Optional<CharacterEntity> getCharacterByName(String name);
}
