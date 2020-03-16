package com.di.xyz.Request;

import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Data;


@Data
@Alias("mappingVo")
public class MappingVo {
	private String logicId;
	private String mappingId;
	private String sourceColumnName;
	private String targetColumnName;
	
}
