package com.ciana.rule.demo.controller;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ciana.rule.demo.entity.RuleInfo;
import com.ciana.rule.demo.mvc.Response;
import com.ciana.rule.demo.service.RuleService;
import com.ciana.rule.engine.entity.Rule;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("rule")
public class RuleInfoController {

	@Autowired
	private RuleService ruleService;

	/**
	 * 添加
	 * 
	 * @param ruleInfo
	 * @return
	 */
	@PostMapping("add")
	@ResponseBody
	public Response addRule(@Valid @RequestBody RuleInfo ruleInfo) {
		return Response.success(ruleService.insert(ruleInfo));
	}

	/**
	 * 更新
	 * 
	 * @param ruleInfo
	 * @return
	 */
	@PutMapping("update")
	@ResponseBody
	public Response updateRule(@Valid @RequestBody RuleInfo ruleInfo) {
		return Response.success(ruleService.updateById(ruleInfo));
	}

	/**
	 * 保存编辑规则,兼容insert和update
	 * 
	 * @param ruleJson
	 * @return
	 */
	@PostMapping("save")
	@ResponseBody
	public Response save(@RequestBody String ruleJson) {
		log.debug(ruleJson);

		JSONObject obj = JSON.parseObject(ruleJson);
		String rule_obj = obj.getString("ruleJson");

		Rule rule = ruleService.parseRule(rule_obj);

		// 查询最新的版本
		RuleInfo entity = ruleService.selectById(rule.getId());
		boolean add = false;
		if (entity == null) {
			entity = new RuleInfo();
			entity.setId(rule.getId());
			add = true;
		}

		// 这里只作为判断是否update
		entity.setName(rule.getName());
		entity.setCode(rule.getCode());
		entity.setRuletype(rule.getRuletype());
		entity.setDescript(rule.getDescript());
		entity.setRuleJson(rule_obj);
		entity.setOpTime(new Date());
		if (!add) {
			//TODO 应该在发布功能中增加版本号
			entity.setVersion(entity.getVersion() + 1);
			ruleService.updateById(entity);
		} else {
			// 生成一个默认版本号
			entity.setVersion(1);
			entity.setStatus(1);
			ruleService.insert(entity);
		}

		return Response.success(entity.getId());
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("del/{id}")
	@ResponseBody
	public Response delRule(@PathVariable String id) {
		return Response.success(ruleService.deleteById(id));
	}

	/**
	 * 加载
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("load/{id}")
	@ResponseBody
	public Response load(@PathVariable String id) {
		RuleInfo info = ruleService.selectById(id);
		return Response.success(info);
	}

	/**
	 * 查询全部
	 * 
	 * @return
	 */
	@GetMapping("list")
	@ResponseBody
	public Response list() {
		List<RuleInfo> list = ruleService.selectList(null);
		return Response.success(list);
	}

	/**
	 * 测试执行未发布规则
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "exec/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Response exec(@PathVariable String id) {
		RuleInfo rgInfo = ruleService.selectById(id);

		Map<String, Object> params = new LinkedHashMap<String, Object>();
		params.put("test", "5");
		params.put("a", 111);
		params.put("b", 1);
		params.put("bb", false);
		params.put("name", "rule1");
		params.put("detail", "1>2");

		Object obj = ruleService.execRule(rgInfo, params);

		return Response.success(obj);
	}

	/**
	 * 设计器检查语法
	 * 
	 * @param ruleJson
	 * @return
	 */
	@RequestMapping(value = "check", method = RequestMethod.POST)
	@ResponseBody
	public Response check(@RequestBody String ruleJson) {

		JSONObject jsonObject = JSON.parseObject(ruleJson);
		String rule_obj = jsonObject.getString("ruleJson");

		Object obj = ruleService.checkRule(rule_obj);

		return Response.success(obj);
	}
}