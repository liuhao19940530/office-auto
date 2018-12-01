package com.xunpoit.oa.manager.dao;

import com.xunpoit.oa.entity.Org;
import com.xunpoit.oa.page.PageModel;

public interface OrgManagerDao {

	public void addOrg(Org org,int pid);
	
	public void  removeOrg(int id);
	
	public void modifyOrg(Org org);
	
	public Org findOrgById(int id);
	
	public PageModel<Org> findAll(int pid,int offset,int pageSize);
}
