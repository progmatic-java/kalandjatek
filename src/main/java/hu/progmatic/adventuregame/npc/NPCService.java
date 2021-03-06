package hu.progmatic.adventuregame.npc;


import hu.progmatic.adventuregame.inventory.InventoryService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class NPCService {

    @Autowired
    private NPCRepository npcRepository;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private ActionRepository actionRepository;


    public NPC saveNpc(NPC npc) {
        return npcRepository.save(npc);
    }

    public NPCDto saveNpcReturnDto(NPC npc) {
        return buildNpcDto(saveNpc(npc));
    }

    public NPC getNpcById(Integer id) {
        return npcRepository.getById(id);
    }

    public NPCDto getNPCDtoById(Integer id) {
        NPC entity = getNpcById(id);
        return buildNpcDto(entity);
    }

    public NPCDto buildNpcDto(NPC entity) {
        return NPCDto.builder()
                .name(entity.getName())
                .id(entity.getId())
                .description(entity.getDescription())
                .friendly(entity.getFriendly())
                .hp(entity.getHp())
                .mp(entity.getMp())
                .gold(entity.getGold())
                .imgRef(entity.getImgRef())
                .attack(entity.getAttack())
                .defence(entity.getDefence())
                .damage(entity.getDamage())
                .inventoryId(entity.getInventory().getId())
                .items(entity.getInventory().getItems().stream()
                        .map(item -> inventoryService.buildItemDto(item))
                        .toList())
                .firstAction(buildNpcAction(entity.getAction()))
                .build();
    }

    private ActionCommand buildNpcAction(Action action) {
        String npcText = action.getConversationText();
        List<Action> answers = action.getChildActions();
        return ActionCommand.builder()
                .currActionId(action.getId())
                .npcText(npcText)
                .playerAnswers(getPlayerAnswers(answers))
                .build();
    }

    private Map<String, Integer> getPlayerAnswers(List<Action> answers) {
        Map<String, Integer> playerAnswers = new HashMap<>();

        for (Action action : answers) {
            playerAnswers.put(action.getConversationText(), action.getId());
        }
        return playerAnswers;
    }

    public void delete(Integer id) {
        npcRepository.deleteById(id);
    }

    public Action createAction(Action action) {
        return actionRepository.save(action);
    }

    public ActionCommand getNextAction(Integer actionId) {
        Action action = actionRepository.getById(actionId).getChildActions()
            .stream()
            .findFirst()
            .orElseGet(Action::new);
        return buildNpcAction(action);
    }

    public ActionCommand getActionById(Integer actionId) {
        Action action = actionRepository.getById(actionId);
        return buildNpcAction(action);
    }
}