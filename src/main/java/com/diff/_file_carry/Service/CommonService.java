package com.diff._file_carry.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diff._file_carry.Mapper.CommonMapper;

@Service
@Transactional
public class CommonService {
	
	@Autowired
	CommonMapper CommonMapper;

	public List<HashMap<String, String>> MenuList(HashMap<String, String> map) throws Exception{
		return CommonMapper.MenuList(map);
	}
	public HashMap<String, String> Authority(HashMap<String, String> map) throws Exception{
		return CommonMapper.Authority(map);
	}
	public List<HashMap<String, String>> MessageList(String user_id) throws Exception{
		return CommonMapper.MessageList(user_id);
	}
	public void MSGConfirm(String msg_id) throws Exception{
		CommonMapper.MSGConfirm(msg_id);
	}
	public void MSGSave(HashMap<String, String> map) throws Exception{
		CommonMapper.MSGSave(map);
	}
	public HashMap<String, String> ClientInfo(String user_id) throws Exception{
		return CommonMapper.ClientInfo(user_id);
	}
	public List<HashMap<String, String>> CodeList(String code_id) throws Exception{
		return CommonMapper.CodeList(code_id);
	}
	public List<HashMap<String, String>> ClientList() throws Exception{
		return CommonMapper.ClientList();
	}
}
