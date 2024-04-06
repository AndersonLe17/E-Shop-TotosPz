package com.totospz.eshop.repository;

import com.totospz.eshop.domain.enums.Estado;
import com.totospz.eshop.domain.model.Sede;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SedeRepository extends JpaRepository<Sede, Integer> {
    boolean existsBySedNomIgnoreCase(String sedNom);

    boolean existsBySedRuc(String sedRuc);

    boolean existsBySedRazSocIgnoreCase(String sedRazSoc);

    @Query("SELECT s FROM Sede s WHERE " +
            "(:sedNom is null or UPPER(s.sedNom) LIKE UPPER(concat('%', cast(:sedNom as string), '%'))) AND " +
            "(:sedRazSoc is null or UPPER(s.sedRazSoc) LIKE UPPER(concat('%', cast(:sedRazSoc as string), '%'))) AND " +
            "(:sedRuc is null or s.sedRuc LIKE concat('%', cast(:sedRuc as string), '%')) AND " +
            "(:ubiDpt is null or s.sedUbi.ubiCod LIKE concat(cast(:ubiDpt as string), '____%')) AND" +
            "(:ubiPro is null or s.sedUbi.ubiCod LIKE concat('__', cast(:ubiPro as string), '%')) AND" +
            "(:ubiDis is null or s.sedUbi.ubiCod LIKE concat('%____', cast(:ubiDis as string))) AND" +
            "(:sedEst is null or s.sedEst = :sedEst)")
    Page<Sede> pageSede(@Param("sedNom") String sedNom, @Param("ubiDpt") String ubiDpt, @Param("ubiPro") String ubiPro, @Param("ubiDis") String ubiDis,
                        @Param("sedRazSoc") String sedRazSoc, @Param("sedRuc") String sedRuc, @Param("sedEst") Estado sedEst, Pageable pageable);
}
