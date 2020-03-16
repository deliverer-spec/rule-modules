package com.ciana.rule.engine.function;

import java.util.Map;

import com.ciana.rule.engine.function.intf.ExecInterface;
import com.ciana.rule.engine.log.RuleLogDtl;
import com.ciana.rule.engine.parse.entity.RuleNode;
/**
 * 未被定义的节点逻辑
 * @author admin
 *
 */
public class NonExecute implements ExecInterface{
	
	@Override
	public Map<String, Object> initParams(RuleNode node, Map<String, Object> lastParams, Boolean isTest) throws Exception {
		return lastParams;
	}

	@Override
	public Object ifExec(String ruleType, RuleNode node, Map<String, Object> params, RuleLogDtl dLog) throws Exception {
//		dLog = null;  //这类节点不记录日志
		throw new Exception("不支持的节点类型:"+node.getType());
	}

	@Override
	public Object thenExec(String ruleType, RuleNode node, Map<String, Object> params, RuleLogDtl dLog, Object obj) throws Exception {
		return null;
	}

}
