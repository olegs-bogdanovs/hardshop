package lv.tsi.olegsbogdanovs.hardshop.web.dto;

import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class CategoryDto {
    private Long id;
    @Size(min=2, max=30)
    private String name;
    @Size(min=2, max=30)
    private String desc;

//    private Set<Item> items = new HashSet<>();
    private Set<ParameterDto> parameters = new HashSet<>();

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

    public Set<ParameterDto> getParameters() {
        return parameters;
    }

    public void setParameters(Set<ParameterDto> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "CategoryDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
