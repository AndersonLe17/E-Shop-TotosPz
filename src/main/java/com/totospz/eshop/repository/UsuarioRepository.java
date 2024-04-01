package com.totospz.eshop.repository;

import com.totospz.eshop.domain.enums.Estado;
import com.totospz.eshop.domain.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByUsuNom(String usuNom);

    Optional<Usuario> findByUsuNomIgnoreCaseOrUsuCorEle(@Nullable String usuNom, @Nullable String usuCorEle);

    @Query("SELECT u FROM Usuario u WHERE " +
            "(:usuNom is null or UPPER(u.usuNom) LIKE UPPER(concat('%', cast(:usuNom as string), '%'))) AND " +
            "(:perNom is null or UPPER(concat(u.usuPer.perNom, ' ', u.usuPer.perApePat, ' ', u.usuPer.perApeMat)) LIKE UPPER(concat('%', cast(:perNom as string), '%'))) AND " +
            "(:perfCod is null or u.usuPerf.perfCod = :perfCod) AND " +
            "(:sedCod is null or u.usuSed.sedCod = :sedCod) AND " +
            "(:usuEst is null or u.usuEst = :usuEst)")
    Page<Usuario> pageUsuario(@Param("usuNom") String usuNom, @Param("perNom") String perNom,
                              @Param("perfCod") Integer perfCod, @Param("sedCod") Integer sedCod,
                              @Param("usuEst") Estado usuEst, Pageable pageable);

    boolean existsByUsuNomIgnoreCase(String usuNom);

}
