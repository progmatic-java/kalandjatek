package hu.progmatic.adventuregame.init;

import hu.progmatic.adventuregame.npc.NPC;
import hu.progmatic.adventuregame.room.Door;
import hu.progmatic.adventuregame.room.Room;
import hu.progmatic.adventuregame.room.RoomRepository;
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
  private final ChurchInit churchInit = new ChurchInit();
  private final ChurchDungeonsInit churchDungeonsInit = new ChurchDungeonsInit();
  private final MainSquareInit mainSquareInit = new MainSquareInit();
  private final ShopInit shopInit = new ShopInit();
  private final RoadToTheForestInit roadToTheForestInit = new RoadToTheForestInit();
  private final ForestInit forestInit = new ForestInit();
  private final ForestLakeInit forestLakeInit = new ForestLakeInit();

  @Autowired
  private RoomRepository roomRepository;

  @Override
  public void afterPropertiesSet() throws Exception {
    Room inn = createRoom(innInit);
    Room cellar = createRoom(cellarInit);
    Room church = createRoom(churchInit);
    Room churchDungeons = createRoom(churchDungeonsInit);
    Room mainSquare = createRoom(mainSquareInit);
    Room shop = createRoom(shopInit);
    Room roadToTheForest = createRoom(roadToTheForestInit);
    Room forest = createRoom(forestInit);
    Room forestLake = createRoom(forestLakeInit);

    createDoorBetweenRooms(inn, cellar);
    createDoorBetweenRooms(church, churchDungeons);
    createDoorBetweenRooms(mainSquare, inn);
    createDoorBetweenRooms(mainSquare, church);
    createDoorBetweenRooms(mainSquare, shop);
    createDoorBetweenRooms(mainSquare, roadToTheForest);
    createDoorBetweenRooms(roadToTheForest, forest);
    createDoorBetweenRooms(forest, forestLake);

    roomRepository.saveAll(List.of(inn, cellar, church, churchDungeons, mainSquare, shop, forest, roadToTheForest, forestLake));
  }

  private void createDoorBetweenRooms(Room room1, Room room2) {
    Door innToCellar = Door.builder()
        .room1(room1)
        .room2(room2)
        .build();
    room1.getDoors1().add(innToCellar);
    room2.getDoors2().add(innToCellar);
  }

  private Room createRoom(InitRoom initRoom) {
    Room room = Room.builder()
        .name(initRoom.getName())
        .roomImgRef(initRoom.getRoomImgRef())
        .inventory(initRoom.getInventory())
        .build();
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
