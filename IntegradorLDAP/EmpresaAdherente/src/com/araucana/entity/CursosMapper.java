package com.araucana.entity;

import org.apache.ibatis.annotations.Select;

public interface CursosMapper {
	@Select("select * from cursos2 where id=#{id}")
	Empresa findById(String id);

}
