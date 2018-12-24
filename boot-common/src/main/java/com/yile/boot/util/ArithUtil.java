package com.yile.boot.util;

import java.math.BigDecimal;

/**
 * 浮点数运算工具类
 */
public class ArithUtil {

	//默认精度
	private static final int DEF_DIV_SCALE = 2;
	
	private ArithUtil() {}
	
	/**
	 * 加法
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}
	
	/**
	 * 减法
	 * @param v1 被减�?
	 * @param v2 减数
	 * @return
	 */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}
	
	/**
	 * 乘法
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}
	
	/**
	 * 除法（默认精度）
	 * @param v1 被除�?
	 * @param v2 除数
	 * @return
	 */
	public static double div(double v1, double v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}
	
	/**
	 * 除法（指定精度）
	 * @param v1 被除�?
	 * @param v2 除数
	 * @param scale 精度
	 * @return
	 */
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("精度应为正整数或0");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * 根据精度四舍五入
	 * @param v
	 * @param scale
	 * @return
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("精度应为正整数或0");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static void main(String[] args) {
		Double d1 = 0.0;
		Double d2 = 4.6;
		Integer d3 = 1;
		d1 = ArithUtil.sub(d2, d3);
		System.out.println(d1);
		
		Double a = 1.0;
		Double b = 3.0;
		Double c = 0.0;
		c = ArithUtil.div(a, b, 3);
		System.out.println(c);
		
		Double d = 1.465;
		System.out.println(ArithUtil.round(d, 2));
		
		Double m1 = 1.0;
		Double m2 = 2.6;
		Double m3 = 3.2;
		Double m4 = 1.19;
		Double m5 = ArithUtil.sub(ArithUtil.add(ArithUtil.add(m1, m2), m3), m4);
		System.out.println(m5);
		
		Double m11 = 1.0;
		Double m21 = 2.6;
		Double m31 = ArithUtil.mul(m11, m21);
		System.out.println(m31);
	}

}
