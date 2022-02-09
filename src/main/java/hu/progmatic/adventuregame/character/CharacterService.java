package hu.progmatic.adventuregame.character;

import hu.progmatic.felhasznalo.UserType;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class CharacterService implements InitializingBean {

  @Autowired
  private CharacterRepository repository;


  public Character save(Character character) {
    return repository.save(character);
  }

  public Character getById(Integer id) {
    return repository.getById(id);
  }

  public CharacterDto getCharacterDtoById(Integer id) {
    Character entity = getById(id);
    return CharacterDto.builder()
        .characterName(entity.getName())
        .id(entity.getId())
        .race(entity.getRace())
        .description(entity.getDescription())
        .hp(entity.getHp())
        .mp(entity.getMp())
        .gold(entity.getGold())
        .imgRef(entity.getImgRef())
        .answer(entity.getAnswer())
        .build();
  }


  @RolesAllowed(UserType.Roles.USER_WRITE_ROLE)
  public void delete(Integer id) {
    repository.deleteById(id);
  }

  public List<Character> saveAll(List<Character> list) {
    return repository.saveAllAndFlush(list);
  }

  public List<Character> findAllByName(String name) {
    return repository.findAllByNameContains(name);
  }

  public List<Character> findAllByRace(Race race) {
    return repository.findAllByRace(race);
  }


  @Override
  public void afterPropertiesSet() throws Exception {
    if (findAll().isEmpty()) {
      saveAll(List.of(
          Character
              .builder()
              .name("Vallak")
              .race(Race.ORC)
              .hp(200)
              .mp(50)
              .gold(100)
              .imgRef("https://i.imgur.com/lXcw2hg.png")
              .description(Race.ORC.description)
              .build(),
          Character.builder().name("Lyah")
              .race(Race.ELF)
              .hp(100)
              .mp(200)
              .gold(150)
              .imgRef("https://i.imgur.com/13NVorA.png")
              .description(Race.ELF.description)
              .build(),
          Character
              .builder()
              .name("Bence")
              .race(Race.HUMAN)
              .hp(100)
              .mp(100)
              .gold(100)
              .imgRef("https://i.imgur.com/MQRZudq.png")
              .description(Race.HUMAN.description)
              .build(),
          Character
              .builder()
              .name("Mark Zuckerberg")
              .race(Race.REPTILIAN)
              .hp(69)
              .mp(420)
              .gold(666)
              .imgRef("https://i.imgur.com/0f45Fce.png")
              .description(Race.REPTILIAN.description)
              .build()));
    }
  }

  public Race getResults(Answer answer) {
    Map<Race, Integer> answerEvaluation = new HashMap<>();
    fillEvaluationMap(answerEvaluation, answer.getRace1());
    fillEvaluationMap(answerEvaluation, answer.getRace2());
    fillEvaluationMap(answerEvaluation, answer.getRace3());
    fillEvaluationMap(answerEvaluation, answer.getRace4());
    fillEvaluationMap(answerEvaluation, answer.getRace5());
    fillEvaluationMap(answerEvaluation, answer.getRace6());
    fillEvaluationMap(answerEvaluation, answer.getRace7());
    fillEvaluationMap(answerEvaluation, answer.getRace8());
    return getMaxKey(answerEvaluation);
  }

  private Race getMaxKey(Map<Race, Integer> answerEvaluation) {
    int maxValue = 0;
    Race maxKey = null;
    for (Map.Entry<Race, Integer> entry : answerEvaluation.entrySet()) {
      if (maxValue < entry.getValue()) {
        maxValue = entry.getValue();
        maxKey = entry.getKey();
      }
    }
    return maxKey;
  }

  private void fillEvaluationMap(Map<Race, Integer> answerEvaluation, Race race) {
    Integer value = answerEvaluation.getOrDefault(race, 0);
    answerEvaluation.put(race, value + 1);
  }

  public List<Character> findAll() {
    return repository.findAll();
  }

  public Character getResultCharacter(Answer answer) {
    Race characterRace = getResults(answer);
    return Character.builder()
        .name(answer.getName())
        .hp(characterRace.hp)
        .mp(characterRace.mp)
        .gold(characterRace.gold)
        .imgRef(characterRace.img)
        .description(characterRace.description)
        .race(characterRace)
        .answer(answer)
        .build();
  }
}