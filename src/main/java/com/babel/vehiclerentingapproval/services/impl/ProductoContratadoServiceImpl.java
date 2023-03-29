package com.babel.vehiclerentingapproval.services.impl;

import com.babel.vehiclerentingapproval.models.ProductoContratado;
import com.babel.vehiclerentingapproval.persistance.database.mappers.ProductoContratadoMapper;
import com.babel.vehiclerentingapproval.services.ProductoContratadoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoContratadoServiceImpl implements ProductoContratadoService {

    private ProductoContratadoMapper productoContratadoMapper;

    public ProductoContratadoServiceImpl(ProductoContratadoMapper productoContratadoMapper){
        this.productoContratadoMapper = productoContratadoMapper;
    }
    @Override
    @Transactional
    public ProductoContratado addProductoContratado(ProductoContratado productoContratado) {

        this.productoContratadoMapper.insertProductoContratado(productoContratado);

        return productoContratado;
    }
}
