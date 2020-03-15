package com.ciana.rule.engine.link;

public class LinkUtil {

	private static String NOMAL_CLASS = "com.ciana.rule.engine.link.NomalLink";
	private static String CARD_CLASS = "com.ciana.rule.engine.link.CardLink";
	private static String SET_CLASS = "com.ciana.rule.engine.link.SetLink";
	private static String TREE_CLASS = "com.ciana.rule.engine.link.TreeLink";
	private static String TABLE_CLASS = "com.ciana.rule.engine.link.TableLink";
	
	private static String getClassName(String ruleType, String type) {
		//注意 节点没有特殊路由规则
		if("RCard".equals(ruleType)) {
			return CARD_CLASS;
		}
		if("RSet".equals(ruleType)) {
			return SET_CLASS;
		}
		if("RTree".equals(ruleType)) {
			return TREE_CLASS;
		}
		if("RTable".equals(ruleType)) {
			return TABLE_CLASS;
		}
		
		return NOMAL_CLASS;
	}
	
	public static LinkInterface getInstanceClass(String ruleType, String type) throws Exception {
		String classname = LinkUtil.getClassName(ruleType, type);
		Class cls = Class.forName(classname);
		return (LinkInterface)cls.newInstance();
	}
}
