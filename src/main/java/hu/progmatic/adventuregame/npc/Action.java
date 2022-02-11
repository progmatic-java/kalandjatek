package hu.progmatic.adventuregame.npc;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String conversationText;
    @OneToMany(cascade = CascadeType.ALL)
    @Builder.Default
    private List<Action> childActions = new ArrayList<>();
}
