package com.totospz.eshop.domain.model;

import com.totospz.eshop.domain.config.EntityConfig;
import com.totospz.eshop.domain.enums.Documento;
import com.totospz.eshop.domain.enums.Estado;
import com.totospz.eshop.domain.model.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Data @SuperBuilder
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "personas")
public class Persona extends EntityConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "per_cod", nullable = false)
    private Integer perCod;

    @Column(name = "per_nom", nullable = false, length = 120)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String perNom;

    @Column(name = "per_ape_pat", length = 100)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String perApePat;

    @Column(name = "per_ape_mat", length = 100)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String perApeMat;

    @Enumerated(EnumType.STRING)
    @Column(name = "per_tip_doc", length = 10)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private Documento perTipDoc;

    @Column(name = "per_num_doc", unique = true, length = 15)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String perNumDoc;

    @Column(name = "per_cor_ele", unique = true)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String perCorEle;

    @Enumerated(EnumType.STRING)
    @Column(name = "per_est", nullable = false, length = 20)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private Estado perEst;

    @OneToOne(mappedBy = "usuPer", orphanRemoval = true)
    private Usuario perUsu;

}