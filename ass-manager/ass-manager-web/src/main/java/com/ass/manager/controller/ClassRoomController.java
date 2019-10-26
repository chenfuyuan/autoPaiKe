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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
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
import com.ass.manager.service.ClassRoomService;
import com.ass.pojo.TClass;
import com.ass.pojo.TClassroom;


/**
 * 教室信息管理Controller
 * @author chenfuyuan
 *
 */

@Controller
@RequestMapping("/classroom")
public class ClassRoomController {
	
	@Autowired
	private ClassRoomService classroomService;
	
	@Value("${currentPageCount}")
	private Integer currentPageCount;
	
	//添加信息
	@RequestMapping("/add")
	public String add(TClassroom t,HttpServletRequest request) {
		//根据是否传来ID值判断是应修改还是添加信息
		if(t.getId()!=null){
			classroomService.edit(t);
		}else{
			classroomService.add(t);			
		}
		return "redirect:/classroom/findAll/1";
	}
	
	//分页查询所有信息
	@RequestMapping("/findAll/{currentPage}")
	public String findList(Model model,@PathVariable("currentPage")Integer currentPage) {
		if(currentPage==null){
			currentPage=1;
		}
		Integer currentCount=currentPageCount;		//设置每页显示的条数
		EasyUIDataGridResult result=classroomService.findList(currentPage,currentCount);
		int totalPage=(int) Math.ceil(1.0*result.getTotal()/currentCount);		//计算总页数
		model.addAttribute("result",result);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPage", totalPage);
		return "classroom-list";
	}
	
	//跳转到修改信息页面
	@RequestMapping("/edit/{id}")
	public String toEdit(Model model,@PathVariable("id")Integer id) {
		TClassroom t=classroomService.get(id);
		model.addAttribute("message", t);
		return "classroom-add";
	}
	
	//删除单个信息
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id")Integer id) {
		classroomService.deleteById(id);
		return "redirect:/classroom/findAll/1";
	}
	
	//批量删除
	@RequestMapping("/deleteMore/{ids}")
	public String deleteMore(@PathVariable("ids")Integer[] ids) {
		for (Integer id : ids) {
			if(id==null){
				break;
			}
			classroomService.deleteById(id);
		}
		return "redirect:/classroom/findAll/1";
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
		EasyUIDataGridResult result=classroomService.search(words,currentPage,currentCount);
		int totalPage=(int) Math.ceil(1.0*result.getTotal()/currentCount);		//计算总页数
		model.addAttribute("result",result);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPage", totalPage);
		return "classroom-list";	
	}
	
	//根据获取的内容模糊查询名称
	@RequestMapping("/search")
	@ResponseBody
	public String searchName(Model model,@RequestParam String word) {
		List<Object> Name=null;
		Name=classroomService.searchName(word);
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
		List<TClassroom> list=new ArrayList<TClassroom>();
		//使用POI解析Excel文件
		
		//获得xml指定的页的数据
		Sheet hssfSheet = hssfWorkbook.getSheet("教室信息");
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
			String name  = row.getCell(0).getStringCellValue();
			String type  = row.getCell(1).getStringCellValue();
			Integer number  = Integer.parseInt(row.getCell(2).getStringCellValue());
			TClassroom t=new TClassroom(name, type, number, 0, new Date(), new Date(), 1);
			//将获取的对象添加到list集合中
			list.add(t);
		}	
		//批量保存
		classroomService.saveBatch(list);
		return "redirect:/classroom/findAll/1";
	}
	
	//数据表格导出
	@RequestMapping("/export")
	public void exportExl(HttpServletRequest request,HttpServletResponse response) {
		//第一步：查询所有的专业数据
		List<TClassroom> list = classroomService.findAll();
				
		//第二步：使用POI将数据写到Excel文件中
		//在内存中创建一个Excel文件
		HSSFWorkbook workbook = new HSSFWorkbook();
		//创建一个标签页
		HSSFSheet sheet = workbook.createSheet("教室信息");
		//创建标题行
		HSSFRow headRow = sheet.createRow(0);
		headRow.createCell(0).setCellValue("教室代码");
		headRow.createCell(1).setCellValue("教室名称");
		headRow.createCell(2).setCellValue("教室类型");
		headRow.createCell(3).setCellValue("容纳人数");
				
		for (TClassroom t : list) {
			HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
			dataRow.createCell(0).setCellValue(t.getId());
			dataRow.createCell(1).setCellValue(t.getName());
			dataRow.createCell(2).setCellValue(t.getType());
			dataRow.createCell(3).setCellValue(t.getNumber());
		}
				
		try {
			//第三步：使用输出流进行文件下载（一个流、两个头）
			String filename = "教室信息.xls";
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
	
	//无条件分页查询所有教室信息
	@RequestMapping("/findAllAjaxs")
	@ResponseBody
	public List<TClassroom> findAllAjaxs(HttpServletRequest request) {
		List<TClassroom> list = classroomService.findAll();
		return list;
	}
}
