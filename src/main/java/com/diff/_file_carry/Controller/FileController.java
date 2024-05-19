package com.diff._file_carry.Controller;

import java.io.File;
import java.io.BufferedReader;
import java.net.URL;
import java.net.URLConnection;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.text.DecimalFormat;

import com.diff._file_carry.Service.FileService;

@RestController
@RequestMapping("/File")
public class FileController {
	
	@Autowired
	FileService FileService;

	@GetMapping(path = "/CodeList")
	public List<HashMap<String, String>> CodeList(HttpServletRequest req) throws Exception {
		String code_id = (req.getParameter("code_id") == null? "":req.getParameter("code_id"));
		String code_lang_type = (req.getParameter("code_lang_type") == null? "":req.getParameter("code_lang_type"));
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("code_id", code_id);
		map.put("code_lang_type", code_lang_type);
		return FileService.CodeList(map);
	}
	
	@GetMapping(path = "/FileList")
	public List<HashMap<String, String>> FileList(HttpServletRequest req) throws Exception {
		String IP = (req.getParameter("IP") == null? "":req.getParameter("IP"));
		String code_lang_type = (req.getParameter("code_lang_type") == null? "":req.getParameter("code_lang_type"));
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("IP", IP);
		map.put("code_lang_type", code_lang_type);
		return FileService.FileList(map);
	}
	
	@PostMapping(path = "/FileUpload")
	public void FileUpload(@RequestParam(value="files", required=false) List<MultipartFile> files, @RequestParam(value="file_info_list", required=false) String file_info_list, @RequestParam("userIP") String userIP, @RequestParam("userLang") String userLang, @RequestParam("datetime") String datetime, @RequestParam("system_datetime") String system_datetime) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
        map.put("userIP", userIP);
        map.put("userLang", userLang);
        map.put("datetime", datetime);
        map.put("system_datetime", system_datetime);
        FileService.FileUpload(files, file_info_list, map);
	}
	
	@PostMapping(path = "/FileDelete")
	public String FileDelete(HttpServletRequest req) throws Exception {
		String userLang = (req.getParameter("userLang") == null? "":req.getParameter("userLang"));
		String userIP = (req.getParameter("userIP") == null? "":req.getParameter("userIP"));
		String file_id = (req.getParameter("file_id") == null? "":req.getParameter("file_id"));
		String ClientIP = GetClientIP();
		
		if(!ClientIP.equals(userIP)) {
			return "N";
		} else {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("file_id", file_id);
			FileService.FileDelete(map);
			return "Y";
		}
	}
	
	//파일 다운로드
	@PostMapping(path = "/FileDownload")
	public void fileDown(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String userIP = (req.getParameter("userIP") == null? "":req.getParameter("userIP"));
		String file_id = (req.getParameter("file_id") == null? "":req.getParameter("file_id"));
		String file_name = (req.getParameter("file_name") == null? "":req.getParameter("file_name"));
		
		if(!userIP.equals("") && !file_id.equals("") && !file_name.equals("")) {
			
			File f = new File("C:\\FILECARRY\\" + userIP + "\\" + file_name);
	        // file 다운로드 설정
			resp.setContentType("application/download");
			resp.setContentLength((int)f.length());
			resp.setHeader("Content-disposition", "attachment;filename=\"" + file_name + "\"");
	        // response 객체를 통해서 서버로부터 파일 다운로드
	        OutputStream os = resp.getOutputStream();
	        // 파일 입력 객체 생성
	        FileInputStream fis = new FileInputStream(f);
	        FileCopyUtils.copy(fis, os);
	        fis.close();
	        os.close();

	        /*
			
			String contentType = "";
			String fileLength = "";
			
			File tmpFile = new File("C:\\FILECARRY\\" + userIP + "\\" + file_name);
	
		    if (tmpFile.isFile()) {
		      long L = tmpFile.length();
		      fileLength = Long.toString(L);
		    }
			
			String fileChk = file_name.substring((file_name.indexOf(".")+1), file_name.length());
			String userAgent = req.getHeader("User-Agent");
			
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
	
			if(file_name!="") {
	            if(userAgent.indexOf("MSIE") > -1 || userAgent.indexOf("Trident") > -1){
	            	file_name = URLEncoder.encode(f.getName(), "utf-8").replaceAll("\\+", "%20");;
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
	            	file_name = sb.toString();
	            }else {
	            	file_name = new String(f.getName().getBytes("utf-8"));
	            }
	            if(fileLength!=""){	            		            
	            resp.setContentType(contentType);
	            resp.setContentLength(Integer.parseInt(fileLength));
	            resp.setHeader("Content-Disposition", "attachment; filename=\"" + file_name + "\";");
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
	        */
		}
	}
	
	public String GetClientIP() throws IOException {
		String ClientIP = "";
		URL url = new URL("http://checkip.amazonaws.com");
        URLConnection connection = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        ClientIP = in.readLine();
        return ClientIP;
	}
	/*
	@GetMapping(path = "/MessageList")
	public List<HashMap<String, String>> MessageList(HttpServletRequest req) throws Exception {
		String user_id = req.getParameter("user_id");
		return FileService.MessageList(user_id);
	}
	
	@PostMapping(path = "/MSGConfirm")
	public void MSGConfirm(HttpServletRequest req) throws Exception {
		String msg_id = (req.getParameter("msg_id") == null? "": req.getParameter("msg_id"));
		if(!msg_id.equals("")) {
			FileService.MSGConfirm(msg_id);
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
		FileService.MSGSave(map);
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
		return FileService.CodeList(code_id);
	}
	
	@GetMapping(path = "/ClientList")
	public List<HashMap<String, String>> ClientList(HttpServletRequest req) throws Exception {
		return FileService.ClientList();
	}
	*/
}
