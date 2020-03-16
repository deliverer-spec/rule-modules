/**
 * 
 */
package com.ciana.rule.engine.node.data;

/**
 * @author Administrator
 * @since 1.0
 */
public class DataUtil {
	
	private static String NONCLASS = "com.ciana.rule.engine.node.data.NoneRuleDataImpl";
	private static String DATA_DB_CLASS = "com.ciana.rule.engine.node.data.DBRuleDataImpl";
	private static String DATA_REST_CLASS = "com.ciana.rule.engine.node.data.RestRuleDataImpl";
	private static String DATA_INTF_CLASS = "com.ciana.rule.engine.node.data.IntfRuleDataImpl";
	private static String DATA_CACHE_CLASS = "com.ciana.rule.engine.node.data.CacheRuleDataImpl";
	
	private static String getClassName(String datatype) {
		if("sql".equals(datatype)) {
			return DATA_DB_CLASS;
		}
		if("reset".equals(datatype)) {
			return DATA_REST_CLASS;
		}
		if("cache".equals(datatype)) {
			return DATA_CACHE_CLASS;
		}
		if("intf".equals(datatype)) {
			return DATA_INTF_CLASS;
		}
		
		return NONCLASS;
	}
	
	public static RuleDataInterface getInstanceClass(String datatype) throws Exception {
		String classname = DataUtil.getClassName(datatype);
		Class cls = Class.forName(classname);
		return (RuleDataInterface)cls.newInstance();
	}
	
}
