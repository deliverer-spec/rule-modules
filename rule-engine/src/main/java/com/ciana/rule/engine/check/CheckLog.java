package com.ciana.rule.engine.check;

import java.util.ArrayList;
import java.util.List;

import com.ciana.rule.engine.parse.entity.RuleNode;

public class CheckLog {


	private List<String> clog = new ArrayList<String>();//校验信息
	private List<String> elog = new ArrayList<String>();//错误信息
	
	public void info(RuleNode node, String log) {
		
		clog.add(getS(node.getDepth())+log);
	}
	
	public void error(RuleNode node, String error) {
		info(node, "error  "+error);
		elog.add("error  "+error);
	}
	
	public List<String> getInfoLog(){
		return clog;
	}
	
	public List<String> getErrorLog() {
		return elog;
	}
	
	public void print() {
		for(String s:clog) {
			System.out.println(s);
		}
		System.out.println("---------------- error ------------");
		for(String s:elog) {
			System.out.println(s);
		}
	}
	
	private String getS(Integer depth) {
		if ( depth == null)  depth = 0;
		String msg = "";
		while(depth>0) {
			msg = msg +"    ";
			depth--;
		}
		
		return msg;
	}
}
