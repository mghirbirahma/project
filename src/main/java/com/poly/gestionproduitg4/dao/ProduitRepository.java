package com.poly.gestionproduitg4.dao;

import com.poly.gestionproduitg4.entities.Produit;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ProduitRepository extends JpaRepository<Produit,Long> {
    public Page<Produit> findByNomContains(String mc, Pageable p);
    @Query("select p from Produit p where p.categorie.id=:x")
    public List<Produit> getProductsByCat(@Param("x") Long idc);



}
