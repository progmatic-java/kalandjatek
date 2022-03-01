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
        return buildCharacterDto(entity);
    }

    private CharacterDto buildCharacterDto(CharacterEntity entity) {
        List<ItemDto> combatItems = entity.getInventory().getItems().stream().filter(item -> item.getTypeOfItem().equals(ItemEnum.ATTACK) || item.getTypeOfItem().equals(ItemEnum.SHIELD)).map(item -> inventoryService.buildItemDto(item)).toList();
        List<ItemDto> consumableItems = entity.getInventory().getItems().stream().filter(item -> item.getTypeOfItem().equals(ItemEnum.CONSUMABLE)).map(item -> inventoryService.buildItemDto(item)).toList();
        List<ItemDto> allItems = entity.getInventory().getItems().stream().map(item -> inventoryService.buildItemDto(item)).toList();
        ItemDto activeWeapon = entity.getActiveInventory().getItems().stream().filter(item -> item.getTypeOfItem().equals(ItemEnum.ATTACK)).map(item -> inventoryService.buildItemDto(item)).findFirst().orElseThrow();
        ItemDto activeShield = entity.getActiveInventory().getItems().stream().filter(item -> item.getTypeOfItem().equals(ItemEnum.SHIELD)).map(item -> inventoryService.buildItemDto(item)).findFirst().orElseThrow();
        return CharacterDto.builder()
            .indexImg(entity.getIndexImg())
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

    @Override
    public void afterPropertiesSet() throws Exception {
        characterRepository.deleteAll();
        if (findAll().isEmpty()) {
            getResultCharacter(
                    Answer.builder()
                            .name("Test elf")
                            .race1(Race.ELF)
                            .race2(Race.ELF)
                            .race3(Race.ELF)
                            .race4(Race.ELF)
                            .race5(Race.ELF)
                            .race6(Race.ELF)
                            .race7(Race.ELF)
                            .race8(Race.ELF)
                            .build()
            );
            getResultCharacter(
                    Answer.builder()
                            .name("Test orc")
                            .race1(Race.ORC)
                            .race2(Race.ORC)
                            .race3(Race.ORC)
                            .race4(Race.ORC)
                            .race5(Race.ORC)
                            .race6(Race.ORC)
                            .race7(Race.ORC)
                            .race8(Race.ORC)
                            .build()
            );
            getResultCharacter(
                    Answer.builder()
                            .name("Test human")
                            .race1(Race.HUMAN)
                            .race2(Race.HUMAN)
                            .race3(Race.HUMAN)
                            .race4(Race.HUMAN)
                            .race5(Race.HUMAN)
                            .race6(Race.HUMAN)
                            .race7(Race.HUMAN)
                            .race8(Race.HUMAN)
                            .build()
            );
            getResultCharacter(
                    Answer.builder()
                            .name("Test reptilian")
                            .race1(Race.REPTILIAN)
                            .race2(Race.REPTILIAN)
                            .race3(Race.REPTILIAN)
                            .race4(Race.REPTILIAN)
                            .race5(Race.REPTILIAN)
                            .race6(Race.REPTILIAN)
                            .race7(Race.REPTILIAN)
                            .race8(Race.REPTILIAN)
                            .build()
            );
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

    public CharacterDto getResultCharacter(Answer answer) {
        Race characterRace = getResults(answer);
        Inventory charInv = Inventory.builder().build();
        characterRace.invItems.stream().map(ItemDto::buildEntity).forEach(charInv::addItem);
        Inventory activeInv = Inventory.builder().build();
        characterRace.activeItems.stream().map(ItemDto::buildEntity).forEach(activeInv::addItem);
        CharacterEntity entity = CharacterEntity.builder()
            .name(answer.getName())
            .maxHp(characterRace.hp)
            .maxMp(characterRace.mp)
            .currMp(characterRace.mp)
            .currHp(characterRace.hp)
            .gold(characterRace.gold)
            .imgRef(characterRace.img)
            .indexImg(characterRace.indexImg)
            .description(characterRace.description)
            .inventory(charInv)
            .activeInventory(activeInv)
            .attack(characterRace.attack)
            .defence(characterRace.defence)
            .race(characterRace)
            .answer(answer)
            .build();
        return buildCharacterDto(characterRepository.save(entity));
    }

    public Integer getIdByName(String name) {
        return characterRepository.getCharacterByName(name).orElseThrow().getId();
    }

    public void moveItemToPlayer(Integer characterId, Integer inventoryId, Integer itemId) {
        Item item = inventoryService.getItemEntityById(itemId);
        Inventory inventory = inventoryService.getInventoryEntityById(inventoryId);
        CharacterEntity character = characterRepository.getById(characterId);

        if (item.getTypeOfItem().equals(ItemEnum.VALUABLE)){
            character.setGold(character.getGold() + item.getValue());
            inventory.getItems().remove(item);
            item.setInventory(null);
            return;
        }
        if (item.getValue() <= character.getGold()) {
            inventory.getItems().remove(item);
            character.getInventory().getItems().add(item);
            item.setInventory(character.getInventory());
            character.setGold(character.getGold() - item.getValue());
        }
    }

    public CharacterDto getCharacterDtoByName(String charName) {
        return buildCharacterDto(characterRepository.getCharacterByName(charName).orElseThrow());
    }
}