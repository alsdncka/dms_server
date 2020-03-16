package com.di.xyz.Request;

import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Data;


@Data
@Alias("sourceColumnVo")
public class SourceColumnVo {
	private String logicId;
	private String sourceColumnNum;
	private String sourceColumnName;

}
