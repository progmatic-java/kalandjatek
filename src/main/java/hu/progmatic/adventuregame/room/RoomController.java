package hu.progmatic.adventuregame.room;

import hu.progmatic.adventuregame.character.CharacterDto;
import hu.progmatic.adventuregame.character.CharacterService;
import hu.progmatic.adventuregame.inventory.InventoryService;
import hu.progmatic.adventuregame.inventory.ItemDto;
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

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/adventuregame/theend")
    public String theEnd() {
        return "/adventuregame/theend";
    }

    @GetMapping("/adventuregame/characterpage/{characterId}/room/{roomId}")
    public String gameStarter(
            @PathVariable Integer characterId,
            @PathVariable Integer roomId,
            Model model
    ) {
        RoomDto currRoom = roomService.getRoomDtoById(roomId);
        CharacterDto currCharacter = characterService.getCharacterDtoById(characterId);
        if (roomService.roomHasEnemy(roomId)) {
            Integer enemyId = roomService.getEnemyId(roomId);
            return "redirect:/adventuregame/characterpage/" + characterId + "/room/" + roomId + "/npc/" + enemyId;
        }
        setCurrRoom(model, currRoom);
        RoomDto startingRoom = currCharacter.getPlayerRooms().stream().filter(room -> room.getRoomName().equals("The Black Hole Inn")).findFirst().orElseThrow();
        model.addAttribute("startingRoom", startingRoom);
        return getCurrRoomWithCharacter(model, currRoom, currCharacter);
    }

    @GetMapping("/adventuregame/characterpage/{characterId}/room/{roomId}/item/{itemId}")
    public String roomItemToPlayer(
            @PathVariable Integer characterId,
            @PathVariable Integer roomId,
            @PathVariable Integer itemId,
            Model model
    ) {
        RoomDto currRoom = roomService.getRoomDtoById(roomId);
        characterService.moveItemToPlayer(characterId, currRoom.getInventoryId(), itemId);
        CharacterDto currCharacter = characterService.getCharacterDtoById(characterId);
        currRoom = roomService.getRoomDtoById(roomId);
        ItemDto item = inventoryService.getItemDtoById(itemId);

        if(item.getItemName().equals("The Golden Dragon Egg")){
            return "redirect:/adventuregame/theend";
        }
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
        RoomDto currRoom = roomService.getRoomDtoById(roomId);
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
        RoomDto currRoom = roomService.getRoomDtoById(roomId);
        CharacterDto currCharacter = characterService.getCharacterDtoById(characterId);
        ActionCommand currAction = npcService.getNextAction(actionId);
        npcActionHandling(model, npcService.getNPCDtoById(npcId), currAction);
        return getCurrRoomWithCharacter(model, currRoom, currCharacter);
    }

    @GetMapping("/adventuregame/characterpage/{characterId}/room/{roomId}/npc/{npcId}/Action/{actionId}")
    public String actionWithNpc(
            @PathVariable Integer characterId,
            @PathVariable Integer roomId,
            @PathVariable Integer npcId,
            @PathVariable Integer actionId,
            Model model
    ) {
        RoomDto currRoom = roomService.getRoomDtoById(roomId);
        CharacterDto currCharacter = characterService.getCharacterDtoById(characterId);
        ActionCommand currAction = npcService.getActionById(actionId);
        npcActionHandling(model, npcService.getNPCDtoById(npcId), currAction);
        return getCurrRoomWithCharacter(model, currRoom, currCharacter);
    }

    @GetMapping("/adventuregame/characterpage/{characterId}/room/{roomId}/npc/{npcId}/action/{actionId}/item/{itemId}")
    public String npcItemToPlayer(
            @PathVariable Integer characterId,
            @PathVariable Integer roomId,
            @PathVariable Integer npcId,
            @PathVariable Integer actionId,
            @PathVariable Integer itemId
    ) {
        NPCDto currNpc = npcService.getNPCDtoById(npcId);
        characterService.moveItemToPlayer(characterId, currNpc.getInventoryId(), itemId);
        return "redirect:/adventuregame/characterpage/" + characterId + "/room/" + roomId + "/npc/" + npcId + "/Action/" + actionId;
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
        model.addAttribute("friendlyNpc", currNpc.isFriendly());
        model.addAttribute("conversationOver", currAction.getPlayerAnswers().isEmpty());
    }

    private void setCurrRoom(Model model, RoomDto currRoom) {
        model.addAttribute("currRoomItems", currRoom.getItems());
        model.addAttribute("currNpcs", currRoom.getNpcDtoList());
        model.addAttribute("currDoors", currRoom.getAdjacentRooms());
    }

    @ModelAttribute("startingRoom")
    public RoomDto startRoom() {
        return new RoomDto();
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

    @ModelAttribute("friendlyNpc")
    public boolean friendlyNpc() {
        return false;
    }
}
