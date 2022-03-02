package hu.progmatic.adventuregame.combat;

import hu.progmatic.adventuregame.character.CharacterDto;
import hu.progmatic.adventuregame.character.CharacterService;
import hu.progmatic.adventuregame.inventory.ItemDto;
import hu.progmatic.adventuregame.inventory.ItemEnum;
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
}