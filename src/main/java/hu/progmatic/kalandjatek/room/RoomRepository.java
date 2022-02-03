package hu.progmatic.kalandjatek.room;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RoomRepository extends JpaRepository<RoomEntity, Integer> {
    Optional<RoomEntity> getRoomEntityByName(String name);
}
