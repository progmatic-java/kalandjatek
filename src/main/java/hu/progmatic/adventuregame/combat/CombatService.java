package hu.progmatic.adventuregame.combat;

import hu.progmatic.adventuregame.character.CharacterEntity;
import hu.progmatic.adventuregame.character.CharacterRepository;
import hu.progmatic.adventuregame.inventory.Item;
import hu.progmatic.adventuregame.inventory.ItemEnum;
import hu.progmatic.adventuregame.npc.NPC;
import hu.progmatic.adventuregame.npc.NPCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Random;

@Transactional
@Service
public class CombatService {

    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private NPCRepository NPCRepository;


    public void swapActiveItem(Integer characterId, Integer itemId) {
        CharacterEntity character = characterRepository.getById(characterId);
        Item item = character.getInventory().getItems().stream().filter(item1 -> item1.getId().equals(itemId)).findAny().orElseThrow();
        if (character.getActiveInventory().getItems().isEmpty()) {
            character.getInventory().getItems().remove(item);
            character.getActiveInventory().getItems().add(item);
            item.setInventory(character.getActiveInventory());
        } else {
            Item activeItem = character.getActiveInventory().getItems().stream().filter(item1 -> item1.getTypeOfItem().equals(item.getTypeOfItem())).findAny().orElseThrow();
            character.getInventory().getItems().remove(item);
            character.getActiveInventory().getItems().remove(activeItem);
            character.getInventory().getItems().add(activeItem);
            character.getActiveInventory().getItems().add(item);
            item.setInventory(character.getActiveInventory());
            activeItem.setInventory(character.getInventory());
        }
    }


    public void fightRound(Integer characterId, Integer npcId) {
        CharacterEntity character = characterRepository.getById(characterId);
        NPC npc = NPCRepository.getById(npcId);

        playerAttack(character, npc);
        if (npc.getHp() > 0) {
            npcAttack(character, npc);
        }

    }

    private void npcAttack(CharacterEntity character, NPC npc) {
        Integer npcAttack = npc.getAttack();
        Random random = new Random();
        Integer attackRoll = random.nextInt(10) + 1;
        Item shield = character.getActiveInventory().getItems().stream()
                .filter(item -> item.getTypeOfItem().equals(ItemEnum.SHIELD))
                .findFirst()
                .orElseThrow();
        if ((npcAttack + attackRoll) > (character.getDefence() + shield.getDefence())) {
            character.setCurrHp(character.getCurrHp() - npc.getDamage());
        }
    }

    private void playerAttack(CharacterEntity character, NPC npc) {
        Integer playerAttack = character.getAttack();

        Item weapon = character.getActiveInventory().getItems().stream()
                .filter(item -> item.getTypeOfItem().equals(ItemEnum.ATTACK))
                .findFirst()
                .orElseThrow();

        playerAttack += weapon.getAttack();
        Random random = new Random();
        Integer attackRoll = random.nextInt(10) + 1;
        if ((playerAttack + attackRoll) > npc.getDefence()) {
            npc.setHp(npc.getHp() - weapon.getDamage());
        }
    }
}
