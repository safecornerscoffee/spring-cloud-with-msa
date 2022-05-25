package com.safecornerscoffee.msa.product.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String productId;

    @Column(nullable=false)
    private String productName;
    @Column(nullable=false)
    private Integer stock;
    @Column(nullable=false)
    private Integer unitPrice;

    @Column(nullable=false, updatable = false, insertable=false)
    @ColumnDefault(value="CURRENT_TIMESTAMP")
    private Date createdAt;
}
