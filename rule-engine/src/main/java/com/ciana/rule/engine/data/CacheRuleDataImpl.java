package com.ciana.rule.engine.data;

import java.util.Map;

import com.ciana.rule.engine.parse.entity.RuleNode;

/**
 * 从公共缓存中获取
 * @author admin
 *
 */
public class CacheRuleDataImpl implements RuleDataInterface {

	@Override
	public Map<String, Object> getData(RuleNode node, Map<String, Object> last_params) throws Exception {
		// TODO 不知道如何实现
		return last_params;
	}

}
