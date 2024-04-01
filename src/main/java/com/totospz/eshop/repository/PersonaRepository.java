package com.totospz.eshop.repository;

import com.totospz.eshop.domain.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {
    boolean existsByPerNumDoc(String perNumDoc);

    boolean existsByPerCorEle(String perCorEle);

}
