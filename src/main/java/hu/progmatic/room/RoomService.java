package hu.progmatic.room;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoomService implements InitializingBean {
    private List<RoomEntity> rooms = List.of(
            RoomEntity.builder().name("Kocsma").door(new DoorEntity()).build(),
            RoomEntity.builder().name("Főtér").door(new DoorEntity()).build(),
            RoomEntity.builder().name("Erdő").door(new DoorEntity()).build()
    );

    @Override
    public void afterPropertiesSet() throws Exception {
    }
}
