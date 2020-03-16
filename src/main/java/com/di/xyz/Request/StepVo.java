package com.di.xyz.Request;

import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Data;


@Data
@Alias("stepVo")
public class StepVo {
	private String logicId;
	private String stepId;
	private String stepNum;
	private String stepType;
	private String stepQueryFile;
	private String stepComment;
	private String query;
}
