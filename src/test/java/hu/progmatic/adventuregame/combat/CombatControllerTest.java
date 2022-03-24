package hu.progmatic.adventuregame.combat;

import hu.progmatic.adventuregame.character.CharacterDto;
import hu.progmatic.adventuregame.character.CharacterService;
import hu.progmatic.adventuregame.npc.NPCDto;
import hu.progmatic.adventuregame.npc.NPCService;
import hu.progmatic.adventuregame.room.RoomDto;
import hu.progmatic.adventuregame.room.RoomService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
@WithUserDetails("admin")
class CombatControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private CharacterService characterService;

  @Test
  @DisplayName("A combat elkezdődik")
  void combatStart() throws Exception {
    CharacterDto character = characterService.getCharacterDtoByName("Test orc");
    RoomDto roomDto = character.getPlayerRooms().stream().filter(room -> room.getRoomName().equals("Cellar of Black Hole Inn")).findFirst().orElseThrow();
    NPCDto npcDto = roomDto.getNpcDtoList().stream().filter(npc -> npc.getName().equals("Mad dog")).findFirst().orElseThrow();
    mockMvc.perform(get("/adventuregame/characterpage/" + character.getId() + "/room/" + roomDto.getId() + "/npc/" + npcDto.getId() +"/combatstart"))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString(npcDto.getName() + " vs " + character.getCharacterName())));
  }

  @Test
  @DisplayName("Lemegye egy combat kör")
  void combatRound() throws Exception {
    CharacterDto character = characterService.getCharacterDtoByName("Test orc");
    RoomDto roomDto = character.getPlayerRooms().stream().filter(room -> room.getRoomName().equals("Cellar of Black Hole Inn")).findFirst().orElseThrow();
    NPCDto npcDto = roomDto.getNpcDtoList().stream().filter(npc -> npc.getName().equals("Mad dog")).findFirst().orElseThrow();
    mockMvc.perform(get("/adventuregame/characterpage/" + character.getId() + "/room/" + roomDto.getId() + "/npc/" + npcDto.getId() +"/combatround"))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString(npcDto.getName() + " vs " + character.getCharacterName())));
  }
}