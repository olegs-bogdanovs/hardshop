package lv.tsi.olegsbogdanovs.hardshop.persistanse.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Parameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String desc;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parameter")
    private Set<ItemParameterValue> itemParameterValues;

    @ManyToOne
    private Category category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Set<ItemParameterValue> getItemParameterValues() {
        return itemParameterValues;
    }

    public void setItemParameterValues(Set<ItemParameterValue> itemParameterValues) {
        this.itemParameterValues = itemParameterValues;
    }


}
