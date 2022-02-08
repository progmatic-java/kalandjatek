package hu.progmatic.kalandjatek.room;

import hu.progmatic.kalandjatek.Inventory;
import hu.progmatic.kalandjatek.NPC.NPC;
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

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn
  private Inventory inventory;

//  @OneToMany(mappedBy = "charRoom", cascade = CascadeType.ALL)
//  @Builder.Default
//  private List<CharacterEntity> characters = new ArrayList<>();

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
