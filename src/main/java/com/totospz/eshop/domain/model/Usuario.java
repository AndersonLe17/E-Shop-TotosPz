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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data @SuperBuilder
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "usuarios")
public class Usuario extends EntityConfig implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usu_cod", nullable = false)
    private Integer usuCod;

    @OneToOne(optional = false)
    @JoinColumn(name = "usu_per_cod", nullable = false, unique = true)
    private Persona usuPer;

    @Column(name = "usu_nom", nullable = false, unique = true, length = 100)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String usuNom;

    @Column(name = "usu_cor_ele", nullable = false, unique = true)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String usuCorEle;

    @Column(name = "usu_con", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String usuCon;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usu_perf_cod", nullable = false)
    private Perfil usuPerf;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usu_sed_cod", nullable = false)
    private Sede usuSed;

    @Enumerated(EnumType.STRING)
    @Column(name = "usu_est", nullable = false, length = 20)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private Estado usuEst;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.getUsuPer().getPerNom()));
    }

    @Override
    public String getPassword() {
        return this.usuCon;
    }

    @Override
    public String getUsername() {
        return this.usuNom;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}