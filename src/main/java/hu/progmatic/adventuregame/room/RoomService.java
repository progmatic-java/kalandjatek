package hu.progmatic.adventuregame.room;

import hu.progmatic.adventuregame.inventory.Inventory;
import hu.progmatic.adventuregame.inventory.InventoryService;
import hu.progmatic.adventuregame.inventory.ItemDto;
import hu.progmatic.adventuregame.npc.NPC;
import hu.progmatic.adventuregame.npc.NPCDto;
import hu.progmatic.adventuregame.npc.NPCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class RoomService {

  @Autowired
  private RoomRepository roomRepository;
  @Autowired
  private InventoryService inventoryService;
  @Autowired
  private NPCService NPCService;


  public RoomDto getRoomById(Integer id) {
    return buildRoomDto(roomRepository.getById(id));
  }

  private RoomDto buildRoomDto(Room room) {
    return RoomDto.builder()
        .id(room.getId())
        .roomName(room.getName())
        .roomImgRef(room.getRoomImgRef())
        .roomAudio(room.getRoomAudio())
        .roomDescription(room.getRoomDescription())
        .inventoryId(room.getInventory().getId())
        .adjacentRooms(getAdjacentRooms(room))
        .npcDtoList(getNPCDtoList(room.getNpcEntities()))
        .items(getItemList(room.getInventory()))
        .build();
  }

  private List<ItemDto> getItemList(Inventory inventory) {
    return inventory.getItems().stream()
        .map(item -> inventoryService.buildItemDto(item))
        .toList();
  }

  private List<NPCDto> getNPCDtoList(List<NPC> npcEntities) {
    return npcEntities.stream()
        .map(npc -> NPCService.buildNpcDto(npc))
        .toList();
  }

  public Room getByName(String name) {
    return roomRepository.getRoomEntityByName(name).orElseThrow();
  }

  public Room saveRoom(Room room) {
    return roomRepository.save(room);
  }

  public List<Room> saveAllRoom(List<Room> rooms) {
    return roomRepository.saveAll(rooms);
  }

  public Map<String, Integer> getAdjacentRooms(Room room) {
    List<Door> allDoors = room.getDoors1();
    allDoors.addAll(room.getDoors2());
    List<Room> nextRooms = allDoors.stream()
        .map(door -> getOtherRoom(door, room))
        .toList();
    return getRoomMap(nextRooms);
  }

  private Map<String, Integer> getRoomMap(List<Room> nextRooms) {
    Map<String, Integer> roomMap = new HashMap<>();
    for (Room room : nextRooms) {
      roomMap.put(room.getName(), room.getId());
    }
    return roomMap;
  }

  private Room getOtherRoom(Door door, Room room) {
    if (door.getRoom1().equals(room)) {
      return door.getRoom2();
    } else {
      return door.getRoom1();
    }
  }

  public void dropAllRoom() {
    roomRepository.deleteAll();
  }

  public Room getRoomEntityById(Integer id) {
    return roomRepository.getById(id);
  }
}
