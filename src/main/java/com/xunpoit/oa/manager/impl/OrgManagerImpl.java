package com.xunpoit.oa.manager.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xunpoit.oa.dao.OrgMapper;
import com.xunpoit.oa.entity.Org;
import com.xunpoit.oa.manager.dao.OrgManagerDao;
import com.xunpoit.oa.page.PageModel;

@Service
public class OrgManagerImpl implements OrgManagerDao {

	@Autowired
	private OrgMapper orgMapper;
	
	public void addOrg(Org org, int pid) {
		// TODO Auto-generated method stub
		if(pid > 0) {
			
			Org parent = orgMapper.selectByPrimaryKey(pid);
			
			org.setParent(parent);
		}
		//先添加
		orgMapper.insertSelective(org);
		
		//更新机构编号的问题
		String sn = org.getId()+"";
		
		if(pid > 0) {
			
			Org parent = orgMapper.selectByPrimaryKey(pid);
			
			sn = parent.getSn()+"_"+org.getId();
		}
		
		org.setSn(sn);
		
		//在更新
		orgMapper.updateByPrimaryKeySelective(org);
        
	}

	public void removeOrg(int id) {
		
		Org org = orgMapper.selectByPrimaryKey(id);
		
		List<Org> count = org.getChildList();
		
		if(count.size()>0) {
			
	    	throw new RuntimeException("旗下拥有子机构！不可直接删除！");
	    	
		} else {
			
			orgMapper.deleteByPrimaryKey(id);
		}
	}

	public void modifyOrg(Org org) {
		// TODO Auto-generated method stub
        orgMapper.updateByPrimaryKeySelective(org);
	}

	public Org findOrgById(int id) {
		// TODO Auto-generated method stub
		return orgMapper.selectByPrimaryKey(id);
	}

	public PageModel<Org> findAll(int pid,int offset,int pageSize) {
		
		PageModel<Org> pm = new PageModel<Org>();
		
	    Map<String,Integer> paramMap = new HashMap<String,Integer>();
	    
	    paramMap.put("pid", pid);
	    paramMap.put("offset", offset);
	    paramMap.put("pageSize", pageSize);

	    List<Org> dataList = orgMapper.findAllByParent(paramMap);
	    
	    pm.setPageSize(pageSize);
	    
	    pm.setDataList(dataList);
	    
	    long items = orgMapper.selectCount(pid);
	    
	    pm.setItems((int)items);
	    
		return pm;
	}

}
