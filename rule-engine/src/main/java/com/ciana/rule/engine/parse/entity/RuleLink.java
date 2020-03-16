package com.ciana.rule.engine.parse.entity;

/**
 * 
 * @author yinbo
 * @date: 2019/06/16 14:06
 */
@lombok.Data
public class RuleLink {

	private String id;
	private String type;
	private String sourceId;
	private String targetId;
	private String relations;
	private Integer priority;
	private String execs; //是否执行，默认false
	
}
