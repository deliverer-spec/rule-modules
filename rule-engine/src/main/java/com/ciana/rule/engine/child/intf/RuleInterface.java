package com.ciana.rule.engine.child.intf;

import com.ciana.rule.engine.entity.Rule;

/**
 * 查询子规则信息 
 * 调用rule-engine时需要实现其接口
 * 需要在engine.properties中新增 rule.engine.ruleimpl参数
 * @author admin
 *
 */
public interface RuleInterface {

	/**
	 * 根据code 和version 查询返回子规则
	 * @param code
	 * @param version
	 * @param develop 是否开发
	 * @return
	 */
	public Rule getChildRule(String code, String version, Boolean develop) throws Exception;
}
