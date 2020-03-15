package com.ciana.rule.demo.service;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.ciana.rule.demo.entity.RuleInfo;
import com.ciana.rule.engine.entity.Rule;


public interface RuleService extends IService<RuleInfo>{

	/**
	 * 格式化为rule实例
	 * @param ruleJson
	 * @return
	 */
	public Rule parseRule(String ruleJson);
	
	/**
	 * 测试执行规则
	 * @param rInfo
	 * @param params
	 * @return
	 */
	public Object execRule(RuleInfo rInfo, Map<String, Object> params);
	
	/**
	 * 设计器检查语法
	 * @param ruleJson
	 */
	public Object checkRule(String ruleJson);
	
}
