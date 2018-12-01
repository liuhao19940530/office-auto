package com.xunpoit.oa.manager.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xunpoit.oa.dao.OrgMapper;
import com.xunpoit.oa.dao.PersonMapper;
import com.xunpoit.oa.entity.Org;
import com.xunpoit.oa.entity.Person;
import com.xunpoit.oa.manager.dao.PersonManagerDao;
import com.xunpoit.oa.page.PageModel;
@Service
public class PersonManagerImpl implements PersonManagerDao {

	@Autowired
	private PersonMapper personMapper;
	
	@Autowired
	private OrgMapper orgMapper;
	
	@Override
	public void addPerson(Person person,int orgId) {
		// TODO Auto-generated method stub
		Org org = orgMapper.selectByPrimaryKey(orgId);
		
		person.setOrg(org);
		
		personMapper.insertSelective(person);
	}

	@Override
	public void removeOrg(int id) {
		// TODO Auto-generated method stub
		personMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void modifyOrg(Person person) {
		// TODO Auto-generated method stub
		personMapper.updateByPrimaryKeySelective(person);
	}

	@Override
	public Person findOrgById(int id) {
		// TODO Auto-generated method stub
		return personMapper.selectByPrimaryKey(id);
	}

	@Override
	public PageModel<Person> findAll(int offset, int pageSize) {
		
		PageModel<Person> pm = new PageModel<>();
		
		Map<String,Integer> paramMap = new HashMap<>();
		
		paramMap.put("offset",offset);
		
		paramMap.put("pageSize",pageSize);
		
		List<Person> dataList = personMapper.findAllByParent(paramMap);
		
		pm.setDataList(dataList);
		
		pm.setPageSize(pageSize);
		
		long items = personMapper.selectCount();
		
		pm.setItems((int)items);
		
		return pm;
	}

}
