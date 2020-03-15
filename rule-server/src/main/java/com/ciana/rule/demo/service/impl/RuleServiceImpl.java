package com.ciana.rule.demo.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciana.rule.demo.entity.RuleInfo;
import com.ciana.rule.demo.mapper.RuleInfoMapper;
import com.ciana.rule.demo.service.RuleService;
import com.ciana.rule.engine.RuleEngine;
import com.ciana.rule.engine.check.CheckLog;
import com.ciana.rule.engine.check.RuleCheck;
import com.ciana.rule.engine.entity.Rule;
import com.ciana.rule.engine.log.RuleLog;
import com.ciana.rule.engine.parse.RuleParse;

import lombok.extern.slf4j.Slf4j;

@Service(value="RuleService")
@Slf4j
public class RuleServiceImpl  extends ServiceImpl<RuleInfoMapper, RuleInfo> implements RuleService{


	/**
	 * 格式化为rule实例
	 * @param ruleJson
	 * @return
	 */
	@Override
	public Rule parseRule(String ruleJson) {
		return new RuleEngine().getRule(ruleJson);
	}
	
	/**
	 * 测试执行规则
	 * @param rInfo
	 * @param params
	 * @return
	 */
	@Override
	public Object execRule(RuleInfo rInfo, Map<String, Object> params){
		Rule rule = new Rule();			
		BeanUtils.copyProperties(rInfo, rule);
		
		RuleEngine rengine = new RuleEngine();
		rengine.develop(rule, params);
		
		RuleLog ruleLog = rengine.getRlog();
		log.debug(JSON.toJSONString(ruleLog));
		
		return rengine.getObj();
	}
	
	/**
	 * 设计器检查语法
	 * @param ruleJson
	 */
	@Override
	public Object checkRule(String ruleJson) {
		RuleParse parse = new RuleParse(ruleJson);

		RuleCheck check = new RuleCheck();
		check.check(parse.getStartId(), parse, new HashMap<String, Object>());
		
		CheckLog log = check.getLog();
		//检查日志
		log.print();
		
		return log;
	}
}
