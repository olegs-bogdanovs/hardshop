package lv.tsi.olegsbogdanovs.hardshop.web.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Objects;

public class ParameterDto {
    private Long id;
    @Min(5)
    @Max(99)
    private String name;
    @Min(5)
    @Max(99)
    private String desc;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParameterDto that = (ParameterDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(desc, that.desc);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, desc);
    }

    @Override
    public String toString() {
        return "ParameterDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
