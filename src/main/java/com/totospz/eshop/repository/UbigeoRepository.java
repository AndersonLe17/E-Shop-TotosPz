package com.totospz.eshop.repository;

import com.totospz.eshop.domain.model.Ubigeo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UbigeoRepository extends JpaRepository<Ubigeo, String> {
}
