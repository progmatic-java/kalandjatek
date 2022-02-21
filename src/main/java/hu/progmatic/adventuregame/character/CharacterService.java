package hu.progmatic.adventuregame.character;

import hu.progmatic.adventuregame.inventory.*;
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
    private CharacterRepository characterRepository;
    @Autowired
    private InventoryService inventoryService;


    public CharacterEntity save(CharacterEntity character) {
        return characterRepository.save(character);
    }

    public CharacterEntity getById(Integer id) {
        return characterRepository.getById(id);
    }

    public CharacterDto getCharacterDtoById(Integer id) {
        CharacterEntity entity = characterRepository.getById(id);
        List<ItemDto> combatItems = entity.getInventory().getItems().stream().filter(item -> item.getTypeOfItem().equals(ItemEnum.ATTACK) || item.getTypeOfItem().equals(ItemEnum.SHIELD)).map(item -> inventoryService.buildItemDto(item)).toList();
        List<ItemDto> consumableItems = entity.getInventory().getItems().stream().filter(item -> item.getTypeOfItem().equals(ItemEnum.CONSUMABLE)).map(item -> inventoryService.buildItemDto(item)).toList();
        List<ItemDto> allItems = entity.getInventory().getItems().stream().map(item -> inventoryService.buildItemDto(item)).toList();
        ItemDto activeWeapon = entity.getActiveInventory().getItems().stream().filter(item -> item.getTypeOfItem().equals(ItemEnum.ATTACK)).map(item -> inventoryService.buildItemDto(item)).findFirst().orElseThrow();
        ItemDto activeShield = entity.getActiveInventory().getItems().stream().filter(item -> item.getTypeOfItem().equals(ItemEnum.SHIELD)).map(item -> inventoryService.buildItemDto(item)).findFirst().orElseThrow();
        return CharacterDto.builder()
                .characterName(entity.getName())
                .id(entity.getId())
                .race(entity.getRace())
                .description(entity.getDescription())
                .maxHp(entity.getMaxHp())
                .maxMp(entity.getMaxMp())
                .currHp(entity.getCurrHp())
                .currMp(entity.getCurrMp())
                .attack(entity.getAttack())
                .defence(entity.getDefence())
                .gold(entity.getGold())
                .imgRef(entity.getImgRef())
                .answer(entity.getAnswer())
                .allItems(allItems)
                .combatItems(combatItems)
                .consumableItems(consumableItems)
                .activeWeapon(activeWeapon)
                .activeShield(activeShield)
                .build();
    }


    @RolesAllowed(UserType.Roles.USER_WRITE_ROLE)
    public void delete(Integer id) {
        characterRepository.deleteById(id);
    }

    public List<CharacterEntity> saveAll(List<CharacterEntity> list) {
        return characterRepository.saveAllAndFlush(list);
    }

    public List<CharacterEntity> findAllByName(String name) {
        return characterRepository.findAllByNameContains(name);
    }

    public List<CharacterEntity> findAllByRace(Race race) {
        return characterRepository.findAllByRace(race);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        Inventory elf = Inventory.builder().build();
        Inventory orc = Inventory.builder().build();
        Inventory human = Inventory.builder().build();
        Inventory reptilian = Inventory.builder().build();

        Item shield = Item.builder().itemName("Shield").typeOfItem(ItemEnum.SHIELD).description("He protect.").inventory(elf).build();
        Item shieldOrc = Item.builder().itemName("Shield").typeOfItem(ItemEnum.SHIELD).description("He protect.").inventory(orc).defence(5).build();
        Item sword = Item.builder().itemName("Sword").typeOfItem(ItemEnum.ATTACK).description("He attack.").inventory(human).build();
        Item mace = Item.builder().itemName("Mace").typeOfItem(ItemEnum.ATTACK).description("So big.").inventory(orc).attack(2).damage(10).build();
        Item rock = Item.builder().itemName("Rock").typeOfItem(ItemEnum.ATTACK).description("Tasty").inventory(reptilian).build();

        elf.getItems().add(shield);
        orc.getItems().add(mace);
        orc.getItems().add(shieldOrc);
        human.getItems().add(sword);
        reptilian.getItems().add(rock);

        if (findAll().isEmpty()) {
            List<CharacterEntity> buildingCharacter = List.of(
                    CharacterEntity
                            .builder()
                            .name("Vallak")
                            .race(Race.ORC)
                            .maxHp(200)
                            .maxMp(50)
                            .gold(100)
                            .attack(15)
                            .defence(10)
                            .currHp(200)
                            .currMp(50)
                            .imgRef("https://i.imgur.com/lXcw2hg.png")
                            .description(Race.ORC.description)
                            .inventory(Inventory.builder().build())
                            .activeInventory(orc)
                            .build(),
                    CharacterEntity.builder().name("Lyah")
                            .race(Race.ELF)
                            .maxHp(100)
                            .maxMp(200)
                            .gold(200)
                            .attack(8)
                            .defence(20)
                            .currHp(100)
                            .currMp(200)
                            .imgRef("https://i.imgur.com/13NVorA.png")
                            .description(Race.ELF.description)
                            .inventory(elf)
                            .build(),
                    CharacterEntity
                            .builder()
                            .name("Bence")
                            .race(Race.HUMAN)
                            .maxHp(150)
                            .maxMp(100)
                            .gold(200)
                            .attack(10)
                            .defence(16)
                            .currHp(150)
                            .currMp(100)
                            .imgRef("https://i.imgur.com/MQRZudq.png")
                            .description(Race.HUMAN.description)
                            .inventory(human)
                            .build(),
                    CharacterEntity
                            .builder()
                            .name("Mark Zuckerberg")
                            .race(Race.REPTILIAN)
                            .maxHp(150)
                            .maxMp(150)
                            .gold(200)
                            .attack(12)
                            .defence(13)
                            .currHp(150)
                            .currMp(150)
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
        return characterRepository.findAll();
    }

    public CharacterEntity getResultCharacter(Answer answer) {
        Inventory charInv = Inventory.builder().build();
        List<Item> startingItems = List.of(
                Item.builder().itemName("Sword").typeOfItem(ItemEnum.ATTACK).description("He attack.").inventory(charInv).build(),
                Item.builder().itemName("Shield").typeOfItem(ItemEnum.SHIELD).description("He protect.").inventory(charInv).build(),
                Item.builder().itemName("Potion").typeOfItem(ItemEnum.CONSUMABLE).description("He heals.").inventory(charInv).build()
        );
        charInv.setItems(startingItems);
        Race characterRace = getResults(answer);
        return CharacterEntity.builder()
                .name(answer.getName())
                .maxHp(characterRace.hp)
                .maxMp(characterRace.mp)
                .currMp(characterRace.mp)
                .currHp(characterRace.hp)
                .gold(characterRace.gold)
                .imgRef(characterRace.img)
                .description(characterRace.description)
                .inventory(charInv)
                .attack(characterRace.attack)
                .defence(characterRace.defence)
                .race(characterRace)
                .answer(answer)
                .build();
    }

    public Integer getIdByName(String name) {
        return characterRepository.getCharacterByName(name).orElseThrow().getId();
    }

    public void moveItemToPlayer(Integer characterId, Integer inventoryId, Integer itemId) {
        Item item = inventoryService.getItemEntityById(itemId);
        Inventory inventory = inventoryService.getInventoryEntityById(inventoryId);
        CharacterEntity character = characterRepository.getById(characterId);

        if (item.getValue() > character.getGold()) {
            return;
        }
        inventory.getItems().remove(item);
        character.getInventory().getItems().add(item);
        item.setInventory(character.getInventory());
        character.setGold(character.getGold() - item.getValue());
    }
}