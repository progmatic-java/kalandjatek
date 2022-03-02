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

  public String swapActiveItem(Integer characterId, Integer itemId) {
    CharacterEntity character = characterRepository.getById(characterId);
    Item item = character.getInventory().getItems().stream().filter(item1 -> item1.getId().equals(itemId)).findAny().orElseThrow();
    Item activeItem = character.getActiveInventory().getItems().stream().filter(item1 -> item1.getTypeOfItem().equals(item.getTypeOfItem())).findAny().orElseThrow();
    character.getInventory().getItems().remove(item);
    character.getActiveInventory().getItems().remove(activeItem);
    character.getInventory().getItems().add(activeItem);
    character.getActiveInventory().getItems().add(item);
    item.setInventory(character.getActiveInventory());
    activeItem.setInventory(character.getInventory());
    return character.getName() + " swapped their " + activeItem.getItemName() + " to a(n) " + item.getItemName();
  }


  public String fightRound(Integer characterId, Integer npcId) {
    CharacterEntity character = characterRepository.getById(characterId);
    NPC npc = NPCRepository.getById(npcId);
    String combatLog = playerAttack(character, npc);
    if (npc.getHp() >= 0) {
      combatLog += npcAttack(character, npc);
      if (character.getCurrHp() < 1) {
        return combatLog + character.getName() + " suffered a fatal blow.";
      }
      return combatLog;
    }
    return combatLog + npc.getName() + " suffered a fatal blow.";
  }

  private String npcAttack(CharacterEntity character, NPC npc) {
    Integer npcAttack = npc.getAttack();
    Random random = new Random();
    Integer attackRoll = random.nextInt(10) + 1;
    Item shield = character.getActiveInventory().getItems().stream()
        .filter(item -> item.getTypeOfItem().equals(ItemEnum.SHIELD))
        .findFirst()
        .orElseThrow();
    if ((npcAttack + attackRoll) > (character.getDefence() + shield.getDefence())) {
      int damage = npc.getDamage() + random.nextInt(6);
      character.setCurrHp(character.getCurrHp() - damage);
      return npc.getName() + " attacked " + character.getName() + " and dealt " + damage + " damage.\n";
    }
    return npc.getName() + " missed.\n";
  }

  private String playerAttack(CharacterEntity character, NPC npc) {
    Integer playerAttack = character.getAttack();
    Item weapon = character.getActiveInventory().getItems().stream()
        .filter(item -> item.getTypeOfItem().equals(ItemEnum.ATTACK))
        .findFirst()
        .orElseThrow();
    playerAttack += weapon.getAttack();
    Random random = new Random();
    Integer attackRoll = random.nextInt(10) + 1;
    if ((playerAttack + attackRoll) > npc.getDefence()) {
      int damage = weapon.getDamage() + random.nextInt(6);
      if (npc.getHp() - damage < 0) {
        npc.setHp(0);
      } {
        npc.setHp(npc.getHp() - damage);
      }
      return character.getName() + " attacked " + npc.getName() + " and dealt " + damage + " damage.\n";
    }
    return character.getName() + " missed.\n";
  }

  public boolean isNpcDead(Integer npcId) {
    NPC npc = NPCRepository.getById(npcId);
    return npc.getHp() < 1;
  }

  public boolean isPlayerDead(Integer characterId) {
    CharacterEntity character = characterRepository.getById(characterId);
    return character.getCurrHp() < 1;
  }

  public String consumeItem(Integer characterId, Integer itemId, Integer npcId) {
    CharacterEntity character = characterRepository.getById(characterId);
    NPC npc = NPCRepository.getById(npcId);
    Item item = character.getInventory().getItems().stream().filter(item1 -> item1.getId().equals(itemId)).findAny().orElseThrow();
    if (item.getHp() != null) {
      character.getInventory().getItems().remove(item);
      item.setInventory(null);
      return healPlayer(character, item);
    } else {
      if (item.getMp() <= character.getCurrMp()) {
        character.getInventory().getItems().remove(item);
        item.setInventory(null);
        character.setCurrMp(character.getCurrMp() - item.getMp());
        npc.setHp(npc.getHp() - item.getDamage());
        return npc.getHp() > 0 ?
            character.getName() + " used " + item.getItemName() + " and dealt " + item.getDamage() + " damage.\n" :
            character.getName() + " used " + item.getItemName() + " and dealt a fatal blow.\n";
      } else {
        return character.getName() + " does not have enough mana.";
      }
    }
  }

  private String healPlayer(CharacterEntity character, Item item) {
    if (character.getMaxHp() - character.getCurrHp() > item.getHp()) {
      character.setCurrHp(character.getCurrHp() + item.getHp());
      return character.getName() + " healed " + item.getHp() + " amount.";
    } else {
      int healedAmount = character.getMaxHp() - character.getCurrHp();
      character.setCurrHp(character.getMaxHp());
      return character.getName() + " healed " + healedAmount + " amount.";
    }
  }
}
