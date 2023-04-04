package com.babel.vehiclerentingapproval.persistance.database.mappers;

import com.babel.vehiclerentingapproval.models.ResolucionSolicitud;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ResolucionSolicitudesMapper {

    @Select("SELECT COD_RESULTADO, DESCRIPCION FROM TIPO_RESULTADO_SOLICITUD")
    @Results({
            @Result(property = "codigoResultado" , column = "COD_RESULTADO"),
            @Result(property = "descripcion" , column = "DESCRIPCION")
    })
    List<ResolucionSolicitud> getTipoResolucionesSolicitudes();


}
