package hu.progmatic.kalandjatek.room;

import hu.progmatic.kalandjatek.InventoryEntity;
import hu.progmatic.kalandjatek.InventoryService;
import hu.progmatic.kalandjatek.ItemDto;
import hu.progmatic.kalandjatek.ItemEntity;
import hu.progmatic.kalandjatek.NPC.NPCEntity;
import hu.progmatic.kalandjatek.NPC.NPCRepository;
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
    @Autowired
    private NPCRepository NPCRepository;

    public List<RoomEntity> findAllRooms() {
        return roomRepository.findAll();
    }

    public List<NPCEntity> getNpcEntities() {
        return NPCRepository.findAll();
    }

    public RoomDto getRoomById(Integer id) {
        return buildRoomDto(roomRepository.getById(id));
    }

    private RoomDto buildRoomDto(RoomEntity roomEntity) {
        return RoomDto.builder()
                .id(roomEntity.getId())
                .roomName(roomEntity.getName())
                .adjacentRooms(new ArrayList<>())
                .npcEntities(getNpcsName(roomEntity.getNpcEntities()))
                .items(getItemList(roomEntity.getInventory()))
                .build();
    }

    private List<String> getItemList(InventoryEntity inventory) {
        return inventory.getItems().stream()
                .map(ItemEntity::getItemName)
                .toList();
    }

    private ItemDto buildItemDto(ItemEntity itemEntity) {
        return ItemDto.builder()
                .id(itemEntity.getId())
                .itemName(itemEntity.getItemName())
                .description(itemEntity.getDescription())
                .typeOfItem(itemEntity.getTypeOfItem())
//        .inventoryId(itemEntity.getInventory().getId())
                .build();
    }

    private List<String> getNpcsName(List<NPCEntity> npcEntities) {
//        return npcEntities.stream()
//                .map(NPCEntity::getName)
//                .toList();
        return null;
    }

    public RoomEntity getByName(String name) {
        return roomRepository.getRoomEntityByName(name).orElseThrow();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
//    if (roomRepository.count() == 0) {
//      roomRepository.saveAll(rooms);
//    }
    }

    public RoomEntity saveRoom(RoomEntity room) {
        return roomRepository.save(room);
    }

    public List<RoomEntity> saveAllRoom(List<RoomEntity> rooms) {
        return roomRepository.saveAll(rooms);
    }

    public DoorEntity saveDoor(RoomEntity room1, RoomEntity room2) {
        DoorEntity newDoor = doorRepository.save(DoorEntity.builder()
                .room1(room1)
                .room2(room2)
                .build()
        );
        room1.getDoors1().add(newDoor);
        room1.getDoors2().add(newDoor);
        return newDoor;
    }

    public void dropAllRoom() {
        roomRepository.deleteAll();
    }
}
