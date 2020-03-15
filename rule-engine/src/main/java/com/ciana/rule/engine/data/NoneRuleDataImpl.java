package com.ciana.rule.engine.data;

import java.util.Map;

import com.ciana.rule.engine.parse.entity.RuleNode;
/**
 * 空接口
 * @author admin
 *
 */
public class NoneRuleDataImpl implements RuleDataInterface{

	@Override
	public Map<String, Object> getData(RuleNode node, Map<String, Object> last_params) throws Exception {
//		return last_params;
		throw new Exception("没有实现的类型");
	}

}
