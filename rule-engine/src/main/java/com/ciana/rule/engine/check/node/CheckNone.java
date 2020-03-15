package com.ciana.rule.engine.check.node;

import java.util.Map;

import com.ciana.rule.engine.check.CheckLog;
import com.ciana.rule.engine.parse.RuleParse;
import com.ciana.rule.engine.parse.entity.RuleNode;

public class CheckNone implements CNInterface{

	@Override
	public void check(String id, RuleNode node, RuleParse parse, Map<String, Object> params, CheckLog log)
			throws Exception {
		
		log.error(node, "没有找到定义的节点类型");
	}

}
