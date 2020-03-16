package com.di.xyz.Request;

import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Data;


@Data
@Alias("targetColumnVo")
public class TargetColumnVo {
	private String logicId;
	private String targetColumnNum;
	private String targetColumnName;

}
