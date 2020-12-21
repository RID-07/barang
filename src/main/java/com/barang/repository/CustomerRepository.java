/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barang.repository;

import com.barang.entity.Customer;
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
public interface CustomerRepository extends JpaRepository<Customer, BigInteger>{
    
    @Query(value = "select nextval(:sequence)", nativeQuery = true)
    public BigInteger nextVal(@Param("sequence") String sequence);
    
    @Transactional
    @Modifying
    @Query(value = "update toko.customer set jumlah_barang = :jmlBrg where no_register = :noreg", nativeQuery = true)
    void updateJmlBrgByNoReg(@Param("jmlBrg")int jmlBrg, @Param("noreg") String noreg);
    
    public Customer findByNoReg(String noReg);
    
    public Customer findByNamaPemesan(String namaPemesan);
}
