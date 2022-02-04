package hu.progmatic.kalandjatek.room;

import hu.progmatic.kalandjatek.character.CharacterEntity;
import hu.progmatic.kalandjatek.character.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
        model.addAttribute("currRoom", roomService.getRoomById(roomId));
        model.addAttribute("currPlayer", characterService.getById(characterId));
        return "/kalandjatek/room";
    }

    @ModelAttribute("startingRoom")
    public RoomEntity startRoom() {
        return roomService.getByName("Inn");
    }

    @ModelAttribute("currRoom")
    public RoomDto currRoom() {
        return new RoomDto();
    }

    @ModelAttribute("currPlayer")
    public CharacterEntity currPlayer() {
        return new CharacterEntity();
    }
}
