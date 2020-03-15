package com.ciana.rule.engine.data.demo;

import java.util.Map;

import com.ciana.rule.engine.data.RuleDataInterface;
import com.ciana.rule.engine.parse.entity.RuleNode;

public class JYXPFKData implements RuleDataInterface{

	@Override
	public Map<String, Object> getData(RuleNode node, Map<String, Object> last_params) throws Exception {
		
		last_params.put("age", "22");
		last_params.put("sex", "男");
		last_params.put("maritalStatus", "未婚");
		last_params.put("Education", "本科及以上");
		last_params.put("Localacc", "有");
		last_params.put("Familyassets", "当地房产");
		last_params.put("Licenseterm", "3");
		last_params.put("Tobaccorank", "21");
		last_params.put("quantitysize", "6");
		last_params.put("Businesslife", "11");
		last_params.put("Smokepinwater", "100000");
		last_params.put("Otherstreams", "1000000");
		last_params.put("geographical", "繁华街区");
		last_params.put("Customersource", "物流推荐");
		last_params.put("operatingstores", "自有门店");
		last_params.put("Creditcard", "71");
		last_params.put("djk", "true");
		last_params.put("djtz", "true");
		last_params.put("zws", "false");
		return last_params;
	}

}
