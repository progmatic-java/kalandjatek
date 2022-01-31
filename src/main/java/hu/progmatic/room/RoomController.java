package hu.progmatic.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/kalandjatek/room")
    public String roomPage() {
        return "/kalandjatek/room";
    }
}
