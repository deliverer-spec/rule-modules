package com.ciana.rule.demo.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 规则信息表
 * 
 * @author yinbo
 * @email 278253683@qq.com
 * @date 2019-09-30 18:04:51
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("rule_info")
public class RuleInfo extends Model<RuleInfo> implements Serializable {

	private static final long serialVersionUID = 1L;

	// 规则id
	@TableId(value = "id", type = IdType.UUID)
	private String id;

	// 规则集名称
	@TableField("name")
	private String name;

	// 规则集编码
	@TableField("code")
	private String code;

	// 版本号
	@TableField("version")
	private Integer version;

	// 规则集描述
	@TableField("descript")
	private String descript;

	// 规则集类型
	@TableField("ruletype")
	private String ruletype;

	// 规则集状态（1：有效 0：无效）
	@TableField("status")
	private Integer status;

	// 源码
	@TableField("resources")
	private String ruleJson;

	// 操作时间
	@TableField("op_time")
	private Date opTime;

	@Override
	protected Serializable pkVal() {
		return id;
	}

}
