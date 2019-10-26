package com.ass.manager.service.impl;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.ass.mapper.TUserMapper;
import com.ass.pojo.TUser;
import com.ass.pojo.TUserExample;
import com.ass.pojo.TUserExample.Criteria;


public class Realm extends AuthorizingRealm {

	@Autowired
	private TUserMapper tUserMapper;
	
	//认证方法
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		UsernamePasswordToken myToken=(UsernamePasswordToken) token;
		String username = myToken.getUsername();
		//根据用户名查询数据库中的密码
		TUserExample example=new TUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(username);
		List<TUser> list=tUserMapper.selectByExample(example);
		if(list.get(0)==null){
			//用户名不存在
			return  null;
		}
		//如果能查询到，再由框架比对数据库中查询到的密码和页面提交的密码是否一致
		AuthenticationInfo info=new SimpleAuthenticationInfo(list.get(0),list.get(0).getPassword(),this.getName());
		return info;
	}

	//授权方法
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		
		//获取当前登录用户对象
		TUser user = (TUser) principal.getPrimaryPrincipal();
		if(user.getName().equals("admin")){
			info.addStringPermission("super");
		}
		return info;
	}
}
