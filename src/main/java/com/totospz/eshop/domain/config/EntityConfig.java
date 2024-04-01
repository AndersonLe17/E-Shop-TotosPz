package com.totospz.eshop.domain.config;

import com.totospz.eshop.domain.model.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data @SuperBuilder
@NoArgsConstructor @AllArgsConstructor
@MappedSuperclass
public class EntityConfig {
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "usu_reg", nullable = false)
    private Usuario usuReg;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "usu_mod", nullable = false)
    private Usuario usuMod;

    @Column(name = "fec_hor_reg", nullable = false, updatable = false, columnDefinition = "timestamp with time zone")
    @CreationTimestamp
    private LocalDateTime fecHorReg;

    @Column(name = "fec_hor_mod", nullable = false, columnDefinition = "timestamp with time zone")
    @UpdateTimestamp
    private LocalDateTime fecHorMod;

}