package hu.progmatic.room;

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
              Room.builder().name("Inn").build(),
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
          .containsExactlyInAnyOrder("Wasteland", "Church", "Inn", "Main square", "Forest", "Inn cellar");
    }

    @Test
    @DisplayName("Get room by name test")
    void getRoomByNameTest() {
      Room inn = roomService.getByName("Inn");
      assertEquals("Inn", inn.getName());
      Room forest = roomService.getByName("Forest");
      assertEquals("Forest", forest.getName());
    }

    @Test
    @Disabled
    @DisplayName("Add door test")
    void addDoorTest() {
      Room inn = roomService.getByName("Inn");
      Room innCellar = roomService.getByName("Inn cellar");
      Door cellarDoor = roomService.saveDoor(inn, innCellar);

      assertThat(cellarDoor.getId()).isNotNull();
      assertEquals("Inn", cellarDoor.getRoom1().getName());
      assertEquals("Inn cellar", cellarDoor.getRoom2().getName());
    }
  }
}