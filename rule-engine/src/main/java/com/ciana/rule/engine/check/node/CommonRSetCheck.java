package com.ciana.rule.engine.check.node;

import java.util.Map;

import com.ciana.rule.engine.check.CheckLog;
import com.ciana.rule.engine.parse.RuleParse;
import com.ciana.rule.engine.parse.entity.RuleNode;
import com.ciana.rule.engine.run.RuleRunner;

public class CommonRSetCheck implements CNInterface{

	@Override
	public void check(String id, RuleNode node, RuleParse parse, Map<String, Object> params, CheckLog log)
			throws Exception {
		
		log.info(node, node.getName()+" "+node.getMatchCondition()+" "+node.getRuleContent());
		String match = node.getMatchCondition();
		if(!(match == null || "".equals(match))) {
			//条件允许为空，默认条件返回为 true
			try {
				Object obj = RuleRunner.execute(match, params);
				boolean b = (obj instanceof Boolean);
				if(b) {
					log.info(node, "条件执行成功："+match+"="+obj+";返回布尔值");
				}else{
					log.error(node, "条件执行成功："+match+"="+obj+";返回值类型错误:"+obj.getClass().getName());
				}
				
			} catch (Exception e) {
				log.error(node, "条件节点错误:  "+match+" :缺参数或者语法错误");
//				e.printStackTrace();
			}
		}else {
			log.info(node, "条件为空，默认返回true");
		}
		
		String content = node.getRuleContent();
		if(!(content == null || "".equals(content))) {
			//条件允许为空，默认条件返回为 true
			try {
				Object obj = RuleRunner.execute(content, params);
				//这里需要分析结构类型：RSet 返回布尔值；RTree/RTable 返回任意；TCard 返回数值
				boolean b = (obj instanceof Boolean);
				if(b) {
					log.info(node, "动作执行成功："+content+"="+obj+";返回布尔值");
				}else{
					log.error(node, "动作执行成功："+content+"="+obj+";返回值类型错误:"+obj.getClass().getName());
				}
				
			} catch (Exception e) {
				log.error(node, "动作节点错误:  "+content+" :缺参数或者语法错误");
//				e.printStackTrace();
			}
		}else {
			log.info(node, "条件为空，默认返回true");
		}
	}

}
