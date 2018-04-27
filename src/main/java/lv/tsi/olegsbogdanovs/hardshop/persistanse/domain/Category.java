package lv.tsi.olegsbogdanovs.hardshop.persistanse.domain;

import javax.persistence.*;
import java.util.HashSet;
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
    private Set<Item> items = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private Set<Parameter> parameters = new HashSet<>();

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

    public Set<Item> getItems() {
        return items;
    }

    public void addItem(Item item){
        items.add(item);
        item.setCategory(this);
    }

//    public void setItems(Set<Item> items) {
//        this.items = items;
//    }

    public Set<Parameter> getParameters() {
        return parameters;
    }

    public void addParameter(Parameter parameter){
        parameters.add(parameter);
        parameter.setCategory(this);
    }

//    public void setParameters(Set<Parameter> parameters) {
//        this.parameters = parameters;
//    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", items=" + items +
                ", parameters=" + parameters +
                '}';
    }
}
