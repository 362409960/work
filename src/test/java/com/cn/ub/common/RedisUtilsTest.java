package com.cn.ub.common;

import org.junit.Test;

import junit.framework.TestCase;

public class RedisUtilsTest extends TestCase {

	@Test
	public void test() {
		RedisUtils.getJedis().set("aa", "123");
		System.out.println(RedisUtils.get("aa"));
	}

}
