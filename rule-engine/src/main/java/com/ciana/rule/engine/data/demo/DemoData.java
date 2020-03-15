package com.ciana.rule.engine.data.demo;

import java.util.Map;

import com.ciana.rule.engine.data.RuleDataInterface;
import com.ciana.rule.engine.parse.entity.RuleNode;

/**
 * 数据接口demo
 * @author admin
 *
 */
public class DemoData implements RuleDataInterface{

	@Override
	public Map<String, Object> getData(RuleNode node, Map<String, Object> last_params) throws Exception {
		
		last_params.put("id", 123);
		last_params.put("name", "demo");
		last_params.put("age", 35);
		
		return last_params;
	}

}
