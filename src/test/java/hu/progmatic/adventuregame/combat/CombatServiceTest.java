package hu.progmatic.adventuregame.combat;

import hu.progmatic.adventuregame.character.CharacterDto;
import hu.progmatic.adventuregame.character.CharacterService;
import hu.progmatic.adventuregame.inventory.ItemDto;
import hu.progmatic.adventuregame.inventory.ItemEnum;
import hu.progmatic.adventuregame.npc.NPC;
import hu.progmatic.adventuregame.npc.NPCDto;
import hu.progmatic.adventuregame.npc.NPCService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@WithUserDetails("admin")
class CombatServiceTest {

  @Autowired
  private CharacterService characterService;
  @Autowired
  private CombatService combatService;
  @Autowired
  private NPCService NPCService;


  @Test
  @DisplayName("Swapping the active item of the character")
  void charachterActiveItemSwapTest() {
    CharacterDto testChar = characterService.getCharacterDtoByName("Test reptilian");
    ItemDto testItem = testChar.getAllItems().stream().filter(item -> item.getTypeOfItem().equals(ItemEnum.SHIELD)).findFirst().orElseThrow();

    assertEquals("Chitin shield", testChar.getActiveShield().getItemName());
    assertEquals("Hardened Skin", testItem.getItemName());

    String swapLog = combatService.swapActiveItem(testChar.getId(), testItem.getId());
    testChar = characterService.getCharacterDtoByName("Test reptilian");
    testItem = testChar.getAllItems().stream().filter(item -> item.getTypeOfItem().equals(ItemEnum.SHIELD)).findFirst().orElseThrow();

    assertEquals("Hardened Skin", testChar.getActiveShield().getItemName());
    assertEquals("Chitin shield", testItem.getItemName());
    assertEquals("Test reptilian swapped their Chitin shield to a(n) Hardened Skin", swapLog);
  }

  @Test
  @DisplayName("Consume items test")
  void consumeItemsTest() {
    CharacterDto testChar = characterService.getCharacterDtoByName("Test reptilian");
    ItemDto healItem = testChar.getAllItems().stream().filter(item -> item.getHp() != null).findFirst().orElseThrow();
    ItemDto magicItem = testChar.getAllItems().stream().filter(item -> item.getMp() != null).findFirst().orElseThrow();

    NPCDto testNpc = NPCService.saveNpcReturnDto(NPC.builder().name("Test npc").friendly(false).damage(50).defence(100).attack(100).hp(100).build());

    assertEquals(150, testChar.getCurrHp());
    assertEquals(150, testChar.getCurrMp());
    assertEquals(100, testNpc.getHp());
    assertEquals(20, healItem.getHp());
    assertEquals(20, magicItem.getMp());
    assertEquals(20, magicItem.getDamage());
    assertEquals(3, testChar.getAllItems().size());

    combatService.fightRound(testChar.getId(), testNpc.getId());
    testChar = characterService.getCharacterDtoByName("Test reptilian");
    int damagedHp = testChar.getCurrHp();

    assertTrue(testChar.getMaxHp() > damagedHp);

    String healLog = combatService.consumeItem(testChar.getId(), healItem.getId(), testNpc.getId());
    testChar = characterService.getCharacterDtoByName("Test reptilian");

    assertTrue(testChar.getCurrHp() > damagedHp);
    assertEquals("Test reptilian healed 20 amount.", healLog);
    assertEquals(2, testChar.getAllItems().size());

    String magicLog = combatService.consumeItem(testChar.getId(), magicItem.getId(), testNpc.getId());
    testChar = characterService.getCharacterDtoByName("Test reptilian");
    testNpc = NPCService.getNPCDtoById(testNpc.getId());

    assertEquals("Test reptilian used Acid Spit and dealt 20 damage.\n", magicLog);
    assertEquals(130, testChar.getCurrMp());
    assertEquals(80, testNpc.getHp());
    assertEquals(1, testChar.getAllItems().size());
  }
}