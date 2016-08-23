package cn.cetelem.des.expression.engine.library;

import org.apache.commons.lang.StringUtils;

import cn.cetelem.des.expression.sign.impl.Judge;

/**
 * 
 * @author flaki
 * @date 2016年6月1日
 * @type 适用于自定义引擎的模版,仅供参考
 * @version 1.0
 * @email wysznb@hotmail.com
 *
 */
public enum BasicModel {
	MODEL_EQ("object", Judge.EQ, "object"), MODEL_GT("object", Judge.GT,
			"object"), MODEL_GTAE("object", Judge.GTAE, "object"), MODEL_LT(
			"object", Judge.LT, "object"), MODEL_LTAE("object", Judge.LTAE,
			"object");
	/**
	 * 左对象
	 */
	private final String left;
	/**
	 * 右对象
	 */
	private final String right;
	/**
	 * 逻辑表达式
	 */
	private final Judge sign;

	private BasicModel(String left, Judge sign, String right) {
		this.left = left;
		this.sign = sign;
		this.right = right;
	}

	public String getLeft() {
		return left;
	}

	public String getRight() {
		return right;
	}

	public Judge getSign() {
		return sign;
	}
	
	/**
	 * 
	 * 获取对应模版的方法
	 * 
	 */
	private static BasicModel getBasicModel(String el) {
		return BasicModel.valueOf("MODEL_"
				+ StringUtils.upperCase(el.trim().split(" ")[1]));

	}

	private static String getLeft(String el) {
		return el.trim().split(" ")[0];
	}

	private static String getRight(String el) {
		return el.trim().split(" ")[0];
	}
	/**
	 * 处理流程
	 */
	public static String judge(String el, Object object) {
		String left = getLeft(el);
		String right = getRight(el);
		switch (BasicModel.getBasicModel(el)) {
		case MODEL_EQ:
			left = initToString(left, object);
			right = initToString(right, object);
			break;
		default:
			break;
		}
		//  未完成
		return el;

	}
	
	/**
	 * 数字判断器
	 * 
	 */
	private static String initToString(String string, Object object) {
		if (string.indexOf("'") > -1) {
			return string.split("'")[1];
		} else if (checkNumber(string)) {
			
		}
		return null;
	}

	/**
	 * 数字判断方法
	 * 
	 */
	private static boolean checkNumber(String string) {
		if(StringUtils.isEmpty(string)){
			return false;
		}
		return string.matches("\\d*(.\\d+)?");
	}
	

}
