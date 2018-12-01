package com.xunpoit.oa.controller;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xunpoit.oa.entity.Org;
import com.xunpoit.oa.manager.dao.OrgManagerDao;
import com.xunpoit.oa.page.PageModel;
import com.xunpoit.oa.page.Pager;

@Controller
@RequestMapping("/org")
public class OrgController {

	@Resource
	private OrgManagerDao orgManager;
	
	@RequestMapping(value="/addUI",method=RequestMethod.GET)
	public String addUI(int pid,Model model) {
		
		model.addAttribute("pid",pid);
		
		return "org/org_add";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ModelAndView add(Org org,String pid) {
		
		ModelAndView mv = new ModelAndView("common/pub_add_success");
		
		orgManager.addOrg(org, Integer.valueOf(pid));
		
		return mv;
	}
	
	@RequestMapping(value="/del",method=RequestMethod.GET)
	public ModelAndView delete(int id) {
		
		ModelAndView mv = new ModelAndView("common/pub_del_success");
		
		orgManager.removeOrg(id);
		
		return mv;
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public ModelAndView modify(Org org) {
		
		ModelAndView mv = new ModelAndView("org/org_update");
		
		orgManager.modifyOrg(org);
		
		return mv;
	}
	
	@RequestMapping(value="/findOne",method=RequestMethod.GET)
	public ModelAndView queryOne(int id) {
		
		ModelAndView mv = new ModelAndView("org/index");
		
		orgManager.findOrgById(id);
		
		return mv;
	}
	
	@RequestMapping(value="/findAll",method=RequestMethod.GET)
	public String queryAll(int pid,Pager pager,Model model) {
		
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
		
		return "org/index";
	}
	
	@InitBinder("pager")    
	public void initBinder2(WebDataBinder binder) {    
	            binder.setFieldDefaultPrefix("pager.");    
	} 

}
