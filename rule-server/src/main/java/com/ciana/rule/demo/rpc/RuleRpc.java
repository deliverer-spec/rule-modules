package com.ciana.rule.demo.rpc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ciana.rule.demo.service.RuleService;

@RequestMapping("rpc")
@RestController
public class RuleRpc {

	@Autowired
    private RuleService ruleService;
	
	
}
