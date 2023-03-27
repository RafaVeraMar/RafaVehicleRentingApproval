package com.babel.vehiclerentingapproval.persistance.database.mappers;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ValidacionesMapper {

    @Select("SELECT INVERSION FROM SOLICITUD_RENTING")




}
