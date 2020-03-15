package com.ciana.rule.engine.check.node;

import java.util.Map;

import com.ciana.rule.engine.block.BlockInterface;
import com.ciana.rule.engine.check.CheckLog;
import com.ciana.rule.engine.parse.RuleParse;
import com.ciana.rule.engine.parse.entity.RuleNode;

public class BlockCheck  implements CNInterface{

	@Override
	public void check(String id, RuleNode node, RuleParse parse, Map<String, Object> params, CheckLog log)
			throws Exception {

		//字段：classname
		
		String classname = node.getIntfname();
		if(classname == null || "".equals(classname)) {
			log.error(node, "类路径不正确");
			return;
		}
		@SuppressWarnings("rawtypes")
		Class cls = Class.forName(classname);
		Object obj = cls.newInstance();
		if(obj instanceof BlockInterface) {
			
		}else {
			log.error(node, "必须实现BlockInterface接口");
		}
	}

}
