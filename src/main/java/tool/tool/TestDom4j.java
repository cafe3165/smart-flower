package tool.tool;

import org.dom4j.io.SAXReader;

import base.po.Context;
import base.po.Device;
import base.po.Function;
import base.po.Location;
import base.po.Serviice;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element; 
import org.dom4j.Attribute;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException; 

public class TestDom4j {
    /**
     * 获取指定xml文档的Document对象,xml文件必须在classpath中可以找到
     *
     * @param xmlFilePath xml文件路径
     * @return Document对象
     */ 
    public static Document parse2Document(String xmlFilePath){
        SAXReader reader = new SAXReader();
        Document doc = null;
        try {
            doc = reader.read(new File(xmlFilePath));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return doc;
    }
    
    public static String testParseXmlData(String xmlFilePath){
        //获取xml解析器对象
        //SAXReader reader = new SAXReader();
        //将xml解析为Document对象
        Document doc = TestDom4j.parse2Document(xmlFilePath);
        //获取文档的根元素
        Element root  = doc.getRootElement();
        //定义保存xml数据的缓冲字符串
        StringBuffer sb = new StringBuffer();
        List<Object> list=new ArrayList<Object>();      
        for(Iterator i_action=root.elementIterator();i_action.hasNext();){
            Element e_action = (Element)i_action.next();
            sb.append("{EntityType="+"\""+e_action.getName()+"\"");
            for(Iterator a_action=e_action.attributeIterator();a_action.hasNext();){
                Attribute attribute = (Attribute)a_action.next();
                sb.append(","+attribute.getName().toString()+"=\""+attribute.getValue()+"\"");               
            }
            sb.append("}\n");
        }
        System.out.println(sb);
        return sb.toString();
        
    }
    
//    public static void main(String[] args) {
//    	TestDom4j t=new TestDom4j();
//    	String line=t.readToString("D:\\012345\\caseConfigure.txt");
//    	String sread=line.replace('\\', '/');
////        TestDom4j.testParseXmlData("F:/WorkSpaceTestNewk/xmlReflction/test.xml");
//        TestDom4j.testParseXmlData(sread);
//    }
 
    public static void main(String[] args) {
    	TestDom4j t=new TestDom4j();
    	String x=t.getXMLtoString();
//    	System.err.println("x="+x);
    	t.testtest(x);
    	
    	
    }
    
    
    public static String getXMLtoString(){
    	TestDom4j t=new TestDom4j();
    	String line=t.readToString("D:\\012345\\caseConfigure.txt");
    	String sread=line.replace('\\', '/');
//        TestDom4j.testParseXmlData("F:/WorkSpaceTestNewk/xmlReflction/test.xml");

        String mainmain=TestDom4j.testParseXmlData(sread); 
        return mainmain;
    }
    
    
    public String readToString(String fileName) {  
        String encoding = "UTF-8";  
        File file = new File(fileName);  
        Long filelength = file.length();  
        byte[] filecontent = new byte[filelength.intValue()];  
        try {  
            FileInputStream in = new FileInputStream(file);  
            in.read(filecontent);  
            in.close();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        try {  
            return new String(filecontent, encoding);  
        } catch (UnsupportedEncodingException e) {  
            System.err.println("The OS does not support " + encoding);  
            e.printStackTrace();  
            return null;  
        }  
    } 
    
    public static void testtest(String x)
    {
    	String[] s=x.split("\\{EntityType=\"");
    	for(String i:s){
    		String[] classNameList=i.split("\"");
    		String className=classNameList[0];//className等于记录的类的名字    		
    		//分别创建六个对象  
    		
    		//创建Device对象
    		if(className.equals("Device")){
    			String[] eidList=i.split("\",eid=\"");
    			String[] eidValueList=eidList[1].split("\"");
    			String eid=eidValueList[0];//eid是建立关联和联系的重要属性，每一个对象的唯一标志
        		String name=eidValueList[2];//name是每个属性的名字*最终的名字是eid加name作为名字
        		String function=eidValueList[4];//functionList是设备联系的所有功能的集合
        		String description="{name:"+name+" functionList:"+function+"}";
        		String[] functionList=function.split("\\|");
        		for(String f:functionList){
        			//functionService.addFunction(device, f);
        			if(f.length()>0)
        			System.out.println("name:"+eid+"  function:"+f);
        		}
        		//Device d=new Device(name,describtion)
        		System.err.println("name="+eid+"   describtion:"+description);
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
        		String[] serviiceList=serviice.split("\\|");        		
        		for(String sii:serviiceList){
        			//serviiceService.addServiice(fs1, s1);
        			if(sii.length()>0)
        			System.err.println("name:"+eid+"  serviice:"+sii);
        		}	
        		//Function f1=new Function("upTempertureFunction","URI-1","upTempertureFunction");
        		System.err.println("name="+eid+"  path="+path+"   describtion="+description);
    		}
    		
    		//创建Context对象
    		if(className.equals("Context")){
    			String[] eidList=i.split("\",eid=\"");
    			String[] eidValueList=eidList[1].split("\"");
    			String eid=eidValueList[0];//eid是建立关联和联系的重要属性，每一个对象的唯一标志
        		String name=eidValueList[2];//name是每个属性的名字*最终的名字是eid加name作为名字
        		String serviice=eidValueList[4];//serviice是设备联系的所有功能的集合
        		String value=eidValueList[6];
        		String description="{name:"+name+" serviiceList:"+serviice+"}";
        		String[] serviiceList=serviice.split("\\|");
        		for(String sii:serviiceList){
        			//contextService.addContext(hs1, hum);
        			if(sii.length()>0)
        			System.out.println("name:"+eid+"  serviice:"+sii);
        		}
        		//Context c=new Context("Temperture","Temperture",23.0);
        		System.err.println("name="+eid+"   describtion:"+description+"  value:"+value);
    		}
    		//创建Serviice对象
    		if(className.equals("Serviice")){
    			String[] eidList=i.split("\",eid=\"");
    			String[] eidValueList=eidList[1].split("\"");
    			String eid=eidValueList[0];//eid是建立关联和联系的重要属性，每一个对象的唯一标志
        		String name=eidValueList[2];//name是每个属性的名字*最终的名字是eid加name作为名字
        		String description="{name:"+name+"}"; 		
        		//Serviice hs1=new Serviice("upHumidityService","upHumidityService");
        		System.err.println("name="+eid+"   describtion:"+description);
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
        		String[] device0List=device0.split("\\|");
        		for(String d0:device0List){
        			//deviceService.addDevice(loca, d);
        			if(d0.length()>0)
        				System.out.println("name:"+eid+"  device:"+d0);			
        		}
        		String[] context0List=context0.split("\\|");
        		for(String c0:context0List){
        			//contextService.addContext(loca, c);
        			if(c0.length()>0)
        			System.out.println("name:"+eid+"  context:"+c0);
        		}
        		String[] user0List=user0.split("\\|");
        		for(String u0:user0List){
        			//userService.addUser(loca, u);
        			if(u0.length()>0)
        			System.out.println("name:"+eid+"  user:"+u0);
        		}
        		//Location loca=new Location("floor001","floor001");
        		System.err.println("name="+eid+"   describtion:"+description);
    		}

    	}
    	
    }
    
    
    
}
