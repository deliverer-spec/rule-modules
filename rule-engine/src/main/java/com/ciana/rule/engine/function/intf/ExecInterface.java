package com.ciana.rule.engine.function.intf;

import java.util.Map;

import com.ciana.rule.engine.log.RuleLogDtl;
import com.ciana.rule.engine.parse.entity.RuleNode;

/**
 * 实现具体节点的逻辑接口
 * @author yinbo
 * @date: 2019/06/16 14:18
 */
public interface ExecInterface {
	
	/**
	 * 定义每个节点执行前的参数初始化
	 * @param node
	 * @param req_params
	 * @param isTest
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> initParams(RuleNode node, Map<String, Object> req_params, Boolean isTest)  throws Exception;

	/**
	 * 每个节点执行条件语句
	 * @param ruleType
	 * @param node
	 * @param params
	 * @param dLog
	 * @return
	 * @throws Exception
	 */
	public Object ifExec(String ruleType, RuleNode node, Map<String, Object> params, RuleLogDtl dLog) throws Exception;
	
	/**
	 * 每个节点执行结果语句
	 * @param ruleType
	 * @param node
	 * @param params
	 * @param dLog
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object thenExec(String ruleType, RuleNode node, Map<String, Object> params, RuleLogDtl dLog, Object obj) throws Exception;
	
}
