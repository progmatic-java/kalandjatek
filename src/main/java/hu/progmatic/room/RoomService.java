package hu.progmatic.room;

import hu.progmatic.kalandjatek.InventoryEntity;
import hu.progmatic.kalandjatek.ItemsEntity;
import hu.progmatic.kalandjatek.character.CharacterEntity;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RoomService implements InitializingBean {
  private List<RoomEntity> rooms = List.of(
      RoomEntity.builder().name("Inn").build(),
      RoomEntity.builder().name("Main square").build(),
      RoomEntity.builder().name("Forest").build(),
      RoomEntity.builder().name("Inn cellar").build()
  );

  @Autowired
  private RoomRepository roomRepository;
  @Autowired
  private DoorRepository doorRepository;


  public List<RoomEntity> findAllRooms() {
    return roomRepository.findAll();
  }

  public RoomDto getRoomById(Integer id) {
    return buildRoomDto(roomRepository.getById(id));
  }

  private RoomDto buildRoomDto(RoomEntity roomEntity) {
    return RoomDto.builder()
        .id(roomEntity.getId())
        .roomName(roomEntity.getName())
        .adjacentRooms(new ArrayList<>())
        .characters(getCharactersName(roomEntity.getCharacters()))
        .items(getItemList(roomEntity.getInventory()))
        .build();
  }

  private List<String> getItemList(InventoryEntity inventory) {
//    return inventory.getItems().stream()
//        .map(ItemsEntity::getItemName)
//        .toList();
    return null;
  }

  private List<String> getCharactersName(List<CharacterEntity> characters) {
//    return characters.stream()
//        .map(CharacterEntity::getName)
//        .toList();
    return null;
  }


  public RoomEntity getByName(String name) {
    return roomRepository.getRoomEntityByName(name).orElseThrow();
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    if (roomRepository.count() == 0) {
      roomRepository.saveAll(rooms);
    }
  }

  public RoomEntity saveRoom(RoomEntity room) {
    return roomRepository.save(room);
  }

  public List<RoomEntity> saveAllRoom(List<RoomEntity> rooms) {
    return roomRepository.saveAll(rooms);
  }

  public DoorEntity saveDoor(RoomEntity room1, RoomEntity room2) {
    return doorRepository.save(DoorEntity.builder()
        .room1(room1)
        .room2(room2)
        .build()
    );
  }
}
