package com.ciana.rule.engine.node.data;

import java.util.Map;

import com.ciana.rule.engine.parse.entity.RuleNode;
/**
 * 空接口
 * @author admin
 *
 */
public class NoneRuleDataImpl implements RuleDataInterface{

	@Override
	public Map<String, Object> getData(RuleNode node, Map<String, Object> lastParams) throws Exception {
//		return lastParams;
		throw new Exception("没有实现的类型");
	}

}
