package hu.progmatic.adventuregame.npc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActionCommand {
    private Integer currActionId;
    private String npcText;
    private Map<String, Integer> playerAnswers;
}
