package com.xunpoit.oa.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xunpoit.oa.entity.Org;
import com.xunpoit.oa.entity.Person;
import com.xunpoit.oa.manager.dao.OrgManagerDao;
import com.xunpoit.oa.manager.dao.PersonManagerDao;
import com.xunpoit.oa.page.PageModel;
import com.xunpoit.oa.page.Pager;

@Controller
@RequestMapping("/person")
public class PersonController {

	@Resource
	private PersonManagerDao personManager;
	
	@Autowired
	private OrgManagerDao orgManager;
	
	@RequestMapping(value="/addUI",method=RequestMethod.GET)
	public String addUI() {
		
		return "person/person_add";
	}
	
	@RequestMapping(value="/selectOrg",method=RequestMethod.GET)
	public String selectOrg(int pid,Pager pager,Model model) {
		
        PageModel<Org> pm = orgManager.findAll(pid,pager==null?0:pager.getOffset(),3);
		
		model.addAttribute("pm",pm);
		
		model.addAttribute("pid",pid);
		
		model.addAttribute("ppid",0);//默认是顶级机构
		
		if(pid > 0) {
			
			//没有处于顶级机构
			Org parent = orgManager.findOrgById(pid);	
			
			if(parent != null&&parent.getParent() != null) {
			
				model.addAttribute("ppid",parent.getParent().getId());
			}
		}
		
		return "person/select_org";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ModelAndView add(Person person,int orgId) {
		
		ModelAndView mv = new ModelAndView("common/pub_add_success");
		
		personManager.addPerson(person,orgId);
		
		return mv;
	}
	
	@RequestMapping(value="/del",method=RequestMethod.GET)
	public ModelAndView delete(int id) {
		
		ModelAndView mv = new ModelAndView("common/pub_del_success");
		
		personManager.removeOrg(id);
		
		return mv;
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public ModelAndView modify(Person person) {
		
		ModelAndView mv = new ModelAndView("person/person_update");
		
		personManager.modifyOrg(person);
		
		return mv;
	}
	
	@RequestMapping(value="/findOne",method=RequestMethod.GET)
	public ModelAndView queryOne(int id) {
		
		ModelAndView mv = new ModelAndView("person/index");
		
		personManager.findOrgById(id);
		
		return mv;
	}
	
	@RequestMapping(value="/findAll",method=RequestMethod.GET)
	public String queryAll(Pager pager,Model model) {
		
		PageModel<Person> pm = personManager.findAll(pager==null?0:pager.getOffset(),3);
		
		model.addAttribute("pm",pm);
		
		return "person/index";
	}
	
	@InitBinder("pager")    
	public void initBinder2(WebDataBinder binder) {    
	            binder.setFieldDefaultPrefix("pager.");    
	} 

}
