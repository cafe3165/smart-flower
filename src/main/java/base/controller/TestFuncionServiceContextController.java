package base.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import base.pagemodel.MSG;
import base.po.Device;
import base.po.Function;
import base.po.Item;
import base.po.Order;
import base.po.Serviice;
import base.service.impl.ContextService;
import base.service.impl.FunctionService;
import base.service.impl.ServiiceService;

@Controller
public class TestFuncionServiceContextController {

	@Autowired
	FunctionService functionService;
	@Autowired
	ServiiceService serviiceService;
	@Autowired
	ContextService contextService;
	
	@RequestMapping(value="/createFunctionAndService")
	@ResponseBody
	public Object createFunctionService(){
		//找到某个功能f1
		List<Function> f=functionService.findByFunctionName("upTempertureFunction");
		Function f1=f.get(0);
        //创建与f1有关的服务1
		Serviice s1=new Serviice("upTempertureService","This is a Service about temperture");
		serviiceService.createServiice(s1);
		//加边
		serviiceService.addServiice(f1, s1);
		
		       //找到某个功能f2
				List<Function> ff=functionService.findByFunctionName("downTempertureFunction");
				Function ff1=ff.get(0);
		        //创建与f1有关的服务1
				Serviice ss1=new Serviice("downTempertureFunction","This is a Service about temperture");
				serviiceService.createServiice(ss1);
				//加边
				serviiceService.addServiice(ff1, ss1);
		
		
		
		return f1+"==**=="+s1;
	}
	
	
	@RequestMapping(value="/deleteFunctionAndService")
	@ResponseBody
	public MSG deleteFunctionAndService(){
		serviiceService.deleteall();
		return new MSG("success");
	}
	
	
	
	
	
	
}
