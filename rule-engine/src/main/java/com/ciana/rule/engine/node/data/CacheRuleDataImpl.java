package com.ciana.rule.engine.node.data;

import java.util.Map;

import com.ciana.rule.engine.parse.entity.RuleNode;

/**
 * 从公共缓存中获取
 * @author admin
 *
 */
public class CacheRuleDataImpl implements RuleDataInterface {

	@Override
	public Map<String, Object> getData(RuleNode node, Map<String, Object> lastParams) throws Exception {
		// TODO 暂不实现具体
		return lastParams;
	}

}
