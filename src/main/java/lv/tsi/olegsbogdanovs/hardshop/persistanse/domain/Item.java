package lv.tsi.olegsbogdanovs.hardshop.persistanse.domain;

import javax.persistence.*;
import java.util.*;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;
    private String name;
    private String desc;

    @ManyToMany
    @JoinTable(name="item_cart",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "cart_id"))
    private Set<Cart> carts = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private List<ItemParameterValue> itemParameterValues = new ArrayList<>();

    @Lob
    private Byte[] image;

    @ManyToOne
    private Category category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Cart> getCarts() {
        return carts;
    }

    public void setCarts(Set<Cart> carts) {
        this.carts = carts;
    }

    public List<ItemParameterValue> getItemParameterValues() {
        return itemParameterValues;
    }

    public void addItemParameterValue(ItemParameterValue itemParameterValue){
        this.itemParameterValues.add(itemParameterValue);
        itemParameterValue.setItem(this);
    }
//    public void setItemParameterValues(Set<ItemParameterValue> itemParameterValues) {
//        this.itemParameterValues = itemParameterValues;
//    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


}
