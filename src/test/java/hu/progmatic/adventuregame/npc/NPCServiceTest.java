package hu.progmatic.adventuregame.npc;

import hu.progmatic.adventuregame.inventory.Inventory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
class NPCServiceTest {

  @Autowired
  private NPCService npcService;

  @Test
  @DisplayName("Create NPC")
  void createNpc() {
    NPC newEntity = NPC.builder().name("Runner").description("The coffee lover of The Black Hole Inn").build();
    npcService.saveNpc(newEntity);
    assertNotNull(newEntity.getId());
  }

  @Test
  @DisplayName("Delete an NPC")
  void deleteCharacter() {
    NPC newEntity = NPC.builder().name("Zs Black").description("Phantom of Progmatchique").build();
    npcService.saveNpc(newEntity);
    assertNotNull(newEntity.getId());
    npcService.delete(newEntity.getId());
    Exception exception = null;
    try {
      npcService.getNPCDtoById(newEntity.getId());
    } catch (Exception e) {
      exception = e;
    }
    assertNotNull(exception);
  }


  @Nested
  @DisplayName("One NPC")
  class NpcExistsTest {
    private NPC npc;

    @BeforeEach
    void setUp() {
      NPC newNpc = NPC.builder()
          .name("Maya")
          .description("The waitress of The Black Hole Inn")
          .inventory(new Inventory())
          .action(Action.builder().build())
          .friendly(true).build();
      npc = npcService.saveNpc(newNpc);
    }

    @AfterEach
    void tearDown() {
      npcService.delete(npc.getId());
    }

    @Test
    @DisplayName("Get NPC by id")
    void getNpc() {
      NPCDto readed = npcService.getNPCDtoById(npc.getId());
      assertNotNull(readed.getId());
      assertEquals("Maya", readed.getName());
      assertEquals("The waitress of The Black Hole Inn", readed.getDescription());
    }

    @Test
    @DisplayName("Update a NPC")
    void updateCharacter() {
      npc.setName("Burrows the Saint");
      npcService.saveNpc(npc);
      NPCDto updated = npcService.getNPCDtoById(npc.getId());
      assertEquals("Burrows the Saint", updated.getName());
    }

  }

  @Test
  @DisplayName("Get action by Id")
  void getActionTest() {
    Action action = npcService.createAction(
        Action.builder()
            .conversationText("Action text")
            .childActions(
                List.of(
                    Action.builder()
                        .conversationText("Child text 1")
                        .build(),
                    Action.builder()
                        .conversationText("Child text 2")
                        .build()
                )
            )
            .build()
    );

    ActionCommand checked = npcService.getActionById(action.getId());
    assertEquals("Action text", checked.getNpcText());
    assertThat(checked.getPlayerAnswers().keySet())
        .hasSize(2)
        .containsExactlyInAnyOrder("Child text 1", "Child text 2");
  }

  @Test
  @DisplayName("Get next action")
  void getNextActionTest() {
    Action action = npcService.createAction(
        Action.builder()
            .conversationText("Player answer")
            .childActions(
                List.of(
                    Action.builder()
                        .conversationText("Npc action")
                        .childActions(
                            List.of(
                                Action.builder()
                                    .conversationText("Child text 1")
                                    .build(),
                                Action.builder()
                                    .conversationText("Child text 2")
                                    .build()
                            )
                        )
                        .build()
                )
            )
            .build()
    );

    ActionCommand checked = npcService.getNextAction(action.getId());
    assertEquals("Npc action", checked.getNpcText());
    assertThat(checked.getPlayerAnswers().keySet())
        .hasSize(2)
        .containsExactlyInAnyOrder("Child text 1", "Child text 2");
  }
}