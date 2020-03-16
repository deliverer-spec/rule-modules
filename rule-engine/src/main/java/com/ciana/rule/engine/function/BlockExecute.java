package com.ciana.rule.engine.function;

import java.util.Map;

import com.ciana.rule.engine.function.intf.ExecInterface;
import com.ciana.rule.engine.log.RuleLogDtl;
import com.ciana.rule.engine.node.block.BlockInterface;
import com.ciana.rule.engine.parse.entity.RuleNode;

public class BlockExecute implements ExecInterface{

	@Override
	public Map<String, Object> initParams(RuleNode node, Map<String, Object> lastParams, Boolean isTest) throws Exception {
		@SuppressWarnings("rawtypes")
		Class cls = Class.forName(node.getIntfname());
		BlockInterface def = (BlockInterface) cls.newInstance();
		return def.initParams(lastParams);
	}

	@Override
	public Object ifExec(String ruleType, RuleNode node, Map<String, Object> params, RuleLogDtl dLog) throws Exception {
		Class<?> cls = Class.forName(node.getIntfname());
		BlockInterface def = (BlockInterface) cls.newInstance();
		return def.ifExec(params, dLog);
	}

	@Override
	public Object thenExec(String ruleType, RuleNode node, Map<String, Object> params, RuleLogDtl dLog, Object obj) throws Exception {
		Class<?> cls = Class.forName(node.getIntfname());
		BlockInterface def = (BlockInterface) cls.newInstance();
		return def.thenExec(params, dLog, obj);
	}

}
