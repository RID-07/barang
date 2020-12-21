/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barang.controller;

import com.barang.entity.Barang;
import com.barang.service.TokoService;
import com.barang.view.VBarang;
import com.barang.view.VBarangCustomer;
import com.barang.view.VCustomer;
import com.barang.view.VUpdateBarang;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ITAdmin
 */
@Api(tags = "Service Toko", description = "REST Service Toko", produces = "application/json")
@ApiResponses(value = {
    @ApiResponse(code = 200, message = "Success"),
    @ApiResponse(code = 400, message = "Failed")
})
@RestController
@RequestMapping("/toko")
public class BarangController {

    @Autowired
    TokoService tokoService;

    @ApiOperation(value = "Post Barang")
    @PostMapping(value = "/postBarang", produces = "application/json", consumes = "application/json")
    public Barang postBarang(@RequestBody VBarang vBarang) {
        return tokoService.saveBarang(vBarang);
    }

    @ApiOperation(value = "Post Customer")
    @PostMapping(value = "/postCustomer", produces = "application/json", consumes = "application/json")
    public String postCustomer(@RequestBody VCustomer vCustomer) {
        return tokoService.saveCustomer(vCustomer);
    }

    @ApiOperation(value = "Update Barang")
    @PutMapping(value = "/updateBarang", produces = "application/json", consumes = "application/json")
    public String updateBarang(@RequestBody VUpdateBarang updateBarang) {
        return tokoService.UpdateBarangByNoREG(updateBarang);
    }

    @ApiOperation(value = "Get Barang Customer")
    @GetMapping(value = "/getCustomer/{namaPemesan}", produces = "application/json")
    public VBarangCustomer getCustomer(@PathVariable("namaPemesan") String namaPemesan) {
        return tokoService.getBarangByNamaPemesan(namaPemesan);
    }
}
