/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barang.repository;

import com.barang.entity.Barang;
import java.math.BigInteger;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ITAdmin
 */
@Repository
public interface BarangRepository extends JpaRepository<Barang, BigInteger> {

    public Barang findByNamaBrg(String namaBrg);

    @Transactional
    @Modifying
    @Query(value = "update toko.barang set stok_barang = :stokBrg where nama_barang = :namaBrg", nativeQuery = true)
    void updateJmlBrgByNamaBrg(@Param("stokBrg") int stokBrg, @Param("namaBrg") String namaBrg);
}
