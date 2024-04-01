package com.totospz.eshop.domain.model;

import com.totospz.eshop.domain.config.EntityConfig;
import com.totospz.eshop.domain.enums.Estado;
import com.totospz.eshop.domain.model.Persona;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Data @SuperBuilder
@AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "cliente")
public class Cliente extends EntityConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cli_cod", nullable = false)
    private Integer cliCod;

    @OneToOne(optional = false)
    @JoinColumn(name = "cli_per_cod", nullable = false, unique = true)
    private Persona cliPer;

    @Column(name = "cli_ruc", length = 11)
    @JdbcTypeCode(SqlTypes.CHAR)
    private String cliRuc;

    @Enumerated(EnumType.STRING)
    @Column(name = "cli_est", nullable = false, length = 20)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private Estado cliEst;

}