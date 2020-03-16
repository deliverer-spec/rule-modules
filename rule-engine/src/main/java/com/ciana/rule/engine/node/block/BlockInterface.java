package com.ciana.rule.engine.node.block;

import java.util.Map;

import com.ciana.rule.engine.log.RuleLogDtl;

public interface BlockInterface {

	//参数初始化
	public Map<String, Object> initParams(Map<String, Object> req_params)  throws Exception;

	//执行条件语句
	public Object ifExec(Map<String, Object> params, RuleLogDtl dLog) throws Exception;
	
	//执行结论语句
	public Object thenExec(Map<String, Object> params, RuleLogDtl dLog, Object obj) throws Exception;
	
}
