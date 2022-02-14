package hu.progmatic.adventuregame.room;

import hu.progmatic.adventuregame.inventory.Inventory;
import hu.progmatic.adventuregame.npc.NPC;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Room {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @NotEmpty
  @Column(unique = true)
  private String name;

  private String roomImgRef;

  private String roomAudio;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn
  private Inventory inventory;

  @OneToMany(mappedBy = "npcRoom", cascade = CascadeType.ALL)
  @Builder.Default
  private List<NPC> npcEntities = new ArrayList<>();

  @OneToMany(mappedBy = "room1", cascade = CascadeType.ALL, orphanRemoval = true)
  @Builder.Default
  private List<Door> doors1 = new ArrayList<>();

  @OneToMany(mappedBy = "room2", cascade = CascadeType.ALL, orphanRemoval = true)
  @Builder.Default
  private List<Door> doors2 = new ArrayList<>();
}
