package com.babel.vehiclerentingapproval.persistance.database.mappers;

import com.babel.vehiclerentingapproval.models.ProductoContratado;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface ProductoContratadoMapper {
    @Insert("INSERT INTO PRODUCTO_CONTRATADO (PRODUCTO_ID, ALIAS, IMPORTE_NOMINAL, FECHA_ALTA, FECHA_BAJA)" +
            " VALUES (#{idProducto}, #{alias, jdbcType=VARCHAR}, #{importeNominal}, " +
            "#{fechaAlta, jdbcType=DATE}, null)")
    @Options(useGeneratedKeys = true, keyProperty = "idProductoContratado", keyColumn = "PRODUCTO_CONTRATADO_ID")
    void insertProductoContratado(ProductoContratado productoContratado);
}
