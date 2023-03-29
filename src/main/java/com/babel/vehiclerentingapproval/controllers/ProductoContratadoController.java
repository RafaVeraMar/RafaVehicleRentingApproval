package com.babel.vehiclerentingapproval.controllers;

import com.babel.vehiclerentingapproval.models.ProductoContratado;
import com.babel.vehiclerentingapproval.services.ProductoContratadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductoContratadoController {

    ProductoContratadoService productoContratadoService;

    public ProductoContratadoController(ProductoContratadoService productoContratadoService){
        this.productoContratadoService = productoContratadoService;
    }

    @PostMapping("/productoContratado")
    ResponseEntity addProductoContratado(@RequestBody ProductoContratado productoContratado){
        this.productoContratadoService.addProductoContratado(productoContratado);

        return ResponseEntity.ok(String.format("Producto contratado añadido. ID: %d", productoContratado.getIdProductoContratado()));
    }
}
