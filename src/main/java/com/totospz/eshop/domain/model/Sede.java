package com.totospz.eshop.domain.model;

import com.totospz.eshop.domain.config.EntityConfig;
import com.totospz.eshop.domain.enums.Estado;
import com.totospz.eshop.domain.model.Ubigeo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Data @SuperBuilder
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "sedes")
public class Sede extends EntityConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sed_cod", nullable = false)
    private Integer sedCod;

    @Column(name = "sed_nom", nullable = false, unique = true, length = 128)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String sedNom;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sed_ubi_cod", nullable = false)
    private Ubigeo sedUbi;

    @Column(name = "sed_dir", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String sedDir;

    @Column(name = "sed_ruc", nullable = false, unique = true, length = 11)
    @JdbcTypeCode(SqlTypes.CHAR)
    private String sedRuc;

    @Column(name = "sed_raz_soc", nullable = false, unique = true)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String sedRazSoc;

    @Enumerated(EnumType.STRING)
    @Column(name = "sed_est", nullable = false, length = 20)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private Estado sesEst;

}
