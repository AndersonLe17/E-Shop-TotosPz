package com.totospz.eshop.repository;

import com.totospz.eshop.domain.enums.Estado;
import com.totospz.eshop.domain.model.Perfil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PerfilRepository extends JpaRepository<Perfil, Integer> {
    boolean existsByPerfNomIgnoreCase(String perfNom);

    @Query("SELECT p FROM Perfil p WHERE " +
            "(:perfNom is null or UPPER(p.perfNom) LIKE UPPER(concat('%', cast(:perfNom as string), '%'))) AND " +
            "(:perfEst is null or p.perfEst = :perfEst)")
    Page<Perfil> pagePerfil(@Param("perfNom") String perfNom, @Param("perfEst") Estado perfEst, Pageable pageable);

}
