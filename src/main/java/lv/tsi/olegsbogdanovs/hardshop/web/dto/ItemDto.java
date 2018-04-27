package lv.tsi.olegsbogdanovs.hardshop.web.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ItemDto {
    private Long id;
    private Long categoryId;
    @Size(min = 3, max = 99)
    private String desc;
    @Min(0)
    private Integer quantity;
    @Size(min = 3, max = 99)
    private String name;

    private List<ItemParameterValueDto> values = new ArrayList<>();

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

    public List<ItemParameterValueDto> getValues() {
        return values;
    }

    public void setValues(List<ItemParameterValueDto> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "ItemDto{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", desc='" + desc + '\'' +
                ", quantity=" + quantity +
                ", name='" + name + '\'' +
                ", values=" + values +
                '}';
    }
}
