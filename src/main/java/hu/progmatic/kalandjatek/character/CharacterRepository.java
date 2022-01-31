package hu.progmatic.kalandjatek.character;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharacterRepository extends JpaRepository<CharacterEntity, Integer> {
    List<CharacterEntity> findAllByNameContains(String name);
    List<CharacterEntity> findAllByRace(Race race);
}
