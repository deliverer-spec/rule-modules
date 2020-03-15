package com.ciana.rule.engine.entity;

import lombok.Data;

@Data
public class Rule {

	
    //规则集id
	private String id;
	
    //规则集名称
	private String name;
	
	
	//规则集编码
	private String code;
	
	//规则集描述
	private String descript;
	
	//规则集类型
	private String ruletype;
	
	//规则集状态（1：有效  0：无效）
	private Integer status;
	
	// 源码
	private String ruleJson;
	
	//版本号
	private String version;
	

}
