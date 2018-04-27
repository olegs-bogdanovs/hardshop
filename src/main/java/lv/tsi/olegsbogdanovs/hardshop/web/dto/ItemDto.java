package lv.tsi.olegsbogdanovs.hardshop.web.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class ItemDto {
    private Long id;
    private Long categoryId;
    @Size(min = 3, max = 99)
    private String desc;
    @Min(0)
    private Integer quantity;
    @Size(min = 3, max = 99)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
