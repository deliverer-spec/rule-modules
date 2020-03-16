package com.ciana.rule.engine.log;

import lombok.Data;

/**
 * 每个ruleGroupInfo 的每次执行有一个日志 RuleLog日志
 * @author admin
 *
 */
@Data
public class RuleLog {
	
	private String id;//表主键
	private String ruleCode; //规则码
	private String version;
	private String ruleGroupId;
	private String params;//输入参数  json格式
	private String ruleGroupName;
	private String opTime;
	private Object result;//规则返回结果
	private String code; //执行成功、失败
	private String message;//失败原因
	private String execLog;
	
}
