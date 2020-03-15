package com.ciana.rule.engine.link;

import java.util.List;
import java.util.Map;

import com.ciana.rule.engine.parse.entity.RuleLink;
import com.ciana.rule.engine.parse.entity.RuleNode;

public class NomalLink implements LinkInterface{

	/**
	 * 一般情况下的路由规则
	 */
	@Override
	public Object syncLinks(String ruleType, RuleNode node, List<RuleLink> linkList, Map<String, Object> params,
			Object objIf, Object objThen) throws Exception {
		if(linkList==null || linkList.size()==0 ) {//叶子节点
			return null;
		}
		
		return linkList;
	}

}
