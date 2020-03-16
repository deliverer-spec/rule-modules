package com.ciana.rule.engine.node.child;

import java.util.Map;

import com.ciana.rule.engine.entity.Rule;
import com.ciana.rule.engine.log.RuleLog;
import com.ciana.rule.engine.parse.RuleParse;
import com.ciana.rule.engine.parse.entity.RuleNode;
import com.ciana.rule.engine.run.RuleExecute;

public class RuleChild {
	
	public Object execChild(String ruleType, RuleNode node, Map<String, Object> params, Integer depth) throws Exception {
		//查子规则,解析
		Rule info = ChildUtil.getChildRule(node.getChildCode(), node.getVersion(), node.getDevp());
		RuleParse parse = new RuleParse(info.getRuleJson());
		
		
		RuleLog log = new RuleLog();
		log.setRuleGroupId(info.getId());
		log.setRuleCode(info.getCode());
		log.setVersion(info.getVersion());
		log.setRuleGroupName(info.getName());
//		log.setVersion(rInfo.getvser);
		log.setParams(params.toString());//需要转json
		
		//执行  返回执行日志
		parse.setRuleLog(log);
		parse.setDepth(depth+1);//本级的下一级
		
		RuleExecute exec = new RuleExecute();
		Object obj = exec.execute(parse.getStartId(),parse.getRuletype(), parse, params, log);
		
		return obj;
	}
}
