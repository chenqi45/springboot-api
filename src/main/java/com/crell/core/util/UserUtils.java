package com.crell.core.util;

import com.crell.common.model.User;

import java.util.List;

/**
 * 用户工具类
 * @author crell
 */
public class UserUtils {

	/**
	 * 获取当前用户
	 * @return 取不到返回 new User()
	 */
	public static User getUser(){
		// 如果没有登录，则返回实例化空的User对象。
		User user = new User();
		user.setId("1");
		return user;
	}
	
}
