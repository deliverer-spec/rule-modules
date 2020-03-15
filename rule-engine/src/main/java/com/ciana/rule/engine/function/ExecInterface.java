package com.ciana.rule.engine.function;

import java.util.Map;

import com.ciana.rule.engine.log.RuleLogDtl;
import com.ciana.rule.engine.parse.entity.RuleNode;

public interface ExecInterface {
	
	//参数初始化
	public Map<String, Object> initParams(RuleNode node, Map<String, Object> req_params, Boolean isTest)  throws Exception;

	//执行条件语句
	public Object ifExec(String ruleType, RuleNode node, Map<String, Object> params, RuleLogDtl dLog) throws Exception;
	
	//执行结论语句
	public Object thenExec(String ruleType, RuleNode node, Map<String, Object> params, RuleLogDtl dLog, Object obj) throws Exception;
	
}
