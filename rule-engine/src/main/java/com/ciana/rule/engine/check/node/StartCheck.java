package com.ciana.rule.engine.check.node;

import java.util.List;
import java.util.Map;

import com.ciana.rule.engine.check.CheckLog;
import com.ciana.rule.engine.parse.RuleParse;
import com.ciana.rule.engine.parse.entity.Param;
import com.ciana.rule.engine.parse.entity.RuleNode;

public class StartCheck implements CNInterface{

	@Override
	public void check(String id, RuleNode node, RuleParse parse, Map<String, Object> params, CheckLog log)
			throws Exception {
		List<Param> params_ = node.getParams();
		//将params_ 设置到params 中用于后续节点验证脚本
		for(Param p :params_) {
			//校验  类型， 预设值，是否重复定义
			//注意：param 必须是简单对象，使用时由接口传入值
			log.info(node, "参数："+p.getColum() +"="+p.getDefvaule()+" ;"+p.getColname()  + " "+ p.getColtype());
			if("boolean".equals(p.getColtype().toLowerCase())) {
				params.put(p.getColum(), Boolean.valueOf(p.getDefvaule()));
			}else {
				params.put(p.getColum(), p.getDefvaule());
			}
			
		}
	}

}
