package hu.progmatic.room;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoomService implements InitializingBean {
    private List<RoomEntity> rooms = List.of(
            RoomEntity.builder().name("Kocsma").build(),
            RoomEntity.builder().name("Főtér").build(),
            RoomEntity.builder().name("Erdő").build()
    );

    @Autowired
    private RoomRepository roomRepository;

    public List<RoomEntity> findAllRooms() {
        return roomRepository.findAll().stream().toList();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (roomRepository.count() == 0) {
            roomRepository.saveAll(rooms);
        }
    }

}
