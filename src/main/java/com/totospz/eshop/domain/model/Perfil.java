package com.totospz.eshop.domain.model;

import com.totospz.eshop.domain.config.EntityConfig;
import com.totospz.eshop.domain.enums.Estado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Data @SuperBuilder
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "perfiles")
public class Perfil extends EntityConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "perf_cod", nullable = false)
    private Integer perfCod;

    @Column(name = "perf_nom", nullable = false, unique = true, length = 30)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String perfNom;

    @Column(name = "perf_des", nullable = false, length = 128)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String perfDes;

    @Column(name = "perf_det", nullable = false, length = 512)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String perfDet;

    @Enumerated(EnumType.STRING)
    @Column(name = "perf_est", nullable = false, length = 20)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private Estado perfEst;

    @OneToMany(mappedBy = "usuPerf")
    private List<Usuario> perfUsus;

}