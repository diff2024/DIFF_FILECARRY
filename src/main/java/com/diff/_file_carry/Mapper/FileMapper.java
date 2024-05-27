package com.diff._file_carry.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {
	public List<HashMap<String, String>> TextLangList() throws Exception;
	public List<HashMap<String, String>> CodeList(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> FileList(HashMap<String, String> map) throws Exception;
	public String CodeValue(HashMap<String, String> map) throws Exception;
	public void FileUpload(HashMap<String, String> map) throws Exception;
	public void FileDelete(HashMap<String, String> map) throws Exception;
}
