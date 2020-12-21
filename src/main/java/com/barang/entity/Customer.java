/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barang.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

/**
 *
 * @author ITAdmin
 */
@Entity
@Table(schema = "toko", name = "customer")
@Data
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pemesan")
    private BigInteger idPemesan;
    @Column(name = "no_register")
    private String noReg;
    @Column(name = "nama_barang")
    private String namaBrg;
    @Column(name = "ket_pemesan")
    private String ketPemesan;
    @Column(name = "jumlah_barang")
    private int jmlBrg;
    @Column(name = "unit_barang")
    private String unitBrg;
    @Column(name = "alamat_pemesan")
    private String alamatPemesan;
    @Column(name = "nama_pemesan")
    private String namaPemesan;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "tanggal_pemesanan")
    private Date tglPemesan;
}
