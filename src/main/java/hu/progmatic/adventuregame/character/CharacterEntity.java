package hu.progmatic.adventuregame.character;

import hu.progmatic.adventuregame.inventory.Inventory;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CharacterEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String name;
  @Enumerated(EnumType.STRING)
  Race race;
  private String description;
  private Integer hp;
  private Integer mp;
  private Integer gold;
  private Integer attack;
  private Integer defence;
  private String imgRef;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn
  Answer answer;
  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn
  Inventory inventory;
  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn
  Inventory currentInventory;

}
