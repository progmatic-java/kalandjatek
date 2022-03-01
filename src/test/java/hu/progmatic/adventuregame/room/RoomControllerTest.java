package hu.progmatic.adventuregame.room;

import hu.progmatic.adventuregame.character.CharacterDto;
import hu.progmatic.adventuregame.character.CharacterService;
import hu.progmatic.adventuregame.npc.ActionCommand;
import hu.progmatic.adventuregame.npc.NPCDto;
import hu.progmatic.adventuregame.npc.NPCService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@WithUserDetails("admin")
class RoomControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private RoomService roomService;
  @Autowired
  private CharacterService characterService;
  @Autowired
  private NPCService NPCService;

  @Test
  @DisplayName("The end page appearing")
  void theEndPageTest() throws Exception {
    mockMvc.perform(get("/adventuregame/theend"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("The End")))
        .andExpect(content().string(containsString("THE END...?")));
  }

  @Test
  @DisplayName("Black Hole Inn appearing")
  void innPageTest() throws Exception {
    RoomDto roomDto = roomService.getRoomDtoByName("The Black Hole Inn");
    CharacterDto characterDto = characterService.getCharacterDtoByName("Test orc");

    mockMvc.perform(get("/adventuregame/characterpage/" + characterDto.getId() + "/room/" + roomDto.getId()))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("The Black Hole Inn")))
        .andExpect(content().string(containsString("Test orc")))
        .andExpect(content().string(containsString("Burrows")))
        .andExpect(content().string(containsString("Switcher")))
        .andExpect(content().string(containsString("Beer")))
        .andExpect(content().string(containsString("Knife")));
  }

  @Test
  @DisplayName("Room loading with enemy")
  void enemyInRoomTest() throws Exception {
    RoomDto roomDto = roomService.getRoomDtoByName("Cellar of Black Hole Inn");
    CharacterDto characterDto = characterService.getCharacterDtoByName("Test orc");

    mockMvc.perform(get("/adventuregame/characterpage/" + characterDto.getId() + "/room/" + roomDto.getId()))
        .andDo(print())
        .andExpect(status().isFound());
  }

  @Test
  @DisplayName("Enemy interaction test")
  void enemyInteractionTest() throws Exception {
    RoomDto roomDto = roomService.getRoomDtoByName("Cellar of Black Hole Inn");
    CharacterDto characterDto = characterService.getCharacterDtoByName("Test orc");

    mockMvc.perform(get("/adventuregame/characterpage/" + characterDto.getId() + "/room/" + roomDto.getId() + "/npc/" + roomDto.getNpcDtoList().stream().findAny().orElseThrow().getId()))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("Cellar of Black Hole Inn")))
        .andExpect(content().string(containsString("Test orc")))
        .andExpect(content().string(containsString("Mad dog")))
        .andExpect(content().string(containsString("*Agressive growling")))
        .andExpect(content().string(containsString("YIKES!")));
  }

  @Test
  @DisplayName("Npc next action test")
  void npcNextActionTest() throws Exception {
    RoomDto roomDto = roomService.getRoomDtoByName("The Black Hole Inn");
    CharacterDto characterDto = characterService.getCharacterDtoByName("Test orc");
    NPCDto npcDto = NPCService.getNPCDtoByName("Burrows");
    int actionId = npcDto.getFirstAction().getPlayerAnswers().entrySet().stream()
        .filter(stringIntegerEntry -> stringIntegerEntry.getKey().equals("Hello, I just want a beer!"))
        .mapToInt(Map.Entry::getValue).findAny().orElseThrow();

    mockMvc.perform(get("/adventuregame/characterpage/" + characterDto.getId() + "/room/" + roomDto.getId() + "/npc/" + npcDto.getId() + "/action/" + actionId))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("The Black Hole Inn")))
        .andExpect(content().string(containsString("Test orc")))
        .andExpect(content().string(containsString("Burrows")))
        .andExpect(content().string(containsString("The first beer is on the house! Anything else I can get for you?")))
//        .andExpect(content().string(containsString("I'm actually in search for the last Golden Dragon Egg and I heard it's here somewhere in Thorncall. Is it true?")))
        .andExpect(content().string(containsString("No thanks!")));
  }
}