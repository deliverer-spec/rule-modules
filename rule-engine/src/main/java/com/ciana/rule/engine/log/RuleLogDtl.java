package com.ciana.rule.engine.log;

import lombok.Data;

/**
 * 每个节点有一个日志
 * @author admin
 *
 */
@Data
public class RuleLogDtl {
	

	private String id;
	private String ruleLogId;
	private String ruleGroupId;
	private String ruleGroupName;
	private String ruleId;
	private String ruleName;
	private String match;
	private String content;
	private String version;
	private String description;
	private String params;
	private String opTime;
	private Integer depth;//当前节点所在节点深度
	private String nodeType;
	
	private Object objIf;
	private Object objThen;
	private String code;
	private String message;
	
}
