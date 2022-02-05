package hu.progmatic.kalandjatek.NPC;

import hu.progmatic.kalandjatek.InventoryEntity;
import hu.progmatic.kalandjatek.room.RoomEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NPCEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String name;
    String description;
    Boolean isItFriendly;
    Integer hp;
    Integer mp;
    Integer gold;
    String imgRef;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn
    InventoryEntity inventory;
    @ManyToOne(cascade = CascadeType.ALL)
    RoomEntity npcRoom;
}
