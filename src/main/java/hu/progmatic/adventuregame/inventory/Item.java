package hu.progmatic.adventuregame.inventory;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String itemName;
    private String description;
    @Builder.Default
    private Integer value = 0;
    @Enumerated(EnumType.STRING)
    private ItemEnum typeOfItem;
    @ManyToOne
    private Inventory inventory;

}
