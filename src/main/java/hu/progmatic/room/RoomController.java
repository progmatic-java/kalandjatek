package hu.progmatic.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/kalandjatek/room")
    public String roomPage() {
        return "/kalandjatek/room";
    }


    @ModelAttribute("startingRoom")
    public RoomEntity startRoom() {
        return roomService.getByName("Inn");
    }
}
