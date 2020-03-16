package com.ciana.rule.engine.function;

import java.util.Map;

import com.ciana.rule.engine.function.intf.ExecInterface;
import com.ciana.rule.engine.log.RuleLogDtl;
import com.ciana.rule.engine.node.child.RuleChild;
import com.ciana.rule.engine.parse.entity.RuleNode;

/**
 * 子规则调用
 * @author admin
 *
 */
public class ChildExecute implements ExecInterface {

	@Override
	public Map<String, Object> initParams(RuleNode node, Map<String, Object> req_params, Boolean isTest)
			throws Exception {
		return req_params;
	}

	@Override
	public Object ifExec(String ruleType, RuleNode node, Map<String, Object> params, RuleLogDtl dLog) throws Exception {
		//子节点无条件逻辑
		return true;
	}

	@Override
	public Object thenExec(String ruleType, RuleNode node, Map<String, Object> params, RuleLogDtl dLog, Object obj)
			throws Exception {
		//动作节点逻辑是调用子程序
		RuleChild rc = new RuleChild();
		return rc.execChild(ruleType, node, params, node.getDepth());
	}

}
