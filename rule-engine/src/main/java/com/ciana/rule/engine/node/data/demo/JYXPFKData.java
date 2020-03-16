package com.ciana.rule.engine.node.data.demo;

import java.util.Map;

import com.ciana.rule.engine.node.data.RuleDataInterface;
import com.ciana.rule.engine.parse.entity.RuleNode;

public class JYXPFKData implements RuleDataInterface{

	@Override
	public Map<String, Object> getData(RuleNode node, Map<String, Object> lastParams) throws Exception {
		
		lastParams.put("age", "22");
		lastParams.put("sex", "男");
		lastParams.put("maritalStatus", "未婚");
		lastParams.put("Education", "本科及以上");
		lastParams.put("Localacc", "有");
		lastParams.put("Familyassets", "当地房产");
		lastParams.put("Licenseterm", "3");
		lastParams.put("Tobaccorank", "21");
		lastParams.put("quantitysize", "6");
		lastParams.put("Businesslife", "11");
		lastParams.put("Smokepinwater", "100000");
		lastParams.put("Otherstreams", "1000000");
		lastParams.put("geographical", "繁华街区");
		lastParams.put("Customersource", "物流推荐");
		lastParams.put("operatingstores", "自有门店");
		lastParams.put("Creditcard", "71");
		lastParams.put("djk", "true");
		lastParams.put("djtz", "true");
		lastParams.put("zws", "false");
		return lastParams;
	}

}
