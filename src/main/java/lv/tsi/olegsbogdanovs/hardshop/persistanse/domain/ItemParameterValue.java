package lv.tsi.olegsbogdanovs.hardshop.persistanse.domain;


import javax.persistence.*;

@Entity
public class ItemParameterValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Parameter parameter;
    @ManyToOne
    private Item item;

    private String value;


}
