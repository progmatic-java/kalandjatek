package hu.progmatic.kalandjatek.NPC;

import hu.progmatic.kalandjatek.Inventory;
import hu.progmatic.kalandjatek.room.Room;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NPC {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique=true)
    private String name;
    private String description;
    private Boolean friendly;
    private Integer hp;
    private Integer mp;
    private Integer gold;
    private String imgRef;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn
    private Inventory inventory;
    @ManyToOne(cascade = CascadeType.ALL)
    Room npcRoom;
}
