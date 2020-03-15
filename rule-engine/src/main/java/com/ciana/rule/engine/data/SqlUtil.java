package com.ciana.rule.engine.data;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ciana.rule.engine.config.Config;
import com.ciana.rule.engine.data.intf.ConnInterface;
import com.ciana.rule.engine.parse.entity.RuleNode;

public class SqlUtil {
	
	
	private static String left ="#{";
	private static String right ="}";
	
	/**
	 * 根据数据源名称查询数据库中配置的数据库链接
	 * @param node
	 * @return
	 * @throws Exception 
	 */
	public static Connection getconn(RuleNode node) throws Exception {
		String classname = Config.getInstance().getConfig("rule.engine.connimpl");
		if(classname == null) {
			throw new RuntimeException("需要实现ConnInterface 并配置到engine.properties中");
		}
		Class<?> clazz = Class.forName(classname);
		ConnInterface connIntf = (ConnInterface) clazz.newInstance();
		return connIntf.getconn(node.getDataSourceName());
	}
	/**
	 * 解析sql
	 * @param sql
	 * @return
	 */
	public static Map<String,Object> getAnalysisSql(String str){
		List<String> paramName = new ArrayList<>();
		while(true) {
			int a =str.indexOf(left);
			int b =str.indexOf(right,a+left.length());
			if(a<b) {
				String param = str.substring(a+left.length(),b);
				paramName.add(param);
				str = str.replace(left+param+right, "?");
			}else {
				break;
			}
		}
		Map<String, Object> result = new HashMap<>();
		result.put("sql", str);
		result.put("param", paramName);
		return result;
	}
	
}
