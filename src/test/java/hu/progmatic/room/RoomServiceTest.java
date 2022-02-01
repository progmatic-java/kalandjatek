package hu.progmatic.room;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class RoomServiceTest {

  @Autowired
  private RoomService roomService;

  @Test
  void doorRoomTest() {
    List<RoomEntity> roomEntityList = roomService.findAllRooms();
    RoomEntity inn = getRoom(roomEntityList, "Inn");
    RoomEntity mainSquare = getRoom(roomEntityList, "Main square");
    RoomEntity innCellar = getRoom(roomEntityList, "Forest");
    RoomEntity Forest = getRoom(roomEntityList, "Inn cellar");

    DoorEntity door = roomService.addNewDoor(inn, innCellar);
  }

  private RoomEntity getRoom(List<RoomEntity> roomEntityList, String roomName) {
    return roomEntityList.stream().filter(room -> room.getName().equals(roomName)).findAny().orElseThrow();
  }
}