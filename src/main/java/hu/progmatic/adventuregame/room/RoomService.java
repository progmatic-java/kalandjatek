package hu.progmatic.adventuregame.room;

import hu.progmatic.adventuregame.Inventory;
import hu.progmatic.adventuregame.ItemDto;
import hu.progmatic.adventuregame.Item;
import hu.progmatic.adventuregame.NPC.NPC;
import hu.progmatic.adventuregame.NPC.NPCRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class RoomService implements InitializingBean {
    private List<Room> rooms = List.of(
            Room.builder().name("Inn").build(),
            Room.builder().name("Main square").build(),
            Room.builder().name("Forest").build(),
            Room.builder().name("Inn cellar").build()
    );

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private DoorRepository doorRepository;
    @Autowired
    private NPCRepository NPCRepository;

    public List<Room> findAllRooms() {
        return roomRepository.findAll();
    }

    public List<NPC> getNpcEntities() {
        return NPCRepository.findAll();
    }

    public RoomDto getRoomById(Integer id) {
        return buildRoomDto(roomRepository.getById(id));
    }

    private RoomDto buildRoomDto(Room room) {
        return RoomDto.builder()
                .id(room.getId())
                .roomName(room.getName())
                .roomImgRef(room.getRoomImgRef())
                .adjacentRooms(getAdjacentRooms(room))
                .npcEntities(getNpcsName(room.getNpcEntities()))
                .items(getItemList(room.getInventory()))
                .build();
    }

    private List<String> getItemList(Inventory inventory) {
        return inventory.getItems().stream()
                .map(Item::getItemName)
                .toList();
    }

    private ItemDto buildItemDto(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .itemName(item.getItemName())
                .description(item.getDescription())
                .typeOfItem(item.getTypeOfItem())
//        .inventoryId(itemEntity.getInventory().getId())
                .build();
    }

    private List<String> getNpcsName(List<NPC> npcEntities) {
        return npcEntities.stream()
                .map(NPC::getName)
                .toList();
    }

    public Room getByName(String name) {
        return roomRepository.getRoomEntityByName(name).orElseThrow();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
//    if (roomRepository.count() == 0) {
//      roomRepository.saveAll(rooms);
//    }
    }

    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    public List<Room> saveAllRoom(List<Room> rooms) {
        return roomRepository.saveAll(rooms);
    }

    public Door saveDoor(Room room1, Room room2) {
        Door newDoor = doorRepository.save(Door.builder()
                .room1(room1)
                .room2(room2)
                .build()
        );
        room1.getDoors1().add(newDoor);
        room1.getDoors2().add(newDoor);
        return newDoor;
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
}
