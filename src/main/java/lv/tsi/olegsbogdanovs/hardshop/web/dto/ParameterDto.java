package lv.tsi.olegsbogdanovs.hardshop.web.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class ParameterDto {
    private Long id;
    @Min(5)
    @Max(99)
    private String name;
    @Min(5)
    @Max(99)
    private String desc;
    private Long categoryId;

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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "ParameterDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", categoryId=" + categoryId +
                '}';
    }
}
