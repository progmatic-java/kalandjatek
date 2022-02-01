package hu.progmatic.room;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoorRepository extends JpaRepository<DoorEntity, Integer> {
  List<DoorEntity> findByRoomsContaining(RoomEntity room);
}
