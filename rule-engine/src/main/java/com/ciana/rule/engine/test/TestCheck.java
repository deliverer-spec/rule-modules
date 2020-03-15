package com.ciana.rule.engine.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import com.ciana.rule.engine.check.CheckLog;
import com.ciana.rule.engine.check.RuleCheck;
import com.ciana.rule.engine.parse.RuleParse;

public class TestCheck {
	
	public static void main(String[] args)throws Exception {
		RuleCheck rc = new RuleCheck();
		String json = readTxt("f:/test.txt");
		RuleParse parse = new RuleParse(json);
		Map params = new HashMap();
		rc.check(parse.getStartId(), parse, params);
		
		CheckLog log = rc.getLog();
		log.print();
	}

	private static String readTxt(String file) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(new File(file)));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			System.out.println(line);
			sb.append(line);
		}
		
		return sb.toString();
	}
}
