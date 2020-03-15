package com.ciana.rule.engine.child;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ciana.rule.engine.entity.Rule;
import com.ciana.rule.engine.parse.RuleParse;
import com.ciana.rule.engine.parse.entity.RuleLink;
import com.ciana.rule.engine.parse.entity.RuleNode;

public class FindAllChild {

	public List<Map<String, String>> findAllChild(RuleParse parse) throws Exception {
		
		List<Map<String, String>> childList = new ArrayList<>();
		
		findByNode(parse, parse.getStartId(), childList);
		
		return childList;
	}
	
	private void findByNode(RuleParse parse, String id, List<Map<String, String>> childList) throws Exception {
		RuleNode node = parse.getRuleNode(id);
		//查询子规则
		if("child".equals(node.getType())) {
			//如果是child 查询
			Rule info = ChildUtil.getChildRule(node.getChildCode(), node.getVersion(), node.getDevp());
			
			Map<String, String> cmap = new HashMap<>();
			cmap.put("code", node.getChildCode());
			cmap.put("version", node.getVersion());
			childList.add(cmap);
			
			findChildsChild(info, childList);
		}
		
		List<RuleLink> links = parse.getRuleLinkList(node.getId());
		if(links != null) {
			for(RuleLink link: links) {
				findByNode(parse, link.getTargetId(), childList);
			}
		}
	}
	
	private void findChildsChild(Rule info, List<Map<String, String>> childList) throws Exception {
		RuleParse parse = new RuleParse(info.getRuleJson());
		parse.setDevp(true);
		
		List<Map<String, String>> listc = findAllChild(parse);
		if(listc != null) {
			childList.addAll(listc);
		}
	}
}
