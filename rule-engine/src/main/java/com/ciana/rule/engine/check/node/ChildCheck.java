package com.ciana.rule.engine.check.node;

import java.util.Map;

import com.ciana.rule.engine.check.CheckLog;
import com.ciana.rule.engine.node.child.ChildUtil;
import com.ciana.rule.engine.entity.Rule;
import com.ciana.rule.engine.parse.RuleParse;
import com.ciana.rule.engine.parse.entity.RuleNode;

public class ChildCheck  implements CNInterface{

	@Override
	public void check(String id, RuleNode node, RuleParse parse, Map<String, Object> params, CheckLog log)
			throws Exception {

		//字段   childcode , childname , version
		String childcode = node.getChildCode();
		String version = node.getVersion();
		if(childcode == null || "".equals(childcode)) {
			log.error(node, "子规则编码不能为空");
		}
		if(version == null || "".equals(version)) {
			log.error(node, "子规则版本号不能为空");
		}
		//是否存在，需要接口去查询
		Rule info = ChildUtil.getChildRule(node.getChildCode(), node.getVersion(), node.getDevp());
		if(info == null) {
			log.error(node, "子规则不存在");
		}
	}

}
