package com.diff._file_carry.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommonMapper {
	public List<HashMap<String, String>> MenuList(HashMap<String, String> map) throws Exception;
	public HashMap<String, String> Authority(HashMap<String, String> map) throws Exception;
	public List<HashMap<String, String>> CodeList(String code_id) throws Exception;
	public List<HashMap<String, String>> MessageList(String user_id) throws Exception;
	public void MSGConfirm(String msg_id) throws Exception;
	public void MSGSave(HashMap<String, String> map) throws Exception;
	public HashMap<String, String> ClientInfo(String joborder_seq) throws Exception;
	public List<HashMap<String, String>> ClientList() throws Exception;
}
