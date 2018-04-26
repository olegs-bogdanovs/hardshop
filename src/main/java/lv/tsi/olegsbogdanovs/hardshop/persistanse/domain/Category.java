package lv.tsi.olegsbogdanovs.hardshop.persistanse.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    private String desc;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private Set<Item> items;

    @ManyToMany
    @JoinTable(name="category_parameter",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "parameter_id"))

    private Set<Parameter> parameters;



}
