package hu.progmatic.adventuregame.init;

import hu.progmatic.adventuregame.character.CharacterEntity;
import hu.progmatic.adventuregame.character.CharacterService;
import hu.progmatic.adventuregame.npc.NPC;
import hu.progmatic.adventuregame.room.Door;
import hu.progmatic.adventuregame.room.Room;
import hu.progmatic.adventuregame.room.RoomRepository;
import hu.progmatic.adventuregame.room.RoomService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class
InitService {
    private final InnInit innInit = new InnInit();
    private final CellarInit cellarInit = new CellarInit();
    private final ChurchInit churchInit = new ChurchInit();
    private final ChurchDungeonsInit churchDungeonsInit = new ChurchDungeonsInit();
    private final MainSquareInit mainSquareInit = new MainSquareInit();
    private final ShopInit shopInit = new ShopInit();
    private final RoadToTheForestInit roadToTheForestInit = new RoadToTheForestInit();
    private final ForestInit forestInit = new ForestInit();
    private final ForestLakeInit forestLakeInit = new ForestLakeInit();
    private final BrothelInit brothelInit = new BrothelInit();
    private final ThePathToTheAcademyInit pathToTheAcademyInit = new ThePathToTheAcademyInit();
    private final AcademyInnit academyInnit = new AcademyInnit();
    private final GraveyardInit graveyardInit = new GraveyardInit();
    private final MountainInnit mountainInnit = new MountainInnit();
    private final CaveInit caveInit = new CaveInit();

    @Autowired
    private RoomRepository roomRepository;


    public CharacterEntity generateNewWorld(CharacterEntity newCharacter) {
        Room inn = createRoom(innInit, newCharacter);
        Room cellar = createRoom(cellarInit, newCharacter);
        Room church = createRoom(churchInit, newCharacter);
        Room churchDungeons = createRoom(churchDungeonsInit, newCharacter);
        Room mainSquare = createRoom(mainSquareInit, newCharacter);
        Room shop = createRoom(shopInit, newCharacter);
        Room roadToTheForest = createRoom(roadToTheForestInit, newCharacter);
        Room forest = createRoom(forestInit, newCharacter);
        Room forestLake = createRoom(forestLakeInit, newCharacter);
        Room brothel = createRoom(brothelInit, newCharacter);
        Room pathToAcademy = createRoom(pathToTheAcademyInit, newCharacter);
        Room academy = createRoom(academyInnit, newCharacter);
        Room graveyard = createRoom(graveyardInit, newCharacter);
        Room mountain = createRoom(mountainInnit, newCharacter);
        Room cave = createRoom(caveInit, newCharacter);

        createDoorBetweenRooms(inn, cellar);
        createDoorBetweenRooms(church, churchDungeons);
        createDoorBetweenRooms(church, graveyard);
        createDoorBetweenRooms(mainSquare, inn);
        createDoorBetweenRooms(mainSquare, church);
        createDoorBetweenRooms(mainSquare, shop);
        createDoorBetweenRooms(mainSquare, brothel);
        createDoorBetweenRooms(mainSquare, roadToTheForest);
        createDoorBetweenRooms(mainSquare, graveyard);
        createDoorBetweenRooms(roadToTheForest, forest);
        createDoorBetweenRooms(forest, forestLake);
        createDoorBetweenRooms(mainSquare, pathToAcademy);
        createDoorBetweenRooms(pathToAcademy, academy);
        createDoorBetweenRooms(forestLake, mountain);
        createDoorBetweenRooms(mountain, cave);

//        roomRepository.saveAll(List.of(inn, cellar, brothel, church, churchDungeons, mainSquare, shop, forest, roadToTheForest, forestLake, pathToAcademy, academy, graveyard, mountain, cave));
        return newCharacter;
    }

    private void createDoorBetweenRooms(Room room1, Room room2) {
        Door innToCellar = Door.builder()
                .room1(room1)
                .room2(room2)
                .build();
        room1.getDoors1().add(innToCellar);
        room2.getDoors2().add(innToCellar);
    }

    private Room createRoom(InitRoom initRoom, CharacterEntity newCharacter) {
        Room room = Room.builder()
                .name(initRoom.getName())
                .roomImgRef(initRoom.getRoomImgRef())
                .inventory(initRoom.getInventory())
                .roomDescription(initRoom.getRoomDescription())
                .player(newCharacter)
                .build();
        newCharacter.getPlayerRooms().add(room);
        addNpcToRoom(initRoom.getNpcs(), room, newCharacter);
        return room;
    }

    private void addNpcToRoom(List<NPC> npcList, Room room, CharacterEntity newCharacter) {
        for (NPC npc : npcList) {
            npc.setNpcRoom(room);
        }
        room.setNpcEntities(npcList);
    }
}
