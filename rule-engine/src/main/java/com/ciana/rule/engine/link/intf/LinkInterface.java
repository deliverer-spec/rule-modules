package com.ciana.rule.engine.link.intf;

import java.util.List;
import java.util.Map;

import com.ciana.rule.engine.parse.entity.RuleLink;
import com.ciana.rule.engine.parse.entity.RuleNode;

/**
 * 定义子节点分析接口
 * @author yinbo
 * @date: 2019/06/16 14:21
 */
public interface LinkInterface {

	/**
	 * 分析子节点
	 * @param ruleType
	 * @param node
	 * @param linkList
	 * @param params
	 * @param objIf
	 * @param objThen
	 * @return
	 * @throws Exception
	 */
	public Object syncLinks(String ruleType, RuleNode node, List<RuleLink> linkList, Map<String, Object> params, Object objIf, Object objThen) throws Exception;
}
