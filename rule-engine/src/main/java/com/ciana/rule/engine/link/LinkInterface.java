package com.ciana.rule.engine.link;

import java.util.List;
import java.util.Map;

import com.ciana.rule.engine.parse.entity.RuleLink;
import com.ciana.rule.engine.parse.entity.RuleNode;

public interface LinkInterface {

	//分析子节点
	public Object syncLinks(String ruleType, RuleNode node, List<RuleLink> linkList, Map<String, Object> params, Object objIf, Object objThen) throws Exception;
}
