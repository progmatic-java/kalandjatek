package hu.progmatic.kalandjatek.init;

import hu.progmatic.kalandjatek.Inventory;
import hu.progmatic.kalandjatek.Item;
import hu.progmatic.kalandjatek.ItemEnum;
import hu.progmatic.kalandjatek.NPC.NPC;
import hu.progmatic.kalandjatek.room.Door;
import hu.progmatic.kalandjatek.room.Room;
import hu.progmatic.kalandjatek.room.RoomRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class InitService implements InitializingBean {
  private final InnInit innInit = new InnInit();
  private final CellarInit cellarInit = new CellarInit();

  @Autowired
  private RoomRepository roomRepository;

  @Override
  public void afterPropertiesSet() throws Exception {
    Room inn = createRoom(innInit);
    Room cellar = createRoom(cellarInit);

    createDoorBetweenRooms(inn, cellar);

    roomRepository.saveAll(List.of(inn, cellar));
  }

  private void createDoorBetweenRooms(Room room1, Room room2) {
    Door innToCellar = Door.builder().room1(room1).room2(room2).build();
    room1.getDoors1().add(innToCellar);
    room2.getDoors2().add(innToCellar);
  }

  private Room createRoom(InitRoom initRoom) {
    Room room = Room.builder().name(initRoom.getName()).roomImgRef(initRoom.getRoomImgRef()).inventory(initRoom.getInventory()).build();
    addNpcToRoom(initRoom.getNpcs(), room);
    return room;
  }

  private void addNpcToRoom(List<NPC> npcList, Room room) {
    for (NPC npc : npcList) {
      npc.setNpcRoom(room);
    }
    room.setNpcEntities(npcList);
  }
}
