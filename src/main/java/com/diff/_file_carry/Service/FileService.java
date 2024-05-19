package com.diff._file_carry.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.diff._file_carry.Mapper.FileMapper;

@Service
@Transactional
public class FileService {
	
	@Autowired
	FileMapper FileMapper;

	public List<HashMap<String, String>> CodeList(HashMap<String, String> map) throws Exception{
		return FileMapper.CodeList(map);
	}
	
	public List<HashMap<String, String>> FileList(HashMap<String, String> map) throws Exception{
		return FileMapper.FileList(map);
	}
	
	public String CodeValue(HashMap<String, String> map) throws Exception{
		return FileMapper.CodeValue(map);
	}
	
	public void FileUpload(List<MultipartFile> files, String file_info_list, HashMap<String, String> map) throws Exception{
		String[] bytesUnit = {"Byte", "KB", "MB", "GB", "TB"};
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String userIP = map.get("userIP");
		String userLang = map.get("userLang");
		String datetime = map.get("datetime");
		String system_datetime = map.get("system_datetime");
		
		if(files != null && file_info_list != null && !file_info_list.equals("")) {
			int index=0;
			JSONParser parser = new JSONParser();
	        Object obj = parser.parse(file_info_list); 
	        JSONArray jsonArr = (JSONArray)obj;
	        
	        /*
	        if (jsonArr.size() > 0){
	            for(int i=0; i<jsonArr.size(); i++){
	                JSONObject jsonObj = (JSONObject)jsonArr.get(i);
	                System.out.println("["+i+"] name : " + (String)jsonObj.get("name"));
	                System.out.println("["+i+"] size : " + (String)jsonObj.get("size"));
	                System.out.println("["+i+"] conversation_size : " + (String)jsonObj.get("conversation_size"));
	                System.out.println("["+i+"] period_type : " + (String)jsonObj.get("period_type"));
	            }
	        }
	        */
	        
	        //폴더 생성
	        String fileLocation = "C:\\FILECARRY\\"+userIP+"\\";
			File Folder = new File(fileLocation);
			
			if(Folder.mkdirs()) {
				
			}
			
			for (MultipartFile multipartFile : files) {
            	if(multipartFile.getOriginalFilename() != null && multipartFile.getOriginalFilename() != "") {
            		try {
            			String FileName = (String)((JSONObject)jsonArr.get(index)).get("name");
            			String FileSize = (String)((JSONObject)jsonArr.get(index)).get("size");
            			String FileConversationSize = (String)((JSONObject)jsonArr.get(index)).get("conversation_size");
            			String FilePeriodType = (String)((JSONObject)jsonArr.get(index)).get("period_type");
            			String FilePeriodHour = "0";
            			
            			if(!FilePeriodType.equals("") && FilePeriodType != null) {
            				HashMap<String, String> PeriodMap = new HashMap<String, String>();
            				PeriodMap.put("code_id", "0001");
            				PeriodMap.put("code_detail_id", FilePeriodType);
            				PeriodMap.put("code_lang_type", userLang);
            				FilePeriodHour = FileMapper.CodeValue(PeriodMap);
            			}
            			
            			if(FileName.equals(multipartFile.getOriginalFilename()) && FileSize.equals(Long.toString(multipartFile.getSize()))) {
            				//클라이언트측 만료 일시
            				Date expiration_Date = transFormat.parse(datetime);
            				Calendar expiration_cal = Calendar.getInstance();
            				expiration_cal.setTime(expiration_Date);
            				expiration_cal.add(Calendar.HOUR, Integer.parseInt(FilePeriodHour));
            				expiration_Date = new Date(expiration_cal.getTimeInMillis());
            				String expiration_datetime = transFormat.format(expiration_Date);
            				
            				//서버측 만료 일시
            				Date system_expiration_Date = transFormat.parse(system_datetime);
            				Calendar system_expiration_cal = Calendar.getInstance();
            				system_expiration_cal.setTime(system_expiration_Date);
            				system_expiration_cal.add(Calendar.HOUR, Integer.parseInt(FilePeriodHour));
            				system_expiration_Date = new Date(system_expiration_cal.getTimeInMillis());
            				String system_expiration_datetime = transFormat.format(system_expiration_Date);
            				
            				//파일 업로드
            				FileOutputStream fos = new FileOutputStream(fileLocation + multipartFile.getOriginalFilename());
    		                InputStream is = multipartFile.getInputStream();
    		                int readCount = 0;
    		                byte[] buffer = new byte[1024 * 5];
    		                while((readCount = is.read(buffer)) != -1){
    		                	fos.write(buffer,0,readCount);
    		                }
    		                is.close();
    		                fos.close();
            				
    		                HashMap<String, String> MapperMap = new HashMap<String, String>();
    		                MapperMap.put("IP", userIP);
    		                MapperMap.put("FileName", FileName);
    		                MapperMap.put("FileSize", FileSize);
    		                MapperMap.put("FileConversationSize", FileConversationSize);
    		                MapperMap.put("FilePeriodType", FilePeriodType);
    		                MapperMap.put("datetime", datetime);
    		                MapperMap.put("expiration_datetime", expiration_datetime);
    		                MapperMap.put("system_datetime", system_datetime);
    		                MapperMap.put("system_expiration_datetime", system_expiration_datetime);
    		                FileMapper.FileUpload(MapperMap);
            			}else {
                			System.out.println("오류 발생");
            			}
            			
	            	} catch (Exception e) {
	        			
	        		}
            	}
            	index++;
			}
		}
	}
	
	public void FileDelete(HashMap<String, String> map) throws Exception{
		FileMapper.FileDelete(map);
	}
}
