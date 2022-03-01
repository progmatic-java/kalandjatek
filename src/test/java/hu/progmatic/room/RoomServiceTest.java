package hu.progmatic.room;

import hu.progmatic.adventuregame.npc.NPC;
import hu.progmatic.adventuregame.npc.NPCDto;
import hu.progmatic.adventuregame.npc.NPCService;
import hu.progmatic.adventuregame.room.Door;
import hu.progmatic.adventuregame.room.Room;
import hu.progmatic.adventuregame.room.RoomService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoomServiceTest {

  @Autowired
  private RoomService roomService;

  @Autowired
  private NPCService NPCService;

  @Test
  @DisplayName("Save room test")
  void roomSaveTest() {
    Room savedRoom = roomService.saveRoom(Room.builder()
        .name("Dungeon")
        .build()
    );
    assertThat(savedRoom.getId()).isNotNull();
    assertEquals("Dungeon", savedRoom.getName());
  }

  @Nested
  @DisplayName("Tests with multiple rooms")
  class MultipleRoomsTest {
    List<Room> savedRooms;

    @BeforeEach
    void setUp() {
      roomService.dropAllRoom();
      savedRooms = roomService.saveAllRoom(
          List.of(
              Room.builder().name("Wasteland").build(),
              Room.builder().name("Church").build(),
              Room.builder().name("The Black Hole Inn").build(),
              Room.builder().name("Main square").build(),
              Room.builder().name("Forest").build(),
              Room.builder().name("Inn cellar").build()
          )
      );
    }

    @Test
    @DisplayName("Save multiple room test")
    void roomSaveAllTest() {
      assertThat(savedRooms)
          .isNotNull()
          .hasSize(6)
          .extracting(Room::getName)
          .containsExactlyInAnyOrder("Wasteland", "Church", "The Black Hole Inn", "Main square", "Forest", "Inn cellar");
    }

    @Test
    @DisplayName("Get room by name test")
    void getRoomByNameTest() {
      Room inn = roomService.getByName("The Black Hole Inn");
      assertEquals("The Black Hole Inn", inn.getName());
      Room forest = roomService.getByName("Forest");
      assertEquals("Forest", forest.getName());
    }
  }

  @Test
  @DisplayName("Check if room has an enemy")
  void roomHasEnemyTest() {
    Room testRoom = roomService.saveRoom(
        Room.builder()
            .name("roomHasEnemyTest")
            .build()
    );
    NPC testNpc = NPCService.saveNpc(
        NPC.builder()
            .name("roomHasEnemyTest")
            .friendly(false)
            .npcRoom(testRoom)
            .build()
    );
    testRoom.getNpcEntities().add(testNpc);

    assertTrue(roomService.roomHasEnemy(testRoom.getId()));
  }

  @Test
  @DisplayName("Get enemy id from room")
  void getEnemyByIdTest() {
    Room testRoom = roomService.saveRoom(
        Room.builder()
            .name("getEnemyByIdTest")
            .build()
    );
    NPC testNpc = NPCService.saveNpc(
        NPC.builder()
            .name("getEnemyByIdTest")
            .friendly(false)
            .npcRoom(testRoom)
            .build()
    );
    testRoom.getNpcEntities().add(testNpc);

    Integer enemyId = roomService.getEnemyId(testRoom.getId());
    NPCDto enemyDto = NPCService.getNPCDtoById(enemyId);
    assertEquals("getEnemyByIdTest", enemyDto.getName());
    assertFalse(enemyDto.isFriendly());
  }
}