package com.ciana.rule.engine.check.node;

import java.util.Map;

import com.ciana.rule.engine.check.CheckLog;
import com.ciana.rule.engine.parse.RuleParse;
import com.ciana.rule.engine.parse.entity.RuleNode;

public class CommonRCardCheck implements CNInterface{

	@Override
	public void check(String id, RuleNode node, RuleParse parse, Map<String, Object> params, CheckLog log)
			throws Exception {
		//字段  match_condition  rule_content  card_node_type
		//card_node_type 取值范围 N=空维度    D=分组维度   W=字段维度
		
		String card_node_type = node.getCardNodeType();
		if("D".equals(card_node_type)) {
			
		}else if("W".equals(card_node_type)) {
			
		}
	}

}
