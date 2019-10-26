package com.ass.manager.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ass.common.pojo.EasyUIDataGridResult;
import com.ass.common.utils.FileUtils;
import com.ass.common.utils.JsonUtils;
import com.ass.manager.service.DepartmentService;
import com.ass.pojo.TDepartment;

/**
 * 专业信息管理Controller
 * @author chenfuyuan
 *
 */

@Controller
@RequestMapping("/department")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@Value("${currentPageCount}")
	private Integer currentPageCount;
	
	//添加专业信息
	@RequestMapping("/add")
	public String add(TDepartment tDepartment) {
		//根据是否传来ID值判断是应修改还是添加专业信息
		if(tDepartment.getId()!=null){
			departmentService.editDepartment(tDepartment);
		}else{
			departmentService.addDepartment(tDepartment);			
		}
		return "redirect:/department/findAll/1";
	}
	
	//分页查询所有专业信息
	@RequestMapping("/findAll/{currentPage}")
	public String findListDepartment(Model model,@PathVariable("currentPage")Integer currentPage) {
		if(currentPage==null){
			currentPage=1;
		}
		int currentCount=currentPageCount;		//设置每页显示的条数
		EasyUIDataGridResult result=departmentService.findListDepartment(currentPage,currentCount);
		int totalPage=(int) Math.ceil(1.0*result.getTotal()/currentCount);		//计算总页数
		model.addAttribute("result",result);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPage", totalPage);
		return "department-list";
	}
	
	//跳转到修改信息页面
	@RequestMapping("/edit/{id}")
	public String toEdit(Model model,@PathVariable("id")Integer id) {
		TDepartment tDepartment=departmentService.getDepartment(id);
		model.addAttribute("tDepartment", tDepartment);
		return "department-add";
	}
	
	//删除单个专业信息
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id")Integer id) {
		departmentService.deleteById(id);
		return "redirect:/department/findAll/1";
	}
	
	//批量删除
	@RequestMapping("/deleteMore/{ids}")
	public String deleteMore(@PathVariable("ids")Integer[] ids) {
		for (Integer id : ids) {
			if(id==null){
				break;
			}
			departmentService.deleteById(id);
		}
		return "redirect:/department/findAll/1";
	}
	
	//根据专业名模糊查询专业信息
	@RequestMapping("/search/{currentPage}")
	public String searchDepartment(Model model,@PathVariable("currentPage")Integer currentPage,@RequestParam String words) {
		try {
			words=new String(words.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(currentPage==null){
			currentPage=1;
		}
		int currentCount=currentPageCount;		//设置每页显示的条数
		EasyUIDataGridResult result=departmentService.searchDepartment(words,currentPage,currentCount);
		int totalPage=(int) Math.ceil(1.0*result.getTotal()/currentCount);		//计算总页数
		model.addAttribute("result",result);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPage", totalPage);
		return "department-list";	
	}
	
	//根据获取的内容模糊查询专业名称
	@RequestMapping("/search")
	@ResponseBody
	public String searchDepartmentName(Model model,@RequestParam String word) {
		List<Object> departmentName=null;
		departmentName=departmentService.searchDepartmentName(word);
		String json = JsonUtils.objectToJson(departmentName);
		return json;
	}
	
	//数据表格导入
	@RequestMapping("/import")
	public String importExl(@RequestParam MultipartFile departmentFile,HttpServletRequest request) throws IOException {
		String filePath = departmentFile.getOriginalFilename();				//获取上传文件名
		//将上传的文件转为文件输入流
		MultipartHttpServletRequest mulRequest = (MultipartHttpServletRequest) request;  
		MultipartFile file = mulRequest.getFile("departmentFile");
		InputStream is = file.getInputStream();
		//根据文件名的后缀判断采取哪种POI方式进行解析
		Workbook hssfWorkbook = null;
		if (filePath.endsWith("xlsx")){
			hssfWorkbook = new XSSFWorkbook(is);//Excel 2007
		}else if (filePath.endsWith("xls")){
			hssfWorkbook = new HSSFWorkbook(is);//Excel 2003
		}
		List<TDepartment> list=new ArrayList<TDepartment>();
		//使用POI解析Excel文件
		
		//获得xml指定的页的数据
		Sheet hssfSheet = hssfWorkbook.getSheet("专业信息");
		//遍历数据（行）
		for (Row row : hssfSheet) {
				//获得行数，第一行跳过，不取其数据
				int num = row.getRowNum();
				if(num==0){
					continue;
				}
				//获取每列的数据,并封装到对象中
				String name  = row.getCell(0).getStringCellValue();
				String dean  = row.getCell(1).getStringCellValue();
				String explains  = row.getCell(2).getStringCellValue();
				TDepartment tDepartment=new TDepartment(name, dean, explains, (byte) 1, new Date(), new Date());
				//将获取的对象添加到list集合中
				list.add(tDepartment);
			}	
			//批量保存
			departmentService.saveBatch(list);
			return "redirect:/department/findAll/1";
	}
	
	//数据表格导出
	@RequestMapping("/export")
	public void exportExl(HttpServletRequest request,HttpServletResponse response) {
		//第一步：查询所有的专业数据
		List<TDepartment> list = departmentService.findAll();
				
		//第二步：使用POI将数据写到Excel文件中
		//在内存中创建一个Excel文件
		HSSFWorkbook workbook = new HSSFWorkbook();
		//创建一个标签页
		HSSFSheet sheet = workbook.createSheet("专业信息");
		//创建标题行
		HSSFRow headRow = sheet.createRow(0);
		headRow.createCell(0).setCellValue("专业代码");
		headRow.createCell(1).setCellValue("专业名称");
		headRow.createCell(2).setCellValue("负责人");
		headRow.createCell(3).setCellValue("专业说明");
				
		for (TDepartment department : list) {
			HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
			dataRow.createCell(0).setCellValue(department.getId());
			dataRow.createCell(1).setCellValue(department.getName());
			dataRow.createCell(2).setCellValue(department.getDean());
			dataRow.createCell(3).setCellValue(department.getExplains());
		}
				
		try {
			//第三步：使用输出流进行文件下载（一个流、两个头）
			String filename = "专业信息.xls";
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
	
	//Ajax查询所有专业信息
	@RequestMapping("/findAllAjax")
	@ResponseBody
	public List<TDepartment> findAllAjax() {
		List<TDepartment> list = departmentService.findAll();
		return list;
	}
}
