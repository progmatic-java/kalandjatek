package hu.progmatic.room;

import hu.progmatic.kalandjatek.room.DoorEntity;
import hu.progmatic.kalandjatek.room.RoomEntity;
import hu.progmatic.kalandjatek.room.RoomService;
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
    RoomEntity savedRoom = roomService.saveRoom(RoomEntity.builder()
        .name("Dungeon")
        .build()
    );
    assertThat(savedRoom.getId()).isNotNull();
    assertEquals("Dungeon", savedRoom.getName());
  }

  @Nested
  @DisplayName("Tests with multiple rooms")
  class MultipleRoomsTest {
    List<RoomEntity> savedRooms;

    @BeforeEach
    void setUp() {
      roomService.dropAllRoom();
      savedRooms = roomService.saveAllRoom(
          List.of(
              RoomEntity.builder().name("Wasteland").build(),
              RoomEntity.builder().name("Church").build(),
              RoomEntity.builder().name("Inn").build(),
              RoomEntity.builder().name("Main square").build(),
              RoomEntity.builder().name("Forest").build(),
              RoomEntity.builder().name("Inn cellar").build()
          )
      );
    }

    @Test
    @DisplayName("Save multiple room test")
    void roomSaveAllTest() {
      assertThat(savedRooms)
          .isNotNull()
          .hasSize(6)
          .extracting(RoomEntity::getName)
          .containsExactlyInAnyOrder("Wasteland", "Church", "Inn", "Main square", "Forest", "Inn cellar");
    }

    @Test
    @DisplayName("Get room by name test")
    void getRoomByNameTest() {
      RoomEntity inn = roomService.getByName("Inn");
      assertEquals("Inn", inn.getName());
      RoomEntity forest = roomService.getByName("Forest");
      assertEquals("Forest", forest.getName());
    }

    @Test
    @DisplayName("Add door test")
    void addDoorTest() {
      RoomEntity inn = roomService.getByName("Inn");
      RoomEntity innCellar = roomService.getByName("Inn cellar");
      DoorEntity cellarDoor = roomService.saveDoor(inn, innCellar);

      assertThat(cellarDoor.getId()).isNotNull();
      assertEquals("Inn", cellarDoor.getRoom1().getName());
      assertEquals("Inn cellar", cellarDoor.getRoom2().getName());
    }
  }
}