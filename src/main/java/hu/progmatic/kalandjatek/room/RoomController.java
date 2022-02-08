package hu.progmatic.kalandjatek.room;

import hu.progmatic.kalandjatek.character.Character;
import hu.progmatic.kalandjatek.character.CharacterService;
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


  @GetMapping("/kalandjatek/room")
  public String roomPage() {
    return "/kalandjatek/room";
  }


  @GetMapping("/kalandjatek/characterpage/{characterId}/room/{roomId}")
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
    return "/kalandjatek/room";
  }

  @ModelAttribute("startingRoom")
  public Room startRoom() {
    return roomService.getByName("Inn");
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
