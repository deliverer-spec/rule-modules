package com.ciana.rule.engine.node.block.demo;

import java.util.Map;

import com.ciana.rule.engine.log.RuleLogDtl;
import com.ciana.rule.engine.node.block.BlockInterface;

public class DemoBlock implements BlockInterface{

	@Override
	public Map<String, Object> initParams(Map<String, Object> req_params) throws Exception {
		// TODO Auto-generated method stub
		return req_params;
	}

	@Override
	public Object ifExec(Map<String, Object> params, RuleLogDtl dLog) throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object thenExec(Map<String, Object> params, RuleLogDtl dLog, Object obj) throws Exception {
		// TODO Auto-generated method stub
		return true;
	}
}
