/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barang.entity;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author ITAdmin
 */
@Entity
@Table(schema = "toko", name = "barang")
@Data
public class Barang implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBarang")
    private BigInteger idBrg;
    @Column(name = "nama_barang")
    private String namaBrg;
    @Column(name = "unit_barang")
    private String unitBrg;
    @Column(name = "stok_barang")
    private int stokBrg;
}
