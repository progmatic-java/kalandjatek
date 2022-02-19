package hu.progmatic.adventuregame.combat;

import hu.progmatic.adventuregame.character.CharacterDto;
import hu.progmatic.adventuregame.character.CharacterService;
import hu.progmatic.adventuregame.npc.ActionCommand;
import hu.progmatic.adventuregame.npc.NPCDto;
import hu.progmatic.adventuregame.npc.NPCService;
import hu.progmatic.adventuregame.room.RoomDto;
import hu.progmatic.adventuregame.room.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CombatController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private CharacterService characterService;

    @Autowired
    private NPCService npcService;

    @GetMapping("/adventuregame/combat")
    public String combat() {
        return "/adventuregame/combat";
    }

    @GetMapping("/adventuregame/characterpage/{characterId}/room/{roomId}/npc/{npcId}/changecombatitem/{itemId}")
    public String characterItemToActionItem(
            @PathVariable Integer characterId,
            @PathVariable Integer roomId,
            @PathVariable Integer itemId,
            @PathVariable Integer npcId,
            Model model
    ) {
        RoomDto currRoom = roomService.getRoomById(roomId);
        characterService.moveItemtoPlayer(characterId, currRoom.getInventoryId(), itemId);
        CharacterDto currCharacter = characterService.getCharacterDtoById(characterId);
        currRoom = roomService.getRoomById(roomId);
        NPCDto currNpc = npcService.getNPCDtoById(npcId);
        setCurrRoom(model, currRoom);
        return setCurrCombatRound(model, currRoom, currCharacter, currNpc);
    }

    @GetMapping("/adventuregame/characterpage/{characterId}/room/{roomId}/npc/{npcId}/combatstart")
    public String combatStart(
            @PathVariable Integer characterId,
            @PathVariable Integer roomId,
            @PathVariable Integer npcId,
            Model model
    ) {
        RoomDto currRoom = roomService.getRoomById(roomId);
        CharacterDto currCharacter = characterService.getCharacterDtoById(characterId);
        NPCDto currNpc = npcService.getNPCDtoById(npcId);
        return setCurrCombatRound(model, currRoom, currCharacter, currNpc);
    }

    private String setCurrCombatRound(Model model, RoomDto currRoom, CharacterDto currCharacter, NPCDto currNpc) {
        model.addAttribute("currRoom", currRoom);
        model.addAttribute("currPlayer", currCharacter);
        model.addAttribute("currNpc", currNpc);
        return "/adventuregame/combat";
    }

    private void setCurrRoom(Model model, RoomDto currRoom) {
        model.addAttribute("currRoomItems", currRoom.getItems());
        model.addAttribute("currNpcs", currRoom.getNpcDtoList());
        model.addAttribute("currDoors", currRoom.getAdjacentRooms());
    }
}
