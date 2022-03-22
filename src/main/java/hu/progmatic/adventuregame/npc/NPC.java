package hu.progmatic.adventuregame.npc;

import hu.progmatic.adventuregame.character.CharacterEntity;
import hu.progmatic.adventuregame.inventory.Inventory;
import hu.progmatic.adventuregame.room.Room;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "name"})})

public class NPC {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;
    private Boolean friendly;
    private Integer hp;
    private Integer mp;
    private Integer gold;
    private String imgRef;
    private Integer attack;
    private Integer damage;
    private Integer defence;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    @Builder.Default
    private Inventory inventory = new Inventory();

    @ManyToOne()
    private Room npcRoom;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    @Builder.Default
    private Action action = new Action();

    @ManyToOne()
    private CharacterEntity player;
}
