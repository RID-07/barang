/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barang.service;

import com.barang.entity.Barang;
import com.barang.entity.Customer;
import com.barang.repository.BarangRepository;
import com.barang.repository.CustomerRepository;
import com.barang.utils.ConstantUtil;
import com.barang.utils.Sequence;
import com.barang.utils.Util;
import com.barang.view.VBarang;
import com.barang.view.VBarangCustomer;
import com.barang.view.VCustomer;
import com.barang.view.VUpdateBarang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ITAdmin
 */
@Service
public class TokoService {

    @Autowired
    BarangRepository brgRepository;
    @Autowired
    CustomerRepository custRepository;

    public Barang saveBarang(VBarang vBarang) {
        Barang brg = new Barang();
        brg.setNamaBrg(vBarang.getNamaBrg());
        brg.setUnitBrg(vBarang.getUnitBrg());
        brg.setStokBrg(vBarang.getStokBrg());
        brgRepository.save(brg);
        return brg;
    }

    public String saveCustomer(VCustomer vCustomer) {
        Customer cust = new Customer();
        cust.setNoReg(Util.getNomorRegister(Util.objectToString(custRepository.nextVal(Sequence.SEQ_ID_CUSTOMER))));
        cust.setNamaPemesan(vCustomer.getNamaPemesan());
        cust.setKetPemesan(vCustomer.getKetPemesan());
        cust.setAlamatPemesan(vCustomer.getAlamatPemesan());
        Barang barang = brgRepository.findByNamaBrg(vCustomer.getNamaBrg());
        if (barang.getNamaBrg().equals(vCustomer.getNamaBrg())) {
            cust.setNamaBrg(vCustomer.getNamaBrg());
            if (vCustomer.getJmlBrg() < barang.getStokBrg()) {
                cust.setJmlBrg(vCustomer.getJmlBrg());
            } else {
                return ConstantUtil.UNDER_STOCK;
            }
            cust.setUnitBrg(vCustomer.getUnitBrg());
            cust.setTglPemesan(vCustomer.getTglPemesanan());
            custRepository.save(cust);
            int sisaBrg = barang.getStokBrg() - vCustomer.getJmlBrg();
            barang.setStokBrg(sisaBrg);
            brgRepository.updateJmlBrgByNamaBrg(sisaBrg, barang.getNamaBrg());
        } else {
            return ConstantUtil.NOT_FOUND;
        }
        return ConstantUtil.ORDERED + cust.getNoReg();
    }

    public String UpdateBarangByNoREG(VUpdateBarang barang) {
        Customer customer = custRepository.findByNoReg(barang.getNoReg());
        if (!customer.getNoReg().isEmpty()) {
            Barang brg = brgRepository.findByNamaBrg(barang.getNamaBrg());
            if (barang.getNamaBrg().equalsIgnoreCase(brg.getNamaBrg())) {
                barang.setNamaBrg(customer.getNamaBrg());
                barang.setNoReg(customer.getNoReg());
                if (barang.getKurangBrg() == 0 && barang.getTambahBrg() != 0) {
                    if (brg.getStokBrg() >= barang.getTambahBrg()) {
                        int brgA = customer.getJmlBrg() + barang.getTambahBrg();
                        int jmlStokA = brg.getStokBrg() - barang.getTambahBrg();
                        custRepository.updateJmlBrgByNoReg(brgA, customer.getNoReg());
                        brgRepository.updateJmlBrgByNamaBrg(jmlStokA, brg.getNamaBrg());
                    } else {
                        return ConstantUtil.STOCK_UNDER_ADDED;
                    }
                } else if (barang.getKurangBrg() != 0 && barang.getTambahBrg() == 0) {
                    int brgB = customer.getJmlBrg() - barang.getKurangBrg();
                    int jmlStokB = brg.getStokBrg() + barang.getKurangBrg();
                    custRepository.updateJmlBrgByNoReg(brgB, customer.getNoReg());
                    brgRepository.updateJmlBrgByNamaBrg(jmlStokB, brg.getNamaBrg());
                }
            } else {
                return ConstantUtil.NOT_FOUND;
            }
        } else {
            return ConstantUtil.NOT_FOUND_NOREG;
        }
        return ConstantUtil.SUCCESS;
    }

    public VBarangCustomer getBarangByNamaPemesan(String namaPemesan) {
        VBarangCustomer vBarangCustomer = new VBarangCustomer();
        Customer customer = custRepository.findByNamaPemesan(namaPemesan);
        vBarangCustomer.setNamaPemesan(customer.getNamaPemesan());
        vBarangCustomer.setNamaBrg(customer.getNamaBrg());
        vBarangCustomer.setJmlBrg(customer.getJmlBrg());
        return vBarangCustomer;
    }
}
