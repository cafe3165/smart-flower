package base.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import base.pagemodel.MSG;
import base.po.Context;
import base.po.Device;
import base.po.Function;
import base.po.Location;
import base.po.Serviice;
import base.po.Serviice_CouldChange_Context;
import base.po.User;
import base.service.impl.ContextService;
import base.service.impl.DeviceService;
import base.service.impl.FunctionService;
import base.service.impl.LocationService;
import base.service.impl.ServiiceService;
import base.service.impl.UserService;
import tool.tool.TestDom4j;

@Controller
public class SmartCaseController {
	@Autowired
	DeviceService deviceService;
	@Autowired
	FunctionService functionService;
	@Autowired
	ServiiceService serviiceService;
	@Autowired
	ContextService contextService;
	@Autowired
	LocationService locationService;
	@Autowired
	UserService userService;

	
	
	//模拟升温功能的方法
	public static double upTempertureFunction(Context c){		
	/**
	 * 	通过前面的需求检索到需要改变的状态指定的功能
	 *  该功能(模拟底层的功能方法)改变指定的状态的contextValue
	 *  同时底层状态在知识图谱中展示出来
	 */
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double step=1;//设置升温的幅度
		double cvalue=c.getContextValue();
		cvalue=cvalue+step;
		c.setContextValue(cvalue);
		//System.out.println("调用**相应**upTempertureFunction功能，升高温度"+step+"℃!");	
		return cvalue;
	}
	//模拟降温功能的方法
	public static double downTempertureFunction(Context c){
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double step=1;//设置升温的幅度
		double cvalue=c.getContextValue();
		cvalue=cvalue-step;
		c.setContextValue(cvalue);
		//System.out.println("调用**相应**downTempertureFunction功能功能，降低温度"+step+"℃!");							
		return cvalue;
	}
	public static double upHumidityFunction(Context c){		
		/**
		 * 	通过前面的需求检索到需要改变的状态指定的功能
		 *  该功能(模拟底层的功能方法)改变指定的状态的contextValue
		 *  同时底层状态在知识图谱中展示出来
		 */
			double step=5;//设置升温的幅度
			double cvalue=c.getContextValue();
			cvalue=cvalue+step;
			c.setContextValue(cvalue);
			System.out.println("调用**相应**upHumidityFunction功能，增加湿度"+step+"%");	
			return cvalue;
		}
		//模拟降温功能的方法
		public static double downHumidityFunction(Context c){
			double step=1;//设置升温的幅度
			double cvalue=c.getContextValue();
			cvalue=cvalue-step;
			c.setContextValue(cvalue);
			System.out.println("调用**相应**downTempertureFunction功能功能，降低温度"+step+"℃!");							
			return cvalue;
		}
	/**
	 * 该方法模拟从配置文件中读取场景
	 * 用知识图谱的概念创建整体场景的过程
	 * 在http://127.0.0.1:7474/中可以展示当前的场景
	 * @return
	 */
	@RequestMapping(value="/cratesf")
	@ResponseBody
	public MSG crateSmartFlowerCase(){
			/**
			 * 空调
			 * 
			 * 创建X个设备和Y个功能
			 * X=1
			 * Y=2
			 */
			//创建中心1个节点-设备
			Device d=new Device("AIRCONDITION","up_down_temperture"); 
			deviceService.createDevice(d);
			//创建外围2个节点-功能
        	Function f1=new Function("upTempertureFunction","URI-1","upTempertureFunction");
        	functionService.createFunction(f1);
        	Function f2=new Function("downTempertureFunction","URI-2","downTempertureFunction");
        	functionService.createFunction(f2);
        	 
        	Device device=deviceService.findByDeviceName("AIRCONDITION").get(0);
        	//加边,所有的功能都加入到这个设备中（可以通过描述文件的标签，分别建立设备和功能之间的关系的）
        	Result<Function> functions = functionService.getAllFunctions();
        	Iterator<Function> fu=functions.iterator();
        	while(fu.hasNext()){
        		Function f=fu.next();
        		functionService.addFunction(device, f);
        	}
        	/**
        	 * 创建功能相应的Y个服务
        	 * Y=2
        	 */
        	List<Function> f=functionService.findByFunctionName("upTempertureFunction");
    		Function fs1=f.get(0);
            //创建与f1有关的服务1
    		Serviice s1=new Serviice("upTempertureService","upTempertureService");
    		serviiceService.createServiice(s1);
    		//加边
    		serviiceService.addServiice(fs1, s1);
    		
    		//找到某个功能f2
    		List<Function> ff=functionService.findByFunctionName("downTempertureFunction");
    		Function ff1=ff.get(0);
    		//创建与f1有关的服务1
    		Serviice s2=new Serviice("downTempertureFunction","downTempertureFunction");
    		serviiceService.createServiice(s2);
    		//加边
    		serviiceService.addServiice(ff1, s2);
    		//加一个无关服务
    		Serviice sx=new Serviice("Service","This is a Service");
    		serviiceService.createServiice(sx);
    		/**
    		 * 加湿器
    		 */
    		//创建中心1个节点-加湿器
			Device h=new Device("HUMIDITY","up_humidity"); 
			deviceService.createDevice(h);
			//创建外围2个节点-功能
        	Function hf1=new Function("upHumidityFunction","URI-11","upHumidityFunction");
        	functionService.createFunction(hf1);
        	//建立设备和功能的关联
        	functionService.addFunction(h,hf1);
        	//创建与hf1有关的服务
    		Serviice hs1=new Serviice("upHumidityService","upHumidityService");
    		serviiceService.createServiice(hs1);
    		//加边
    		serviiceService.addServiice(hf1, hs1);
    		// 创建一个用户   		
    		User u=new User("lili","lililili"); 
			userService.createUser(u);

			
			//创建一个状态
			/**
			 * 温度
			 */
			Context c=new Context("Temperture","Temperture",23.0);
			contextService.createContext(c);
			
			contextService.addContext(s1, c);
			contextService.addContext(s2, c);
			/**
			 * 无关状态t
			 */
			Context cx=new Context("t","t",23.0);
			contextService.createContext(cx);
			/**
			 * 温度
			 */
			Context hum=new Context("Humidity","Humidity",10);
			contextService.createContext(hum);			
			contextService.addContext(hs1, hum);

			
			//创建一个位置
			
			Location loca=new Location("floor001","floor001");
			locationService.createLocation(loca);
			
			userService.addUser(loca, u);
			deviceService.addDevice(loca, d);
			contextService.addContext(loca, c);
			contextService.addContext(loca, hum);
			System.err.println("该场景有状态：");
			System.out.println(c);
			System.out.println(cx);
			System.out.println(hum);
			
			System.out.println(loca);
			
			System.out.println(d);
			System.out.println(h);
			
			System.out.println(f1);
			System.out.println(f2);
			System.out.println(hf1);
			
			System.out.println(s1);
			System.out.println(s2);
			System.out.println(hs1);
			System.out.println(sx);
			
			System.out.println(u);
		return new MSG("导入配置文件，创建养花场景成功！");
	}
	/**
	 * 删除当前的场景
	 * @return
	 */
	@RequestMapping(value="/deletesf")
	@ResponseBody
	public MSG deleteSmartFlowerCase(){
		deviceService.deleteall();
		functionService.deleteall();
		userService.deleteall();
		locationService.deleteall();
		contextService.deleteall();
		serviiceService.deleteall();	
		return new MSG("deleteSmartFlowerCase_success!");
	}
	
	/**
	 * 需求说明了要修改的状态的信息以及状态最终的值 
	 * 根据需求中控制的状态，寻找控制状态的服务，对应的方法
	 * 调用对应的方法
	 * 搜索引擎
	 * 方法一：
	 * 根据具体的状态名和描述，找到对应的状态
	 * 调用相应的功能修改状态值
	 * 方法二（待完成）：
	 * 对状态添加位置约束，多一层搜索和查找逻辑
	 * 
	 * 
	 */
	@RequestMapping(value="/demand-engine")
	@ResponseBody
	public Object test(@RequestParam("contextName") String contextName,@RequestParam("demandContextValue") double demandContextValue) {
		//01、根据状态名称找到对应的状态
		List<Context> contextList=contextService.findByContextName(contextName);
		Context context;
		
		 Date dt=new Date();//如果不需要格式,可直接用dt,dt就是当前系统时间
         DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");//设置显示格式
         String nowTime="";
         nowTime= df.format(dt);
         System.err.println("0::::::::::::"+nowTime);
         long t=dt.getTime();
         System.err.println("0::::::::::::"+ System.currentTimeMillis());
		
		
		if(contextList.size()>0){
		context=contextList.get(0);	
		System.err.println(context);
		}
		else{
		context=null;
		System.err.println(context);
		}
		//02、根据需求中指定的状态，获得状态的值（判断是否满足需求）
		double value=context.getContextValue();
		System.out.print("当前ContextValue是："+value);	
//		if(value<demandContextValue){
		while(value<demandContextValue){
			
			Date dt0=new Date();//如果不需要格式,可直接用dt,dt就是当前系统时间
	         DateFormat df0 = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");//设置显示格式
	         String nowTime0="";
	         nowTime0= df0.format(dt0);
	         System.err.println("0::::::::::::"+nowTime0);
	         long t0=dt0.getTime();
	         System.err.println("0::::::::::::"+ System.currentTimeMillis());
			
			System.err.println("此状态值低于"+demandContextValue+"需要提高值!");
			//up的服务和功能			
			 //找到所有指向这个context的服务，检索服务的描述UP:具体在服务类中实现具体操作
			 //检索所有指向这个Serviice的功能，调用功能的方法
			
			//03、当状态不满足需求时，找与该状态有关的服务
			List<Serviice> serviiceList=serviiceService.findServiiceByContextName(contextName);
			System.out.println("该状态和下列服务有关：");
			for(int i=0;i<serviiceList.size();i++){
				System.out.println(serviiceList.get(i));
				String describeService=serviiceList.get(i).getDescribe();//具体根据描述文件比较好！
			}
			for(int i=0;i<serviiceList.size();i++){
				String describeService=serviiceList.get(i).getDescribe();
				
				//04、根据需求和服务的描述属性，对与该状态有关的服务进行筛选和查询，找到可以满足需求的服务
				if(describeService.indexOf("up"+contextName)!=-1){
					System.out.println(serviiceList.get(i)+"可以提高状态值。");
					System.out.println("与该服务相关的功能有：");
					//存在有up这个状态的服务
					//寻找和该状态有关的功能
					String serviiceName=serviiceList.get(i).getServiiceName();
					
					//05、根据相应的服务，找到有关的功能，依据功能的uri信息，找到功能的名称和参数，利用反射机制调用该功能
					//设备功能，服务状态的关系的建立，保证了对应的功能可以操作相应的状态，因此模拟中在功能的类方法里直接操作状态的属性值
					List<Function> funcs=functionService.findFunctionByServiiceName(serviiceName);
					for(int j=0;j<funcs.size();j++){
						System.out.println(funcs.get(j));
						if(funcs.get(j).getDescribe().indexOf("up"+contextName)!=-1){
							System.out.println(funcs.get(j)+"可以实现完成需求");
							//调用的功能在控制器中模拟写出相应方法，最终可以通过restful框架实现功能对底层的操作
							//其中，调用的功能名称，参数等，也通过反射机制实现
							double contextValueNew=0.0;
							if("Humidity".equals(context.getContextName())){
								contextValueNew=SmartCaseController.upHumidityFunction(context);
								System.err.println("Humidity");
							}
							else if("Temperture".equals(context.getContextName())){								
									contextValueNew=SmartCaseController.upTempertureFunction(context);							
							}
							contextService.setContextValueByFunction(contextName, contextValueNew);
							break;
						}		
					}			
				}
			}	
			
//			try
//			{
//			    Thread.sleep(5);
//			    
//			}
//			catch (InterruptedException e)
//			{
//			    e.printStackTrace();
//			}	
			
			//实验时间测试
			Date dt2=new Date();//如果不需要格式,可直接用dt,dt就是当前系统时间
	        DateFormat df2 = new SimpleDateFormat("yyyy：MM：dd HH:mm:ss");//设置显示格式
	        String nowTime2="";
	        nowTime2= df2.format(dt2);
	        System.err.println("0::::::::::::"+nowTime2);
	        long t2=dt2.getTime();
	        System.err.println("0::::::::::::"+t2);
			
	        long between=0;
	        between=t2-t0;
	        System.err.println("between::::::::::::"+between);
	        
	        
			value=context.getContextValue();
			System.err.println("当前状态值为："+value);
			
			
			value=context.getContextValue();
			System.err.println("当前状态值为："+value);
		}
//		else if(value>demandContextValue){
		while(value>demandContextValue){
			
			Date dt0=new Date();//如果不需要格式,可直接用dt,dt就是当前系统时间
	         DateFormat df0 = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");//设置显示格式
	         String nowTime0="";
	         nowTime0= df0.format(dt0);
	         System.err.println("0::::::::::::"+nowTime0);
	         long t0=dt0.getTime();
	         System.err.println("0::::::::::::"+ System.currentTimeMillis());
			
			if("Humidity".equals(context.getContextName())){
				System.err.println("没有降低湿度的设备可以使用！");
				return new MSG("没有降低湿度的设备可以使用");
			}
			System.err.println("当前状态值高于需求值"+demandContextValue+"需要降低数值！");
			List<Serviice> serviiceList=serviiceService.findServiiceByContextName(contextName);
			System.out.println("该状态和下列服务有关：");
			for(int i=0;i<serviiceList.size();i++){
				System.out.println(serviiceList.get(i));
				String describeService=serviiceList.get(i).getDescribe();//具体根据描述文件比较好！
			}
			for(int i=0;i<serviiceList.size();i++){
				String describeService=serviiceList.get(i).getDescribe();
				if(describeService.indexOf("down"+contextName)!=-1){
					System.out.println(serviiceList.get(i)+"可以降低温度，与该服务相关的功能有：");
					//存在有up这个状态的服务
					//寻找和该状态有关的功能
					String serviiceName=serviiceList.get(i).getServiiceName();
					List<Function> funcs=functionService.findFunctionByServiiceName(serviiceName);
					for(int j=0;j<funcs.size();j++){
						System.out.println(funcs.get(j));
						if(funcs.get(j).getDescribe().indexOf("down"+contextName)!=-1){							
							System.out.println(funcs.get(j)+"可以实现完成需求");
							double contextValueNew=SmartCaseController.downTempertureFunction(context);
							contextService.setContextValueByFunction(contextName, contextValueNew);
							break;
						}		
					}			
				}else{
					break;
				}
			}	
//			try
//			{
//			    Thread.sleep(5);
//			}
//			catch (InterruptedException e)
//			{
//			    e.printStackTrace();
//			}
			//实验时间测试
			Date dt2=new Date();//如果不需要格式,可直接用dt,dt就是当前系统时间
	        DateFormat df2 = new SimpleDateFormat("yyyy：MM：dd HH:mm:ss");//设置显示格式
	        String nowTime2="";
	        nowTime2= df2.format(dt2);
	        System.err.println("0::::::::::::"+nowTime2);
	        long t2=dt2.getTime();
	        System.err.println("0::::::::::::"+t2);
			
	        long between=0;
	        between=t2-t0;
	        System.err.println("between::::::::::::"+between);
	        
	        
			value=context.getContextValue();
			System.err.println("当前状态值为："+value);
		}
				
	    return context;
	}
	
	
	//模拟一般方法
	@RequestMapping(value="/demand-engine000")
	@ResponseBody
	public Object test000(@RequestParam("contextName") String contextName,@RequestParam("demandContextValue") double demandContextValue) {
		//01、根据状态名称找到对应的状态
		List<Context> contextList=contextService.findByContextName(contextName);
		Context context;
		
		 Date dt=new Date();//如果不需要格式,可直接用dt,dt就是当前系统时间
         DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");//设置显示格式
         String nowTime="";
         nowTime= df.format(dt);
         System.err.println("0::::::::::::"+nowTime);
         long t=dt.getTime();
         System.err.println("0::::::::::::"+ System.currentTimeMillis());
		
		
		if(contextList.size()>0){
		context=contextList.get(0);	
		System.err.println(context);
		}
		else{
		context=null;
		System.err.println(context);
		}
		//02、根据需求中指定的状态，获得状态的值（判断是否满足需求）
		//double value=context.getContextValue();
		System.out.print("当前ContextValue是："+context.getContextValue());	

		while(context.getContextValue()<demandContextValue){
			
			Date dt1=new Date();//如果不需要格式,可直接用dt,dt就是当前系统时间
	         DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");//设置显示格式
	         String nowTime1="";
	         nowTime1= df.format(dt1);
	         System.err.println("0::::::::::::"+nowTime1);
	         long t1=dt1.getTime();
	         System.err.println("0::::::::::::"+ System.currentTimeMillis());
	         
			System.err.println("此状态值低于"+demandContextValue+"需要提高值!");
			SmartCaseController.upTempertureFunction(context);
			//实验时间测试
			Date dt2=new Date();//如果不需要格式,可直接用dt,dt就是当前系统时间
	        DateFormat df2 = new SimpleDateFormat("yyyy：MM：dd HH:mm:ss");//设置显示格式
	        String nowTime2="";
	        nowTime2= df2.format(dt2);
	        System.err.println("0::::::::::::"+nowTime2);
	        long t2=dt2.getTime();
	        System.err.println("0::::::::::::"+t2);
			
	        long between=0;
	        between=t2-t1;
	        System.err.println("between::::::::::::"+between);
		}
		while(context.getContextValue()>=demandContextValue){
			
			Date dt1=new Date();//如果不需要格式,可直接用dt,dt就是当前系统时间
	         DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");//设置显示格式
	         String nowTime1="";
	         nowTime1= df.format(dt1);
	         System.err.println("0::::::::::::"+nowTime1);
	         long t1=dt1.getTime();
	         System.err.println("0::::::::::::"+ System.currentTimeMillis());
	         
	         
			System.err.println("此状态值低于"+demandContextValue+"需要提高值!");
			SmartCaseController.downTempertureFunction(context);
			//实验时间测试
			Date dt2=new Date();//如果不需要格式,可直接用dt,dt就是当前系统时间
	        DateFormat df2 = new SimpleDateFormat("yyyy：MM：dd HH:mm:ss");//设置显示格式
	        String nowTime2="";
	        nowTime2= df2.format(dt2);
	        System.err.println("0::::::::::::"+nowTime2);
	        long t2=dt2.getTime();
	        System.err.println("0::::::::::::"+t2);
			
	        long between=0;
	        between=t2-t1;
	        System.err.println("between::::::::::::"+between);
		}
		

			
	    return context;
	}
	
	
	
	/**
	 * 以下是实验测试部分=============================================================================================
	 * 2018-05-02
	 * @param locationName
	 * @param contextName
	 * @param describe
	 * @param contextValue
	 * @return
	 */

	
	@RequestMapping(value="/demand-engine-ours")
	@ResponseBody
	public Object testours(@RequestParam("contextName") String contextName,@RequestParam("demandContextValue") double demandContextValue) {
		long test=0;
		int testc=0;
		
		//01、根据状态名称找到对应的状态
		System.out.println("start=ours=method");
		List<Context> contextList=contextService.findByContextName(contextName);
		Context context;
		
		 Date dt=new Date();//如果不需要格式,可直接用dt,dt就是当前系统时间
         DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");//设置显示格式
         String nowTime="";
         nowTime= df.format(dt);       
         long t=dt.getTime();		
		
		if(contextList.size()>0){
		context=contextList.get(0);	
		
		}
		else{
		context=null;
		
		}
		double value=context.getContextValue();		
		while(value<demandContextValue){
			
			Date dt0=new Date();//如果不需要格式,可直接用dt,dt就是当前系统时间
	         DateFormat df0 = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");//设置显示格式
	         String nowTime0="";
	         nowTime0= df0.format(dt0);	     
	         long t0=dt0.getTime();	       		
			List<Serviice> serviiceList=serviiceService.findServiiceByContextName(contextName);
			for(int i=0;i<serviiceList.size();i++){
				String describeService=serviiceList.get(i).getDescribe();//具体根据描述文件比较好！
			}
			for(int i=0;i<serviiceList.size();i++){
				String describeService=serviiceList.get(i).getDescribe();
				if(describeService.indexOf("up"+contextName)!=-1){
					String serviiceName=serviiceList.get(i).getServiiceName();
					List<Function> funcs=functionService.findFunctionByServiiceName(serviiceName);
					for(int j=0;j<funcs.size();j++){					
						if(funcs.get(j).getDescribe().indexOf("up"+contextName)!=-1){							
							double contextValueNew=0.0;
							if("Humidity".equals(context.getContextName())){
								contextValueNew=SmartCaseController.upHumidityFunction(context);								
							}
							else if("Temperture".equals(context.getContextName())){								
									contextValueNew=SmartCaseController.upTempertureFunction(context);							
							}
							contextService.setContextValueByFunction(contextName, contextValueNew);
							break;
						}		
					}			
				}
			}	

			Date dt2=new Date();//如果不需要格式,可直接用dt,dt就是当前系统时间
	        DateFormat df2 = new SimpleDateFormat("yyyy：MM：dd HH:mm:ss");//设置显示格式
	        String nowTime2="";
	        nowTime2= df2.format(dt2);	        
	        long t2=dt2.getTime();	       
			
	        long between=0;
	        between=t2-t0;
	        System.out.println(between);
	        
	        
	        test=test+between;
	        testc++;
	        
	        
			value=context.getContextValue();
			//System.err.println("当前状态值为："+value);
		}
		while(value>demandContextValue){
			
			Date dt0=new Date();//如果不需要格式,可直接用dt,dt就是当前系统时间
	         DateFormat df0 = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");//设置显示格式
	         String nowTime0="";
	         nowTime0= df0.format(dt0);	         
	         long t0=dt0.getTime();			
			if("Humidity".equals(context.getContextName())){				
				return new MSG("没有降低湿度的设备可以使用");
			}			
			List<Serviice> serviiceList=serviiceService.findServiiceByContextName(contextName);			
			for(int i=0;i<serviiceList.size();i++){				
				String describeService=serviiceList.get(i).getDescribe();//具体根据描述文件比较好！
			}
			for(int i=0;i<serviiceList.size();i++){
				String describeService=serviiceList.get(i).getDescribe();
				if(describeService.indexOf("down"+contextName)!=-1){					
					//存在有up这个状态的服务
					//寻找和该状态有关的功能
					String serviiceName=serviiceList.get(i).getServiiceName();
					List<Function> funcs=functionService.findFunctionByServiiceName(serviiceName);
					for(int j=0;j<funcs.size();j++){						
						if(funcs.get(j).getDescribe().indexOf("down"+contextName)!=-1){														
							double contextValueNew=SmartCaseController.downTempertureFunction(context);
							contextService.setContextValueByFunction(contextName, contextValueNew);
							break;
						}		
					}			
				}else{
					break;
				}
			}	
			Date dt2=new Date();//如果不需要格式,可直接用dt,dt就是当前系统时间
	        DateFormat df2 = new SimpleDateFormat("yyyy：MM：dd HH:mm:ss");//设置显示格式
	        String nowTime2="";
	        nowTime2= df2.format(dt2);	        
	        long t2=dt2.getTime();	     			
	        long between=0;
	        between=t2-t0;
	        System.err.println(between);
	        
	        test=test+between;
	        testc++;
	        
			value=context.getContextValue();
			//System.err.println("当前状态值为："+value);
		}
		System.err.println("平均耗时："+(test/testc));		
	    return context;
	}
	
	
	//模拟一般方法
	@RequestMapping(value="/demand-engine-common")
	@ResponseBody
	public Object testcommon(@RequestParam("contextName") String contextName,@RequestParam("demandContextValue") double demandContextValue) {
		//01、根据状态名称找到对应的状态
		System.out.println("start=common=method");
		List<Context> contextList=contextService.findByContextName(contextName);
		Context context;
		
		 Date dt=new Date();//如果不需要格式,可直接用dt,dt就是当前系统时间
         DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");//设置显示格式
         String nowTime="";
         nowTime= df.format(dt);       
         long t=dt.getTime();
		if(contextList.size()>0){
		context=contextList.get(0);	
		}
		else{
		context=null;
		}
		while(context.getContextValue()<demandContextValue){
			
			Date dt1=new Date();//如果不需要格式,可直接用dt,dt就是当前系统时间
	         DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");//设置显示格式
	         String nowTime1="";
	         nowTime1= df.format(dt1);
	         long t1=dt1.getTime();
			SmartCaseController.upTempertureFunction(context);
			//实验时间测试
			Date dt2=new Date();//如果不需要格式,可直接用dt,dt就是当前系统时间
	        DateFormat df2 = new SimpleDateFormat("yyyy：MM：dd HH:mm:ss");//设置显示格式
	        String nowTime2="";
	        nowTime2= df2.format(dt2);
	        long t2=dt2.getTime();
	        long between=0;
	        between=t2-t1;
	        System.err.println(between);
		}
		while(context.getContextValue()>=demandContextValue){
			
			Date dt1=new Date();//如果不需要格式,可直接用dt,dt就是当前系统时间
	         DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");//设置显示格式
	         String nowTime1="";
	         nowTime1= df.format(dt1);
	         long t1=dt1.getTime();	      
			SmartCaseController.downTempertureFunction(context);
			//实验时间测试
			Date dt2=new Date();//如果不需要格式,可直接用dt,dt就是当前系统时间
	        DateFormat df2 = new SimpleDateFormat("yyyy：MM：dd HH:mm:ss");//设置显示格式
	        String nowTime2="";
	        nowTime2= df2.format(dt2);	       
	        long t2=dt2.getTime();
	        long between=0;
	        between=t2-t1;
	        System.err.println(between);
		}	
	    return context;
	}
	
	/**
	 * 以上是实验测试部分=============================================================================================
	 * @param locationName
	 * @param contextName
	 * @param describe
	 * @param contextValue
	 * @return
	 */
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value="/demand-engine0")
	@ResponseBody
	public Object test(@RequestParam String locationName,@RequestParam String contextName,@RequestParam String describe,@RequestParam double contextValue) {
			
	    return 0;
	}
	
	/**
	 * 
	 * 这里放一个解析配置文件String的create方法
	 * 
	 * 
	 * @AlisaYe
	 * 
	 */
	
	/**
	 * 该方法模拟从配置文件中读取场景
	 * 用知识图谱的概念创建整体场景的过程
	 * 在http://127.0.0.1:7474/中可以展示当前的场景
	 * @return
	 */
	@RequestMapping(value="/createCaseByXML")
	@ResponseBody
	public MSG crateSmartFlowerCaseByXML(){
		TestDom4j t=new TestDom4j();
    	String x=t.getXMLtoString();
    	String[] s=x.split("\\{EntityType=\"");
    	//分别创建六个对象  
    	//建立配置文件中的所有节点
    	for(String i:s){
    		String[] classNameList=i.split("\"");
    		String className=classNameList[0];//className等于记录的类的名字    		   		
    		//创建Device对象
    		if(className.equals("Device")){
    			String[] eidList=i.split("\",eid=\"");
    			String[] eidValueList=eidList[1].split("\"");
    			String eid=eidValueList[0];//eid是建立关联和联系的重要属性，每一个对象的唯一标志
        		String name=eidValueList[2];//name是每个属性的名字*最终的名字是eid加name作为名字
        		String function=eidValueList[4];//functionList是设备联系的所有功能的集合
        		String description="{name:"+name+" functionList:"+function+"}";
        		Device d=new Device(eid,description);
        		deviceService.createDevice(d);
    		}   		   	
    		// 创建Function对象
    		if(className.equals("Function")){
    			String[] eidList=i.split("\",eid=\"");
    			String[] eidValueList=eidList[1].split("\"");
    			String eid=eidValueList[0];//eid是建立关联和联系的重要属性，每一个对象的唯一标志
        		String name=eidValueList[2];//name是每个属性的名字*最终的名字是eid加name作为名字
        		String path=eidValueList[4];//path也是function中需要存储的部分
        		String serviice=eidValueList[6];//functionList是设备联系的所有功能的集合
        		String description="{name:"+name+" serviiceList:"+serviice+"}";      		
        		Function f=new Function(eid,path,description);
        		functionService.createFunction(f);
    		}    		
    		//创建Context对象
    		if(className.equals("Context")){
    			String[] eidList=i.split("\",eid=\"");
    			String[] eidValueList=eidList[1].split("\"");
    			String eid=eidValueList[0];//eid是建立关联和联系的重要属性，每一个对象的唯一标志
        		String name=eidValueList[2];//name是每个属性的名字*最终的名字是eid加name作为名字
        		String serviice=eidValueList[4];//serviice是设备联系的所有功能的集合
        		String values=eidValueList[6]; 
        		double value=0.0;
        		try {
        		    value = Integer.parseInt(values);
        		} catch (NumberFormatException e) {
        		    e.printStackTrace();
        		}
        		String description="{name:"+name+" serviiceList:"+serviice+"}";        		
        		Context c=new Context(eid,description,value); 
        		contextService.createContext(c);	
    		}
    		//创建Serviice对象
    		if(className.equals("Serviice")){
    			String[] eidList=i.split("\",eid=\"");
    			String[] eidValueList=eidList[1].split("\"");
    			String eid=eidValueList[0];//eid是建立关联和联系的重要属性，每一个对象的唯一标志
        		String name=eidValueList[2];//name是每个属性的名字*最终的名字是eid加name作为名字
        		String description="{name:"+name+"}"; 		
        		Serviice hs1=new Serviice(eid,description); 
        		serviiceService.createServiice(hs1);
    		}
    	
    		//创建Location对象
    		if(className.equals("Location")){
    			String[] eidList=i.split("\",eid=\"");
    			String[] eidValueList=eidList[1].split("\"");
    			String eid=eidValueList[0];//eid是建立关联和联系的重要属性，每一个对象的唯一标志
        		String name=eidValueList[2];//name是每个属性的名字*最终的名字是eid加name作为名字
        		String description0=eidValueList[4];//functionList是设备联系的所有功能的集合
        		String device0=eidValueList[6];//functionList是设备联系的所有功能的集合
        		String context0=eidValueList[8];//functionList是设备联系的所有功能的集合
        		String user0=eidValueList[10];//functionList是设备联系的所有功能的集合
        		String description="{name:"+name+" description:"+description0+" device:"+device0+" context:"+context0+" user:"+user0+"}";        	
        		Location loca=new Location(eid,description);
        		locationService.createLocation(loca);
    		}
    		//创建User对象
    		if(className.equals("User")){
    			String[] eidList=i.split("\",eid=\"");
    			String[] eidValueList=eidList[1].split("\"");
    			String eid=eidValueList[0];//eid是建立关联和联系的重要属性，每一个对象的唯一标志
        		String name=eidValueList[2];//name是每个属性的名字*最终的名字是eid加name作为名字
        		String description0=eidValueList[4];//functionList是设备联系的所有功能的集合       		
        		String description="{name:"+name+" description:"+description0+"}";        	
        		User u=new User(eid,description);
        		userService.createUser(u);
    		}

    	}
    	//建立所有节点之间的关系
    	for(String ii:s){
    		String[] classNameList=ii.split("\"");
    		String className=classNameList[0];//className等于记录的类的名字    		
    		//分别创建六个对象     		
    		//创建Device对象
    		if(className.equals("Device")){
    			String[] eidList=ii.split("\",eid=\"");
    			String[] eidValueList=eidList[1].split("\"");
    			String eid=eidValueList[0];//eid是建立关联和联系的重要属性，每一个对象的唯一标志
        		String name=eidValueList[2];//name是每个属性的名字*最终的名字是eid加name作为名字
        		String function=eidValueList[4];//functionList是设备联系的所有功能的集合
        		String description="{name:"+name+" functionList:"+function+"}";
        		String[] functionList=function.split("\\|");
        		for(String f:functionList){
        			//functionService.addFunction(device, f);
        			if(f.length()>0){
        				if(functionService.findByFunctionName(f).size()>0){
        					Function function0=functionService.findByFunctionName(f).get(0);
        					if(deviceService.findByDeviceName(eid).size()>0){
        						Device device0=deviceService.findByDeviceName(eid).get(0);
        						functionService.addFunction(device0, function0);
        					}
        				}      				
        			};        			
        		}
    		}   		    		
    		// 创建Function对象
    		if(className.equals("Function")){
    			String[] eidList=ii.split("\",eid=\"");
    			String[] eidValueList=eidList[1].split("\"");
    			String eid=eidValueList[0];//eid是建立关联和联系的重要属性，每一个对象的唯一标志
        		String name=eidValueList[2];//name是每个属性的名字*最终的名字是eid加name作为名字
        		String path=eidValueList[4];//path也是function中需要存储的部分
        		String serviice=eidValueList[6];//functionList是设备联系的所有功能的集合
        		String description="{name:"+name+" serviiceList:"+serviice+"}";
        		String[] serviiceList=serviice.split("\\|");        		
        		for(String sii:serviiceList){
        			//serviiceService.addServiice(fs1, s1);
        			if(sii.length()>0){
        				if(functionService.findByFunctionName(eid).size()>0){
        					Function function0=functionService.findByFunctionName(eid).get(0);
        					if(serviiceService.findByServiiceName(sii).size()>0){
        						Serviice serviice0=serviiceService.findByServiiceName(sii).get(0);
        						serviiceService.addServiice(function0, serviice0);
        					}					
        				}       				
        			}       			
        		}	
    		}    		
    		//创建Context对象
    		if(className.equals("Context")){
    			String[] eidList=ii.split("\",eid=\"");
    			String[] eidValueList=eidList[1].split("\"");
    			String eid=eidValueList[0];//eid是建立关联和联系的重要属性，每一个对象的唯一标志
        		String name=eidValueList[2];//name是每个属性的名字*最终的名字是eid加name作为名字
        		String serviice=eidValueList[4];//serviice是设备联系的所有功能的集合
        		String value=eidValueList[6];
        		String description="{name:"+name+" serviiceList:"+serviice+"}";
        		String[] serviiceList=serviice.split("\\|");
        		for(String sii:serviiceList){
        			//contextService.addContext(hs1, hum);
        			if(sii.length()>0){
        				if(serviiceService.findByServiiceName(sii).size()>0){
        					Serviice serviice0=serviiceService.findByServiiceName(sii).get(0);
        					if(contextService.findByContextName(eid).size()>0){
                				Context context0=contextService.findByContextName(eid).get(0);
                				contextService.addContext(serviice0, context0);
        					}       					
        				}
        			}        			
        		}
    		}   	
    		//创建Location对象
    		if(className.equals("Location")){
    			String[] eidList=ii.split("\",eid=\"");
    			String[] eidValueList=eidList[1].split("\"");
    			String eid=eidValueList[0];//eid是建立关联和联系的重要属性，每一个对象的唯一标志
        		String name=eidValueList[2];//name是每个属性的名字*最终的名字是eid加name作为名字
        		String description0=eidValueList[4];//functionList是设备联系的所有功能的集合
        		String device0=eidValueList[6];//functionList是设备联系的所有功能的集合
        		String context0=eidValueList[8];//functionList是设备联系的所有功能的集合
        		String user0=eidValueList[10];//functionList是设备联系的所有功能的集合
        		String description="{name:"+name+" description:"+description0+" device:"+device0+" context:"+context0+" user:"+user0+"}";
        		String[] device0List=device0.split("\\|");
        		for(String d0:device0List){
        			//deviceService.addDevice(loca, d);
        			if(d0.length()>0){
        				if(deviceService.findByDeviceName(d0).size()>0){
        					Device device00=deviceService.findByDeviceName(d0).get(0);
        					if(locationService.findByLocationName(eid).size()>0){
                				Location location0=locationService.findByLocationName(eid).get(0);
                				deviceService.addDevice(location0, device00);
        					}
        				}
        			}	
        		}
        		String[] context0List=context0.split("\\|");
        		for(String c0:context0List){
        			//contextService.addContext(loca, c);
        			if(c0.length()>0){
        				if(locationService.findByLocationName(eid).size()>0){
        					Location location0=locationService.findByLocationName(eid).get(0);
        					if(contextService.findByContextName(c0).size()>0){
        						Context context00=contextService.findByContextName(c0).get(0);
                				contextService.addContext(location0, context00);  
        					}
        				}        			   				     				
        			}
        		}
        		String[] user0List=user0.split("\\|");
        		for(String u0:user0List){
        			//userService.addUser(loca, u);
        			if(u0.length()>0){
        				if(locationService.findByLocationName(eid).size()>0){
        					Location location0=locationService.findByLocationName(eid).get(0);
        					if(userService.findByUserName(u0).size()>0){
        						User user00=userService.findByUserName(u0).get(0);
                				userService.addUser(location0, user00);
        					}        					
        				}        				        				
        			}        			
        		}
        		//Location loca=new Location("floor001","floor001");        	
    		}
    	}
		return new MSG("导入对象成功！");
	}
	
	

	
}
