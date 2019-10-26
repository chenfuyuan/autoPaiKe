package com.ass.manager.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ass.manager.service.AdminService;
import com.ass.pojo.TUser;

/**
 * 登录处理
 * @author chenfuyuan
 *
 */

@Controller
public class LoginController {
	
	@Autowired
	private AdminService adminService;

	@RequestMapping("/login")
	public String login(HttpServletRequest request,TUser t,Model model){
		//获取数据
		String checkcode=request.getParameter("checkcode");
		//获取验证码
		String key = (String) request.getSession().getAttribute("key");
		//判断验证码是否正确
		if(StringUtils.isNotBlank(checkcode) && checkcode.equals(key)){
			//获得当前登录对象，状态为“未认证”
			Subject subject = SecurityUtils.getSubject();
			//用户名密码令牌
			AuthenticationToken token=new UsernamePasswordToken(t.getName(),DigestUtils.md5DigestAsHex(t.getPassword().getBytes()));
			try {
				subject.login(token);
				TUser user = (TUser) subject.getPrincipal();
				request.getSession().setAttribute("user", user);
				return "redirect:/index";
			} catch (Exception e) {
				model.addAttribute("error", "用户名或密码错误");
				return "login";
			}
		}else if(StringUtils.isBlank(checkcode)){
			return "login";
		}else {
			model.addAttribute("error", "验证码错误");
			return "login";
		}
	}
	
	//用户注销
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request){
		request.getSession().setAttribute("user", null);
		return "login";
	}
	
	//修改密码
	@RequestMapping("/editPassword")
	public String editPassword(HttpServletRequest request,String password){
		TUser user = (TUser) request.getSession().getAttribute("user");
		user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
		adminService.editPassword(user);
		return "edit-Password";
	}
	
}
