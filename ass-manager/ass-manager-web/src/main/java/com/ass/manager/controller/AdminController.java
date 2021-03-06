package com.ass.manager.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ass.common.pojo.EasyUIDataGridResult;
import com.ass.common.utils.FileUtils;
import com.ass.common.utils.JsonUtils;
import com.ass.manager.service.AdminService;
import com.ass.pojo.TUser;


/**
 * 管理员信息管理Controller
 * @author chenfuyuan
 *
 */

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Value("${currentPageCount}")
	private Integer currentPageCount;
	
	//添加信息
	@RequestMapping("/add")
	public String add(TUser t,HttpServletRequest request) {
		//根据是否传来ID值判断是应修改还是添加信息
		if(t.getId()!=null){
			adminService.edit(t);
		}else{
			adminService.add(t);			
		}
		return "redirect:/admin/findAll/1";
	}
	
	//分页查询所有信息
	@RequestMapping("/findAll/{currentPage}")
	public String findList(Model model,@PathVariable("currentPage")Integer currentPage) {
		if(currentPage==null){
			currentPage=1;
		}
		Integer currentCount=currentPageCount;		//设置每页显示的条数
		EasyUIDataGridResult result=adminService.findList(currentPage,currentCount);
		int totalPage=(int) Math.ceil(1.0*result.getTotal()/currentCount);		//计算总页数
		model.addAttribute("result",result);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPage", totalPage);
		return "admin-list";
	}
	
	//跳转到修改信息页面
	@RequestMapping("/edit/{id}")
	public String toEdit(Model model,@PathVariable("id")Integer id) {
		TUser t=adminService.get(id);
		model.addAttribute("message", t);
		return "admin-add";
	}
	
	//删除单个信息
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id")Integer id) {
		adminService.deleteById(id);
		return "redirect:/admin/findAll/1";
	}
	
	//批量删除
	@RequestMapping("/deleteMore/{ids}")
	public String deleteMore(@PathVariable("ids")Integer[] ids) {
		for (Integer id : ids) {
			if(id==null){
				break;
			}
			adminService.deleteById(id);
		}
		return "redirect:/admin/findAll/1";
	}
	
	//根据名称模糊查询信息
	@RequestMapping("/search/{currentPage}")
	public String search(Model model,@PathVariable("currentPage")Integer currentPage,@RequestParam String words) {
		try {
			words=new String(words.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(currentPage==null){
			currentPage=1;
		}
		int currentCount=currentPageCount;		//设置每页显示的条数
		EasyUIDataGridResult result=adminService.search(words,currentPage,currentCount);
		int totalPage=(int) Math.ceil(1.0*result.getTotal()/currentCount);		//计算总页数
		model.addAttribute("result",result);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPage", totalPage);
		return "admin-list";	
	}
	
	//根据获取的内容模糊查询名称
	@RequestMapping("/search")
	@ResponseBody
	public String searchName(Model model,@RequestParam String word) {
		List<Object> Name=null;
		Name=adminService.searchName(word);
		String json = JsonUtils.objectToJson(Name);
		return json;
	}
	
	//数据表格导出
	@RequestMapping("/export")
	public void exportExl(HttpServletRequest request,HttpServletResponse response) {
		//第一步：查询所有的专业数据
		List<TUser> list = adminService.findAll();
				
		//第二步：使用POI将数据写到Excel文件中
		//在内存中创建一个Excel文件
		HSSFWorkbook workbook = new HSSFWorkbook();
		//创建一个标签页
		HSSFSheet sheet = workbook.createSheet("管理员信息");
		//创建标题行
		HSSFRow headRow = sheet.createRow(0);
		headRow.createCell(0).setCellValue("管理员代码");
		headRow.createCell(1).setCellValue("管理员名称");
		headRow.createCell(2).setCellValue("联系电话");
		headRow.createCell(3).setCellValue("家庭住址");
				
		for (TUser t : list) {
			HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
			dataRow.createCell(0).setCellValue(t.getId());
			dataRow.createCell(1).setCellValue(t.getName());
			dataRow.createCell(2).setCellValue(t.getPhone());
			dataRow.createCell(3).setCellValue(t.getAddress());
		}
				
		try {
			//第三步：使用输出流进行文件下载（一个流、两个头）
			String filename = "管理员信息.xls";
			ServletContext servletContext = request.getSession().getServletContext();
			String contentType = servletContext.getMimeType(filename);
			ServletOutputStream out;
			out = response.getOutputStream();
			response.setContentType(contentType);
			
			//获取客户端浏览器类型
			String agent = request.getHeader("User-Agent");
			filename = FileUtils.encodeDownloadFilename(filename, agent);
			response.setHeader("content-disposition", "attachment;filename="+filename);
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
