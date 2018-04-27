package lv.tsi.olegsbogdanovs.hardshop.web.dto;


public class ItemParameterValueDto {
    private Long id;

    private Long itemId;

    private ParameterDto parameter;
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ParameterDto getParameter() {
        return parameter;
    }

    public void setParameter(ParameterDto parameter) {
        this.parameter = parameter;
    }
}
