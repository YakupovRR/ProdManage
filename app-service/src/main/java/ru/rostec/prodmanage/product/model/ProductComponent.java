package ru.rostec.prodmanage.product.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_relations")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ProductComponent {

    @EmbeddedId
    private ProductComponentId  id;

    @Column
    private Integer quantity;

    @ManyToOne
    @MapsId("parentProductId")
    @JoinColumn(name = "parent_product_id")
    private Product parentProduct;

    @ManyToOne
    @MapsId("childProductId")
    @JoinColumn(name = "child_product_id")
    private Product childProduct;

}
