package hu.progmatic.kalandjatek.room;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RoomRepository extends JpaRepository<Room, Integer> {
    Optional<Room> getRoomEntityByName(String name);
}
