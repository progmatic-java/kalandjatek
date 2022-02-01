package hu.progmatic.room;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoomServiceTest {

  @Autowired
  private RoomService roomService;

  @Test
  void doorRoomTest() {
    List<RoomEntity> roomEntityList = roomService.findAllRooms();
    RoomEntity
  }
}