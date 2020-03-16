package com.ciana.rule.engine.function;

import java.util.Map;

import com.ciana.rule.engine.function.intf.ExecInterface;
import com.ciana.rule.engine.log.RuleLogDtl;
import com.ciana.rule.engine.node.card.StarckEntity;
import com.ciana.rule.engine.parse.entity.RuleNode;
import com.ciana.rule.engine.run.RuleRunner;

public class CommonCardExecute implements ExecInterface{

	/*
	 * Common 规则执行节点
	
            规则条件： 在规则语法中属于  if 范围   规则动作：在规则语法中属于 then 范围
            当只输入条件时，返回值肯定是 true/false
                 规则条件   规则动作    
                输入       输入        规则满足时执行动作,返回动作结果
                输入       不输入      规则满足时，动作默认取条件返回结果返回
                不输入     输入        直接执行动作，返回接口不固定
                不输入     不输入       错误
         
        
	 */

	@Override
	public Map<String, Object> initParams(RuleNode node, Map<String, Object> lastParams, Boolean isTest) throws Exception {
		// TODO Auto-generated method stub
		return lastParams;
	}
	
	@Override
	public Object ifExec(String ruleType, RuleNode node, Map<String, Object> params, RuleLogDtl dLog) throws Exception {
		
		//card 判断，首先区分节点类型
		
		
		//TODO  当前处理只考虑 第三种情况，目前还没有场景要求必须分开处理
		if(node.getMatchCondition() == null || "".equals(node.getMatchCondition().trim())) {
			return false;  //设定缺省值默认为false
		}
//		System.out.println("执行语句："+node.toString());
		Object obj = RuleRunner.execute(node.getMatchCondition(), params);
//		System.out.print("  执行结果"+obj);
		return obj;
	}

	@Override
	public Object thenExec(String ruleType, RuleNode node, Map<String, Object> params, RuleLogDtl dLog, Object obj) throws Exception {
		// 判断类型
		if("D".equals(node.getCardNodeType())) {//D  汇总维度     设置一组维度占比比重
			StarckEntity se = new StarckEntity();
			se.setType("D");
			se.setScore(Double.valueOf(node.getRuleContent()));
			se.setNodeName(node.getName());
			
			return se;
		}
		
		if("W".equals(node.getCardNodeType())) {//W  字段维度      设置该字段最大分值
			StarckEntity se = new StarckEntity();
			se.setType("W");
			se.setScore(Double.valueOf(node.getRuleContent()));
			se.setNodeName(node.getName());
			
			return se;
		}
		if(node.getRuleContent() == null || "".equals(node.getRuleContent().trim())) {
			return obj;
		}
		
		if(obj instanceof Boolean) {
			if(!(Boolean)obj) {
				return obj; //如果条件为false,则不执行动作
			}
		}
		
//		System.out.println("执行语句："+node.toString());
		obj = RuleRunner.execute(node.getRuleContent(), params);
//		System.out.print("  执行结果"+obj);
		return obj;
	}

	
}
