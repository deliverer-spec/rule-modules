package com.ciana.rule.engine.function;

import java.util.Map;

import com.ciana.rule.engine.function.intf.ExecInterface;
import com.ciana.rule.engine.log.RuleLogDtl;
import com.ciana.rule.engine.parse.entity.RuleNode;
import com.ciana.rule.engine.run.RuleRunner;

public class CommonSetExecute implements ExecInterface{

	@Override
	public Map<String, Object> initParams(RuleNode node, Map<String, Object> lastParams, Boolean isTest) throws Exception {
		return lastParams;
	}
	
	@Override
	public Object ifExec(String ruleType, RuleNode node, Map<String, Object> params, RuleLogDtl dLog) throws Exception {
		//TODO  当前处理只考虑 第三种情况，目前还没有场景要求必须分开处理
		if(node.getMatchCondition() == null || "".equals(node.getMatchCondition().trim())) {
			return false;  //设定缺省值默认为false
		}
//		System.out.println("执行语句："+node.toString());
		try {
			Object obj = RuleRunner.execute(node.getMatchCondition(), params);
			dLog.setObjIf(obj);
			dLog.setCode("success");
			return obj;
		} catch (Exception e) {
			//TODO  需要完善异常处理
			dLog.setCode("fail");
			dLog.setMessage(e.getMessage());
			throw e;
		}
	}

	@Override
	public Object thenExec(String ruleType, RuleNode node, Map<String, Object> params, RuleLogDtl dLog, Object obj) throws Exception {
		// TODO Auto-generated method stub
		if(node.getRuleContent() == null || "".equals(node.getRuleContent().trim())) {
			return obj;
		}
//		System.out.println("执行语句："+node.toString());
		if(obj instanceof Boolean) {
			if(!(Boolean)obj) {
				return obj; //如果条件为false,则不执行动作
			}
		}
		try {
			obj = RuleRunner.execute(node.getRuleContent(), params);
			
			dLog.setObjThen(obj);
			dLog.setCode("success");
			return obj;
		} catch (Exception e) {
			//TODO  需要完善异常处理
			dLog.setCode("fail");
			dLog.setMessage(e.getMessage());
			throw e;
		}
	}
}
