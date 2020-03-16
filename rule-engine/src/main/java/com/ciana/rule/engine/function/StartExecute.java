package com.ciana.rule.engine.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ciana.rule.engine.function.intf.ExecInterface;
import com.ciana.rule.engine.log.RuleLogDtl;
import com.ciana.rule.engine.node.card.StarckEntity;
import com.ciana.rule.engine.parse.entity.Param;
import com.ciana.rule.engine.parse.entity.RuleNode;

public class StartExecute implements ExecInterface{

	/**
	 * start 节点
	 */
	@Override
	public Map<String, Object> initParams(RuleNode node, Map<String, Object> lastParams, Boolean isTest) throws Exception {
		List<Param> params_ = node.getParams();
		Map<String, Object> params = new HashMap<String, Object>();
		for(Param p: params_) {//检查配置的参数是否都有传值 
			if(lastParams.containsKey(p.getColum())) {
				//TODO 检查类型，格式化暂不实现
				params.put(p.getColum(), lastParams.get(p.getColum()));
				continue;
			}
			
			//如果是测试，增加取默认值
			if(isTest) {
				params.put(p.getColum(), p.getDefvaule());
			}
			
			System.out.println("没有传入必要的参数:"+p.getColum());
			//TODO 需要修改为强制拦截
		}
		return params;
	}

	@Override
	public Object ifExec(String ruleType, RuleNode node, Map<String, Object> params, RuleLogDtl dLog) throws Exception {
		return true;
	}

	@Override
	public Object thenExec(String ruleType, RuleNode node, Map<String, Object> params, RuleLogDtl dLog, Object obj) throws Exception {
		if("RCard".equals(ruleType)) {//取评分卡总分
			StarckEntity se = new StarckEntity();
			se.setType("T");
			se.setScore(Double.valueOf(node.getRuleContent()));
			se.setNodeName(node.getName());
			
			return se;
		}
		return true;
	}

}
