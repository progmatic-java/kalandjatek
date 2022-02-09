package hu.progmatic.adventuregame.room;

import hu.progmatic.adventuregame.character.Character;
import hu.progmatic.adventuregame.character.CharacterService;
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
    model.addAttribute("currRoom", currRoom);
    model.addAttribute("currRoomItems", currRoom.getItems());
    model.addAttribute("currPlayer", characterService.getById(characterId));
    model.addAttribute("currNpcs", currRoom.getNpcEntities());
    model.addAttribute("currDoors", currRoom.getAdjacentRooms());
    return "/adventuregame/room";
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
  public Character currPlayer() {
    return new Character();
  }

}
