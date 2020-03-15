package com.ciana.rule.engine.run;

import java.util.Map;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;

public class RuleRunner {
	
	//表达式执行器
    private static ExpressRunner runner = new ExpressRunner();
    
    /**
     * 规则运算
     * @param express
     * @param context
     * @return
     * @throws Exception
     */
    public static Object execute(String express, DefaultContext<String, Object> context) throws Exception {
    	Object obj = runner.execute(express, context, null,true, false);
    	
    	return obj;
    }
    
    /**
     * 规则运算
     * @param express
     * @param context
     * @return
     * @throws Exception
     */
    public static Object execute(String express, Map<String, Object> map) throws Exception {
    	DefaultContext<String, Object> context = new DefaultContext<String, Object>();
    	context.putAll(map);
    	
    	Object obj = runner.execute(express, context, null,true, false);
    	
    	return obj;
    }

    public static Object execute(String express) throws Exception {
    	DefaultContext<String, Object> context = new DefaultContext<String, Object>();
    	
    	Object obj = runner.execute(express, context, null,true, false);
    	
    	return obj;
    }
}
