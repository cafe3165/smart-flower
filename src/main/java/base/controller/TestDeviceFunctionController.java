package base.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import base.pagemodel.MSG;
import base.po.Device;
import base.po.Function;
import base.po.Item;
import base.po.Order;
import base.service.impl.DeviceService;
import base.service.impl.FunctionService;

@Controller
public class TestDeviceFunctionController {

//	private static final Device String = null;

	@Autowired
	DeviceService deviceService;
	
	@Autowired
	FunctionService functionService;
	
	@RequestMapping(value="/createDeviceAndFunction")
	@ResponseBody
	public MSG create_AIRCONDITION_DeviceAnd_2Function(){
		//创建中心1个节点-设备
		Device d=new Device("AIRCONDITION","up_down_temperture"); 
		deviceService.createDevice(d);
        //创建外围2个节点-功能
        	Function f1=new Function("upTempertureFunction","URI-1");
        	functionService.createFunction(f1);
        	Function f2=new Function("downTempertureFunction","URI-2");
        	functionService.createFunction(f2);
        	
        
        	Device device=deviceService.findByDeviceName("AIRCONDITION").get(0);
        //加边
        	Result<Function> functions = functionService.getAllFunctions();
        	Iterator<Function> fu=functions.iterator();
        	while(fu.hasNext()){
        		Function f=fu.next();
        		functionService.addFunction(device, f);
        	}
		return new MSG("success");
	}
	
	
	@RequestMapping(value="/deleteDeviceAndFunction")
	@ResponseBody
	public MSG aaa(){
		deviceService.deleteall();
		functionService.deleteall();
		return new MSG("success");
	}
	
	
	 @RequestMapping(value="/test")
	 @ResponseBody
	 public Object test(@RequestParam("device") Device d) {
		 String d1=d.getDeviceName();		 
		 List dd=(List) deviceService.findByDeviceName(d1);
		 if(0==dd.size()){		 
			 System.out.println(dd);
			 System.out.println("IT IS NOT PRECENT!!!");
			 return new String("IT IS NOT PRECENT!!!");
		 } 
		System.out.println(d1);
		System.out.println(dd);
		System.out.println("IT IS PRECENT!!!");		
	    return d;
	  }
	
	
	
}
