package ru.rostec.prodmanage.product;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ProductComponentId implements Serializable {

    @Column(name = "parent_product_id")
    private Long parentProductId;

    @Column(name = "child_product_id")
    private Long childProductId;
}
