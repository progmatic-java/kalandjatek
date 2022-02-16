package hu.progmatic.adventuregame.character;

import hu.progmatic.adventuregame.inventory.*;
import hu.progmatic.adventuregame.room.RoomService;
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
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private RoomService roomService;


    public CharacterEntity save(CharacterEntity character) {
        return repository.save(character);
    }

    public CharacterEntity getById(Integer id) {
        return repository.getById(id);
    }

    public CharacterDto getCharacterDtoById(Integer id) {
        CharacterEntity entity = repository.getById(id);
        List<ItemDto> items = entity.getInventory().getItems().stream().map(item -> inventoryService.buildItemDto(item)).toList();
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
                .items(items)
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


    @Override
    public void afterPropertiesSet() throws Exception {
        Inventory elf = Inventory.builder().build();
        Inventory orc = Inventory.builder().build();
        Inventory human = Inventory.builder().build();
        Inventory reptilian = Inventory.builder().build();

        Item shield = Item.builder().itemName("Shield").typeOfItem(ItemEnum.SHIELD).description("He protect.").inventory(elf).build();
        Item sword = Item.builder().itemName("Sword").typeOfItem(ItemEnum.ATTACK).description("He attack.").inventory(human).build();
        Item mace = Item.builder().itemName("Mace").typeOfItem(ItemEnum.ATTACK).description("So big.").inventory(orc).build();
        Item rock = Item.builder().itemName("Rock").typeOfItem(ItemEnum.ATTACK).description("Tasty").inventory(reptilian).build();

        elf.getItems().add(shield);
        orc.getItems().add(mace);
        human.getItems().add(sword);
        reptilian.getItems().add(rock);

        if (findAll().isEmpty()) {
            List<CharacterEntity> buildingCharacter = List.of(
                    CharacterEntity
                            .builder()
                            .name("Vallak")
                            .race(Race.ORC)
                            .hp(200)
                            .mp(50)
                            .gold(100)
                            .imgRef("https://i.imgur.com/lXcw2hg.png")
                            .description(Race.ORC.description)
                            .inventory(orc)
                            .build(),
                    CharacterEntity.builder().name("Lyah")
                            .race(Race.ELF)
                            .hp(100)
                            .mp(200)
                            .gold(150)
                            .imgRef("https://i.imgur.com/13NVorA.png")
                            .description(Race.ELF.description)
                            .inventory(elf)
                            .build(),
                    CharacterEntity
                            .builder()
                            .name("Bence")
                            .race(Race.HUMAN)
                            .hp(100)
                            .mp(100)
                            .gold(100)
                            .imgRef("https://i.imgur.com/MQRZudq.png")
                            .description(Race.HUMAN.description)
                            .inventory(human)
                            .build(),
                    CharacterEntity
                            .builder()
                            .name("Mark Zuckerberg")
                            .race(Race.REPTILIAN)
                            .hp(69)
                            .mp(420)
                            .gold(666)
                            .imgRef("https://i.imgur.com/0f45Fce.png")
                            .description(Race.REPTILIAN.description)
                            .inventory(reptilian)
                            .build());
            List<CharacterEntity> characters = saveAll(buildingCharacter);

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

    public List<CharacterEntity> findAll() {
        return repository.findAll();
    }

    public CharacterEntity getResultCharacter(Answer answer) {
        Race characterRace = getResults(answer);
        return CharacterEntity.builder()
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

    public Integer getIdByName(String name) {
        return repository.getCharacterByName(name).orElseThrow().getId();
    }

    public void moveRoomItemtoPlayer(Integer characterId, Integer roomId, Integer itemId) {
        Item roomItem = inventoryService.getItemEntityById(itemId);
        roomService.getRoomEntityById(roomId).getInventory().getItems().remove(roomItem);
        repository.getById(characterId).getInventory().getItems().add(roomItem);
        roomItem.setInventory(repository.getById(characterId).getInventory());
    }
}