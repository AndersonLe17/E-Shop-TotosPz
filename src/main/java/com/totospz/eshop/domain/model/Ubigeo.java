package com.totospz.eshop.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Data @SuperBuilder
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "ubigeo")
public class Ubigeo {
    @Id
    @Column(name = "ubi_cod", nullable = false, length = 6)
    @JdbcTypeCode(SqlTypes.CHAR)
    private String ubiCod;

    @Column(name = "ubi_dpt", nullable = false, length = 32)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String ubiDpt;

    @Column(name = "ubi_pro", nullable = false, length = 32)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String ubiPro;

    @Column(name = "ubi_dis", nullable = false, length = 32)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String ubiDis;

}
