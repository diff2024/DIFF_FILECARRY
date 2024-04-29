package com.diff._file_carry.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diff._file_carry.Service.CommonService;

@RestController
@RequestMapping("/back/Common")
public class CommonController {
	
	@Autowired
	CommonService CommonService;

	@GetMapping(path = "/MenuList")
	public List<HashMap<String, String>> MenuList(HttpServletRequest req) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("authority_id", req.getParameter("authority_id"));
		return CommonService.MenuList(map);
	}
	
		@GetMapping(path = "/Authority")
	public Map<String, String> Authority(HttpServletRequest req) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("user_id", req.getParameter("user_id"));
		map.put("screen_id", req.getParameter("to"));
		HashMap<String, String> return_map = CommonService.Authority(map);
		return return_map;
	}
	
	//파일 다운로드
	@GetMapping(path = "/fileDown")
	public void fileDown(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if(req.getParameter("clm_file_name")!="" || req.getParameter("clm_file_name")!=null|| req.getParameter("clm_file_name")!="이미지첨부파일" || req.getParameter("clm_file_name")!="PDF첨부파일") {
			String JoborderID = req.getParameter("clm_joborder_id");
			String fileName = req.getParameter("clm_file_name");
			String saveFileName = "http://itfactoryddns.iptime.org:10004/MES/DAEKWANG/img/joborder/"+ JoborderID + "/"+ req.getParameter("clm_file_name");
			String contentType = "";
			String fileLength = "";
			
			File tmpFile = new File("C:\\ITF\\ITFACTORY\\PRODUCT\\"+ JoborderID + "\\" + fileName);
	
		    if (tmpFile.isFile()) {
		      long L = tmpFile.length();
		      fileLength = Long.toString(L);
		    }
			
			
			String fileChk = fileName.substring((fileName.indexOf(".")+1), fileName.length());
			String userAgent = req.getHeader("User-Agent");
			File f = new File(saveFileName);
			
			switch(fileChk) {
				case "xlsx":
					contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
					break;
				case "xls":
					contentType = "application/vnd.ms-excel";
					break;
				case "pdf":
					contentType = "application/pdf";
					break;
				case "ppt":
					contentType = "application/vnd.ms-powerpoint";
					break;
				case "hwp":
					contentType = "application/vnd.hancom.hwp";
					break;
				case "csv":
					contentType = "text/csv";
					break;
				case "gif":
					contentType = "image/gif";
					break;
				case "jpeg":
					contentType = "image/jpeg";
					break;
				case "jpg":
					contentType = "image/jpeg";
					break;
				case "png":
					contentType = "image/png";
					break;
				case "txt":
					contentType = "text/plain";
					break;
			}
	
			if(f != null && fileName!="") {
	            if(userAgent.indexOf("MSIE") > -1 || userAgent.indexOf("Trident") > -1){
	                fileName = URLEncoder.encode(f.getName(), "utf-8").replaceAll("\\+", "%20");;
	            }else if(userAgent.indexOf("Chrome") > -1) {
	            	StringBuffer sb = new StringBuffer();
	            	for(int i=0; i<f.getName().length(); i++) {
	            		char c = f.getName().charAt(i);
	            		if(c > '~') {
	            			sb.append(URLEncoder.encode(""+c, "UTF-8"));
	            		}else {
	            			sb.append(c);
	            		}
	            	}
	            	fileName = sb.toString();
	            }else {
	            	fileName = new String(f.getName().getBytes("utf-8"));
	            }
	            if(fileLength!=""){	            		            
	            resp.setContentType(contentType);
	            resp.setContentLength(Integer.parseInt(fileLength));
	            resp.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
	            resp.setHeader("Content-Transfer-Encoding", "binary");
	            }
	            OutputStream out = resp.getOutputStream();
	            FileInputStream fis = null;
	            
	            try {
	                fis = new FileInputStream(f);
	                FileCopyUtils.copy(fis, out);
	            } catch(Exception e){
	                e.printStackTrace();
	            }finally{
	                if(fis != null){
	                    try{
	                        fis.close();
	                    }catch(Exception e){
	                    	e.printStackTrace();
	                    }
	                }
	                if(out != null) {
	                	out.flush();
	                }
	            }
	        }
		}
	}
	@GetMapping(path = "/MessageList")
	public List<HashMap<String, String>> MessageList(HttpServletRequest req) throws Exception {
		String user_id = req.getParameter("user_id");
		return CommonService.MessageList(user_id);
	}
	
	@PostMapping(path = "/MSGConfirm")
	public void MSGConfirm(HttpServletRequest req) throws Exception {
		String msg_id = (req.getParameter("msg_id") == null? "": req.getParameter("msg_id"));
		if(!msg_id.equals("")) {
			CommonService.MSGConfirm(msg_id);
		}
	}
	
	@PostMapping(path = "/MSGSave")
	public void MSGSave(HttpServletRequest req) throws Exception {
		String clm_msg_title = (req.getParameter("clm_msg_title") == null? "": req.getParameter("clm_msg_title"));
		String clm_user = (req.getParameter("clm_user") == null? "": req.getParameter("clm_user"));
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("receive_user_id", "admin");
		map.put("msg_title", clm_msg_title);
		map.put("user_id", clm_user);
		CommonService.MSGSave(map);
	}
	
	@GetMapping(path = "/InnerIP")
	public String InnerIP(HttpServletRequest req) throws Exception {
		String result = null;
	    try {
	        result = InetAddress.getLocalHost().getHostAddress();
	    } catch (UnknownHostException e) {
	        result = "";
	    }
	    return result; 
	}
	
	@GetMapping(path = "/CodeList")
	public List<HashMap<String, String>> CodeList(HttpServletRequest req) throws Exception {
		String code_id = (req.getParameter("code_id") == null? "": req.getParameter("code_id"));
		return CommonService.CodeList(code_id);
	}
	
	@GetMapping(path = "/ClientList")
	public List<HashMap<String, String>> ClientList(HttpServletRequest req) throws Exception {
		return CommonService.ClientList();
	}
}
