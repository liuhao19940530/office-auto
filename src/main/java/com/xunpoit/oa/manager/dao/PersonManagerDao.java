package com.xunpoit.oa.manager.dao;

import com.xunpoit.oa.entity.Person;
import com.xunpoit.oa.page.PageModel;

public interface PersonManagerDao {
	
	public void  removeOrg(int id);
	
	public void modifyOrg(Person person);
	
	public Person findOrgById(int id);
	
	public PageModel<Person> findAll(int offset,int pageSize);

	void addPerson(Person person, int orgId);
}
