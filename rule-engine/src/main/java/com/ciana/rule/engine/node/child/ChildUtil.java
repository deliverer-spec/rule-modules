package com.ciana.rule.engine.node.child;

import com.ciana.rule.engine.config.Config;
import com.ciana.rule.engine.entity.Rule;
import com.ciana.rule.engine.node.child.intf.RuleInterface;

public class ChildUtil {

	/**
	 * 查询子规则
	 * @param code
	 * @param version
	 * @return
	 * @throws Exception
	 */
	public static Rule getChildRule(String code, String version, Boolean isDevp) throws Exception{
		
		String classname = Config.getInstance().getConfig("rule.engine.ruleimpl");
		if(classname == null) {
			throw new RuntimeException("需要实现RuleInterface 并配置到engine.properties中");
		}
		Class<?> clazz = Class.forName(classname);
		RuleInterface ruleIntf = (RuleInterface) clazz.newInstance();
		return ruleIntf.getChildRule(code, version, isDevp);
	}
	
}
