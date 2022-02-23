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
    npcService.save(newEntity);
    assertNotNull(newEntity.getId());
  }

  @Test
  @DisplayName("Delete an NPC")
  void deleteCharacter() {
    NPC newEntity = NPC.builder().name("Zs Black").description("Phantom of Progmatchique").build();
    npcService.save(newEntity);
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
      npc = npcService.save(newNpc);
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
      npcService.save(npc);
      NPCDto updated = npcService.getNPCDtoById(npc.getId());
      assertEquals("Burrows the Saint", updated.getName());
    }

  }

  @Test
  @DisplayName("Find Burrows")
  void findAllByName() {
    NPC burrow = npcService.findByName("Burrows");
    assertNotNull(burrow.getId());
    assertEquals("Burrows", burrow.getName());

  }
}