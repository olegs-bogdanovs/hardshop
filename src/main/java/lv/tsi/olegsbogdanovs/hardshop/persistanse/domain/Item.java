package lv.tsi.olegsbogdanovs.hardshop.persistanse.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;
    private String name;

    @ManyToMany
    @JoinTable(name="item_cart",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "cart_id"))
    private Set<Cart> carts = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private Set<ItemParameterValue> itemParameterValues = new HashSet<>();

    @Lob
    private Byte[] image;

    @ManyToOne
    private Category category;





}
