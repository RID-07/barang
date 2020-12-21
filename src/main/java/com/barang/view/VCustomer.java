/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barang.view;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author ITAdmin
 */
@Data
public class VCustomer {

    private String ketPemesan, namaPemesan, alamatPemesan, namaBrg, unitBrg;
    private int jmlBrg;
    private Date tglPemesanan;
}
