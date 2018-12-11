
package base.controller;



import java.util.HashSet;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.data.neo4j.conversion.Result;

import base.pagemodel.MSG;
import base.po.Item;
import base.po.Order;
import base.service.impl.ItemService;
import base.service.impl.OrderService;


@Controller
public class Neo4jController {
	
	
	@Autowired
	OrderService orderservice;
	
	@Autowired
	ItemService itemservice;
	
	//创建养花设备场景（模拟：从配置文件中读取相关设备功能、服务状态等）
	@RequestMapping(value="/createOrderAndItem")
	@ResponseBody
	public MSG showpic(){
		//创建中心节点
		Order o=new Order("mark","VIP"); 
        orderservice.createOrder(o);
        //创建外围节点
        for(int i=0;i<2;i++){
        	Item item=new Item("book"+String.valueOf(i),(double)i);
        	itemservice.createItem(item);
        }
        Order order=orderservice.findByCustomer("mark").get(0);
        //加边
        	Result<Item> items = itemservice.getAllItems();
        	Iterator<Item> it=items.iterator();
        	while(it.hasNext()){
        		Item t=it.next();
        		itemservice.addLine(order, t, 5);
        	}
		return new MSG("success");
	}
	
	@RequestMapping(value="/deleteOrderAndItem")
	@ResponseBody
	public MSG a(){
		itemservice.deleteall();
		orderservice.deleteall();
		return new MSG("success");
	}
	
	
	//创建AlisaYE 
	//检测热部署是否成功
	@RequestMapping(value="/createABC")
	@ResponseBody
	public MSG showpicA(){
		return new MSG("热部署成功!!!ABC");
	}
	
	
	
	
}
