package com.ciana.rule.engine.check.node;

import java.util.Map;

import com.ciana.rule.engine.check.CheckLog;
import com.ciana.rule.engine.parse.RuleParse;
import com.ciana.rule.engine.parse.entity.RuleNode;

public class DataCheck  implements CNInterface{

	@Override
	public void check(String id, RuleNode node, RuleParse parse, Map<String, Object> params, CheckLog log)
			throws Exception {

		//四种方式：直接查询，rest接口，缓存，自定义接口
		//定义： data-rows 在每一类中都必须存在
		//直接查询: 查询出来后从结果中再次取值并校验，查询条件需要处理
		//rest接口：返回json对象，从里面提取
		//缓存：获取缓存后从里面提取
		
	}

}
