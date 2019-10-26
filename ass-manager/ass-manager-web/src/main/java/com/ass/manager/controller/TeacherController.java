package com.ass.manager.controller;

import com.ass.common.pojo.EasyUIDataGridResult;
import com.ass.common.utils.FileUtils;
import com.ass.common.utils.JsonUtils;
import com.ass.manager.service.TeacherService;
import com.ass.pojo.TCourseTeacher;
import com.ass.pojo.TTeacher;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 教师信息管理Controller
 * @author LXC
 *
 */

@Controller
@RequestMapping("/teacher")
public class TeacherController {
	
	@Autowired
	private TeacherService teacherService;
	
	@Value("${currentPageCount}")
	private Integer currentPageCount;
	
	//添加信息
	@RequestMapping("/add")
	public String add(TTeacher t,HttpServletRequest request) {
		Integer departmentid = Integer.parseInt(request.getParameter("departmentId"));
		t.setDepartmentid(departmentid);
		String[] courseIds = request.getParameterValues("courseId");
		//根据是否传来ID值判断是应修改还是添加信息
		if(StringUtils.isNotBlank(t.getId())){
			teacherService.deleteTC(t.getId());
			teacherService.edit(t);
			for (String courseId : courseIds) {
				if(StringUtils.isBlank(courseId)){
					break;
				}
				teacherService.insertTC(Integer.parseInt(courseId),t);
			}
		}else{
			TTeacher teacher = teacherService.add(t);
			for (String courseId : courseIds) {
				if(StringUtils.isBlank(courseId)){
					break;
				}
				teacherService.insertTC(Integer.parseInt(courseId),teacher);
			}
		}	
		return "redirect:/teacher/findAll/1";
	}
	
	//分页查询所有信息
	@RequestMapping("/findAll/{currentPage}")
	public String findList(Model model,@PathVariable("currentPage")Integer currentPage) {
		if(currentPage==null){
			currentPage=1;
		}
		Integer currentCount=currentPageCount;		//设置每页显示的条数
		EasyUIDataGridResult result=teacherService.findList(currentPage,currentCount);
		int totalPage=(int) Math.ceil(1.0*result.getTotal()/currentCount);		//计算总页数
		model.addAttribute("result",result);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPage", totalPage);
		return "teacher-list";
	}
	
	//跳转到修改信息页面
	@RequestMapping("/edit/{id}")
	public String toEdit(Model model,@PathVariable("id")String id) {
		TTeacher t=teacherService.get(id);
		List<TCourseTeacher> list=teacherService.getCourseId(id);		//获取该老师教的课程
		if(list.size()==2){
			model.addAttribute("course1", list.get(0).getCourseid());
			model.addAttribute("course2", list.get(1).getCourseid());
		}if(list.size()==1){
			model.addAttribute("course1", list.get(0).getCourseid());
		}
		model.addAttribute("message", t);
		return "teacher-add";
	}
	
	//删除单个信息
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id")String id) {
		teacherService.deleteById(id);
		return "redirect:/teacher/findAll/1";
	}
	
	//批量删除
	@RequestMapping("/deleteMore/{ids}")
	public String deleteMore(@PathVariable("ids")String[] ids) {
		for (String id : ids) {
			if(StringUtils.isBlank(id)){
				break;
			}
			teacherService.deleteById(id);
		}
		return "redirect:/teacher/findAll/1";
	}
	
	//根据工号模糊查询信息
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
		EasyUIDataGridResult result=teacherService.search(words,currentPage,currentCount);
		int totalPage=(int) Math.ceil(1.0*result.getTotal()/currentCount);		//计算总页数
		model.addAttribute("result",result);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPage", totalPage);
		return "teacher-list";	
	}
	
	//根据获取的内容模糊查询名称
	@RequestMapping("/search")
	@ResponseBody
	public String searchName(Model model,@RequestParam String word) {
		List<Object> Name=null;
		Name=teacherService.searchName(word);
		String json = JsonUtils.objectToJson(Name);
		return json;
	}
	
	//数据表格导入
	@RequestMapping("/import")
	public String importExl(@RequestParam MultipartFile File,HttpServletRequest request) throws IOException {
		String filePath = File.getOriginalFilename();				//获取上传文件名
		//将上传的文件转为文件输入流
		MultipartHttpServletRequest mulRequest = (MultipartHttpServletRequest) request;  
		MultipartFile file = mulRequest.getFile("File");
		InputStream is = file.getInputStream();
		//根据文件名的后缀判断采取哪种POI方式进行解析
		Workbook hssfWorkbook = null;
		if (filePath.endsWith("xlsx")){
			hssfWorkbook = new XSSFWorkbook(is);//Excel 2007
		}else if (filePath.endsWith("xls")){
			hssfWorkbook = new HSSFWorkbook(is);//Excel 2003
		}
		List<TTeacher> list=new ArrayList<TTeacher>();
		//使用POI解析Excel文件
		
		//获得xml指定的页的数据
		Sheet hssfSheet = hssfWorkbook.getSheet("教师信息");
		//遍历数据（行）
		for (Row row : hssfSheet) {
			//读取数据前设置单元格类型
			for (Cell cell : row) {	    
	            cell.setCellType(CellType.STRING);
	        }
				
            //获得行数，第一行跳过，不取其数据
			int num = row.getRowNum();
			if(num==0){
				continue;
			}
			//获取每列的数据,并封装到对象中
			String id  = row.getCell(0).getStringCellValue();
			String name  = row.getCell(1).getStringCellValue();
			String code  = row.getCell(2).getStringCellValue();
			String sexString = row.getCell(3).getStringCellValue();
			String sex;
			if(sexString.equals("男")){
				sex="1";
			}else{
				sex="0";
			}
			String address = row.getCell(4).getStringCellValue();
			String phone = row.getCell(5).getStringCellValue();
			Integer departmentid  = Integer.parseInt(row.getCell(6).getStringCellValue());
			String degree  = row.getCell(7).getStringCellValue();
			String title  = row.getCell(8).getStringCellValue();
			String password = code.substring(12, 18);							//获取身份证后六位作为初始密码
			password=DigestUtils.md5DigestAsHex(password.getBytes());		//MD5加密后存放
			code=DigestUtils.md5DigestAsHex(code.getBytes());
			TTeacher t=new TTeacher(id, name, password, code, new Date(), degree, title, sex, phone, address, 1, new Date(), new Date(), departmentid);
			//将获取的对象添加到list集合中
			list.add(t);
		}	
		//批量保存
		teacherService.saveBatch(list);
		return "redirect:/teacher/findAll/1";
	}
	
	//数据表格导出
	@RequestMapping("/export")
	public void exportExl(HttpServletRequest request,HttpServletResponse response) {
		//第一步：查询所有的专业数据
		List<TTeacher> list = teacherService.findAll();
				
		//第二步：使用POI将数据写到Excel文件中
		//在内存中创建一个Excel文件
		HSSFWorkbook workbook = new HSSFWorkbook();
		//创建一个标签页
		HSSFSheet sheet = workbook.createSheet("教师信息");
		//创建标题行
		HSSFRow headRow = sheet.createRow(0);
		headRow.createCell(0).setCellValue("工号");
		headRow.createCell(1).setCellValue("姓名");
		headRow.createCell(2).setCellValue("性别");
		headRow.createCell(3).setCellValue("家庭住址");
		headRow.createCell(4).setCellValue("联系电话");
		headRow.createCell(5).setCellValue("所属专业号");
		headRow.createCell(6).setCellValue("学位");
		headRow.createCell(7).setCellValue("职称");
				
		for (TTeacher t : list) {
			HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
			dataRow.createCell(0).setCellValue(t.getId());
			dataRow.createCell(1).setCellValue(t.getName());
			if(t.getSex().equals("1")){
				dataRow.createCell(2).setCellValue("男");
			}else {
				dataRow.createCell(2).setCellValue("女");				
			}
			dataRow.createCell(3).setCellValue(t.getAddress());
			dataRow.createCell(4).setCellValue(t.getPhone());
			dataRow.createCell(5).setCellValue(t.getDepartmentid());
			dataRow.createCell(6).setCellValue(t.getDegree());
			dataRow.createCell(7).setCellValue(t.getTitle());
		}
				
		try {
			//第三步：使用输出流进行文件下载（一个流、两个头）
			String filename = "教师信息.xls";
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
	
	//分页查询所有教师信息
	@RequestMapping("/findAllAjax")
	@ResponseBody
	public List<TTeacher> findAllAjax(HttpServletRequest request) {
		String departmentIdString = request.getParameter("departmentId");
		Integer departmentId=Integer.parseInt(departmentIdString);
		List<TTeacher> list = teacherService.findAllAjax(departmentId);
		return list;
	}
	
	//分页查询所有课程信息
	@RequestMapping("/findAllAjaxs")
	@ResponseBody
	public List<TTeacher> findAllAjaxs(HttpServletRequest request) {
		List<TTeacher> list = teacherService.findAllAjaxs();
		return list;
	}
}
