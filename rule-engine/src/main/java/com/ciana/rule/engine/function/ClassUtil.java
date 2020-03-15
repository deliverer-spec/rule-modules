package com.ciana.rule.engine.function;

public class ClassUtil {

	private static String NON_CLASS = "com.ciana.rule.engine.function.NonExecute";
	private static String START_CLASS = "com.ciana.rule.engine.function.StartExecute";
	private static String COMMON_SET_CLASS = "com.ciana.rule.engine.function.CommonSetExecute";
	private static String COMMON_CARD_CLASS = "com.ciana.rule.engine.function.CommonCardExecute";
	private static String COMMON_TREE_CLASS = "com.ciana.rule.engine.function.CommonTreeExecute";
	private static String DATA_CLASS = "com.ciana.rule.engine.function.DataExecute";
	private static String BLOCK_CLASS = "com.ciana.rule.engine.function.BlockExecute";
	private static String CHILD_CLASS = "com.ciana.rule.engine.function.ChildExecute";
	
	private static String getClassName(String ruleType, String type) {
		if("start".equals(type)) {
			return START_CLASS;
		}
		if("common".equals(type)) {

			if("RSet".equals(ruleType)) {
				return COMMON_SET_CLASS;
			}else if("RCard".equals(ruleType)) {
				return COMMON_CARD_CLASS;
			}else if("RTree".equals(ruleType)) {
				return COMMON_TREE_CLASS;
			}else if("RTable".equals(ruleType)) {
				return COMMON_TREE_CLASS;
			}
		}
		if("data".equals(type)) {
			return DATA_CLASS;
		}

		if("block".equals(type)) {
			return BLOCK_CLASS;
		}

		if("child".equals(type)) {
			return CHILD_CLASS;
		}
		
		return NON_CLASS;
	}
	
	public static ExecInterface getInstanceClass(String ruleType, String type) throws Exception {
		String classname = ClassUtil.getClassName(ruleType, type);
		@SuppressWarnings("rawtypes")
		Class cls = Class.forName(classname);
		return (ExecInterface)cls.newInstance();
	}
}
