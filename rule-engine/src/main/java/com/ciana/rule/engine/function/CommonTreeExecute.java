package com.ciana.rule.engine.function;

import java.util.Map;

import com.ciana.rule.engine.log.RuleLogDtl;
import com.ciana.rule.engine.parse.entity.RuleNode;
import com.ciana.rule.engine.run.RuleRunner;

public class CommonTreeExecute implements ExecInterface{

	@Override
	public Map<String, Object> initParams(RuleNode node, Map<String, Object> last_params, Boolean isTest) throws Exception {
		return last_params;
	}
	
	@Override
	public Object ifExec(String ruleType, RuleNode node, Map<String, Object> params, RuleLogDtl dLog) throws Exception {
		
		if(node.getMatchCondition() == null || "".equals(node.getMatchCondition().trim())) {
			return false;  //设定缺省值默认为false
		}

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
		if(node.getRuleContent() == null || "".equals(node.getRuleContent().trim())) {
			return obj;
		}

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
