package hu.progmatic.room;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public void afterPropertiesSet() throws Exception {
        if (roomRepository.count() == 0) {
            roomRepository.saveAll(rooms);
        }
    }

    public List<RoomEntity> getDoorsToOtherRooms(RoomEntity room) {
        List<DoorEntity> doors = doorRepository.findByRoomsContaining(room);
        List<RoomEntity> nextRooms = doors.stream().map(DoorEntity::getRooms)
            .flatMap(roomEntities -> roomEntities.stream().filter(room1 -> !room1.equals(room)))
            .toList();
        return nextRooms;
    }
}
