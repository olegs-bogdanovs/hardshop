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

    @ManyToMany(mappedBy = "parameters")
    private Set<Category> categories;

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

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", itemParameterValues=" + itemParameterValues +
                ", categories=" + categories +
                '}';
    }
}
