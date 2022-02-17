package hu.progmatic.adventuregame.room;

import hu.progmatic.adventuregame.character.CharacterDto;
import hu.progmatic.adventuregame.character.CharacterService;
import hu.progmatic.adventuregame.npc.ActionCommand;
import hu.progmatic.adventuregame.npc.NPCDto;
import hu.progmatic.adventuregame.npc.NPCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RoomController {

  @Autowired
  private RoomService roomService;

  @Autowired
  private CharacterService characterService;

  @Autowired
  private NPCService npcService;


  @GetMapping("/adventuregame/room")
  public String roomPage() {
    return "/adventuregame/room";
  }

  @GetMapping("/adventuregame/characterpage/{characterId}/room/{roomId}")
  public String gameStarter(
      @PathVariable Integer characterId,
      @PathVariable Integer roomId,
      Model model
  ) {
    RoomDto currRoom = roomService.getRoomById(roomId);
    CharacterDto currCharacter = characterService.getCharacterDtoById(characterId);
    setCurrRoom(model, currRoom);
    return getCurrRoomWithCharacter(model, currRoom, currCharacter);
  }

  @GetMapping("/adventuregame/characterpage/{characterId}/room/{roomId}/item/{itemId}")
  public String roomItemToPlayer(
      @PathVariable Integer characterId,
      @PathVariable Integer roomId,
      @PathVariable Integer itemId,
      Model model
  ) {
    RoomDto currRoom = roomService.getRoomById(roomId);
    CharacterDto currCharacter = characterService.getCharacterDtoById(characterId);
    setCurrRoom(model, currRoom);
    return getCurrRoomWithCharacter(model, currRoom, currCharacter);
  }

  @GetMapping("/adventuregame/characterpage/{characterId}/room/{roomId}/npc/{npcId}")
  public String talkWithNpc(
          @PathVariable Integer characterId,
          @PathVariable Integer roomId,
          @PathVariable Integer npcId,
          Model model
  ) {
    RoomDto currRoom = roomService.getRoomById(roomId);
    CharacterDto currCharacter = characterService.getCharacterDtoById(characterId);
    NPCDto currNpc = npcService.getNPCDtoById(npcId);
    ActionCommand currAction = currNpc.getFirstAction();
    npcActionHandling(model, currNpc, currAction);
    return getCurrRoomWithCharacter(model, currRoom, currCharacter);
  }

  @GetMapping("/adventuregame/characterpage/{characterId}/room/{roomId}/npc/{npcId}/action/{actionId}")
  public String nextActionWithNpc(
          @PathVariable Integer characterId,
          @PathVariable Integer roomId,
          @PathVariable Integer npcId,
          @PathVariable Integer actionId,
          Model model
  ) {
    RoomDto currRoom = roomService.getRoomById(roomId);
    CharacterDto currCharacter = characterService.getCharacterDtoById(characterId);
    ActionCommand currAction = npcService.getNextAction(actionId);
    npcActionHandling(model, npcService.getNPCDtoById(npcId), currAction);
    return getCurrRoomWithCharacter(model, currRoom, currCharacter);
  }

  private String getCurrRoomWithCharacter(Model model, RoomDto currRoom, CharacterDto currCharacter) {
    model.addAttribute("currRoom", currRoom);
    model.addAttribute("currPlayer", currCharacter);
    return "/adventuregame/room";
  }

  private void npcActionHandling(Model model, NPCDto currNpc, ActionCommand currAction) {
    model.addAttribute("currNpc", currNpc);
    model.addAttribute("currNpcAction", currAction);
    model.addAttribute("npcInterraction", true);
    model.addAttribute("conversationOver", currAction.getPlayerAnswers().isEmpty());
  }

  private void setCurrRoom(Model model, RoomDto currRoom) {
    model.addAttribute("currRoomItems", currRoom.getItems());
    model.addAttribute("currNpcs", currRoom.getNpcDtoList());
    model.addAttribute("currDoors", currRoom.getAdjacentRooms());
  }

  @ModelAttribute("startingRoom")
  public Room startRoom() {
    return roomService.getByName("The Black Hole Inn");
  }

  @ModelAttribute("currRoom")
  public RoomDto currRoom() {
    return new RoomDto();
  }

  @ModelAttribute("currPlayer")
  public CharacterDto currPlayer() {
    return new CharacterDto();
  }

  @ModelAttribute("currNpc")
  public NPCDto currNpc() {
    return new NPCDto();
  }

  @ModelAttribute("npcInterraction")
  public boolean npcInterraction() {
    return false;
  }

  @ModelAttribute("conversationOver")
  public boolean conversationOver() {
    return false;
  }
}
