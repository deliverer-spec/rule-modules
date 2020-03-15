package com.ciana.rule.engine.check;

import com.ciana.rule.engine.check.node.CNInterface;

public class CheckUtil {

	private static String CHECK_NONE_CLASS = "com.ciana.rule.engine.check.node.CheckNone";
	private static String CHECK_START_CLASS = "com.ciana.rule.engine.check.node.StartCheck";
	private static String CHECK_COMM_RSET_CLASS = "com.ciana.rule.engine.check.node.CommonRSetCheck";
	private static String CHECK_COMM_CARD_CLASS = "com.ciana.rule.engine.check.node.CommonRCardCheck";
	private static String CHECK_COMM_TREE_CLASS = "com.ciana.rule.engine.check.node.CommonRTreeCheck";
	private static String CHECK_DATA_CLASS = "com.ciana.rule.engine.check.node.DataCheck";
	private static String CHECK_BLOCK_CLASS = "com.ciana.rule.engine.check.node.BlockCheck";
	private static String CHECK_CHILD_CLASS = "com.ciana.rule.engine.check.node.ChildCheck";
	
	private static String getClassName(String ruleType, String type) {
		if("start".equals(type)) {
			return CHECK_START_CLASS;
		}
		if("common".equals(type)) {
			
			if("RSet".equals(ruleType)) {
				return CHECK_COMM_RSET_CLASS;
			}else if("RCard".equals(ruleType)) {
				return CHECK_COMM_CARD_CLASS;
			}else if("RTree".equals(ruleType)) {
				return CHECK_COMM_TREE_CLASS;
			}else if("RTable".equals(ruleType)) {
				return CHECK_COMM_TREE_CLASS;
			}
		}
		if("data".equals(type)) {
			return CHECK_DATA_CLASS;
		}

		if("block".equals(type)) {
			return CHECK_BLOCK_CLASS;
		}

		if("child".equals(type)) {
			return CHECK_CHILD_CLASS;
		}
		
		return CHECK_NONE_CLASS;
	}
	
	public static CNInterface getInstanceClass(String ruleType, String type) throws Exception {
		String classname = CheckUtil.getClassName(ruleType, type);
		@SuppressWarnings("rawtypes")
		Class cls = Class.forName(classname);
		return (CNInterface)cls.newInstance();
	}
	
}
