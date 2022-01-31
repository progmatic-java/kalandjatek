package hu.progmatic.kalandjatek.character;

import hu.progmatic.kozos.felhasznalo.UserType;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class CharacterService implements InitializingBean {

    @Autowired
    private CharacterRepository repository;

    public CharacterEntity save(CharacterEntity character) {
        return repository.save(character);
    }

    public CharacterEntity getById(Integer id) {
        return repository.getById(id);
    }

    public CharacterDto getCharacterDtoById(Integer id) {
        CharacterEntity entity = getById(id);
        return CharacterDto.builder()
            .characterName(entity.getName())
            .id(entity.getId())
            .race(entity.getRace())
            .build();
    }


    @RolesAllowed(UserType.Roles.USER_WRITE_ROLE)
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public List<CharacterEntity> saveAll(List<CharacterEntity> list) {
        return repository.saveAllAndFlush(list);
    }

    public List<CharacterEntity> findAllByName(String name) {
        return repository.findAllByNameContains(name);
    }

    public List<CharacterEntity> findAllByRace(Race race) {
        return repository.findAllByRace(race);
    }

    public CharacterEntity characterToEntity(Character character) {
        return CharacterEntity.builder()
                .name(character.getName())
                .race(character.getRace())
                .description(character.getDescription())
                .hp(character.getHp())
                .mp(character.getMp())
                .gold(character.getGold())
                .imgRef(character.getImgRef())
                .build();
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        if (findAll().isEmpty()) {
            saveAll(List.of(
                    CharacterEntity
                            .builder()
                            .name("Vallak")
                            .race(Race.ORC)
                            .hp(200)
                            .mp(50)
                            .gold(100)
                            .imgRef("https://i.imgur.com/nAKPlhw.png")
                            .build(),
                    CharacterEntity.builder().name("Lyah")
                            .race(Race.ELF)
                            .hp(100)
                            .mp(200)
                            .gold(150)
                            .imgRef("https://i.imgur.com/13NVorA.png")
                            .build(),
                    CharacterEntity
                            .builder()
                            .name("Bence")
                            .race(Race.HUMAN)
                            .hp(100)
                            .mp(100)
                            .gold(100)
                            .imgRef("https://i.imgur.com/MQRZudq.png")
                            .build(),
                    CharacterEntity
                            .builder()
                            .name("Mark Zuckerberg")
                            .race(Race.REPTILIAN)
                            .hp(69)
                            .mp(420)
                            .gold(666)
                            .imgRef("https://i.imgur.com/0f45Fce.png")
                            .build()));
        }
    }

    public List<CharacterEntity> findAll() {
        return repository.findAll();
    }
}