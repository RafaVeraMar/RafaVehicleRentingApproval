package com.babel.vehiclerentingapproval.persistance.database.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ProfesionMapper {
    @Select("SELECT COUNT(PROFESION_ID) FROM PROFESION WHERE PROFESION_ID = #{profesionId}")
    int existeProfesion(int profesionId);
}
