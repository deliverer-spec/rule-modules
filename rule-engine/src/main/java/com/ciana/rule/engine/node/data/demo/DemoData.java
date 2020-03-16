package com.ciana.rule.engine.node.data.demo;

import java.util.Map;

import com.ciana.rule.engine.node.data.RuleDataInterface;
import com.ciana.rule.engine.parse.entity.RuleNode;

/**
 * 数据接口demo
 * @author admin
 *
 */
public class DemoData implements RuleDataInterface{

	@Override
	public Map<String, Object> getData(RuleNode node, Map<String, Object> lastParams) throws Exception {
		
		lastParams.put("id", 123);
		lastParams.put("name", "demo");
		lastParams.put("age", 35);
		
		return lastParams;
	}

}
