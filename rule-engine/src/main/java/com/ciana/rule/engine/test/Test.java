package com.ciana.rule.engine.test;

import java.util.HashMap;
import java.util.Map;

import com.ciana.rule.engine.run.RuleRunner;

public class Test {
	
	public static void main(String[] args) throws Exception{
		//0<= a   && a <=10
		Map<String, Object> params = new HashMap<>();
		params.put("b", 40);
		Object obj = RuleRunner.execute("if( 0<=b && b<=10){return 10;}else if( 10<b && b<=100){return 50;}else return 100;", params);
		System.out.println(obj);
	}

}
