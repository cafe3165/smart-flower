package base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import base.dao.Device_Has_Function_Repository;
import base.dao.FunctionRepository;
import base.po.Device;
import base.po.Device_Has_Function;
import base.po.Function;
import base.po.Item;
import base.po.Line;
import base.po.Order;

@Service
@Transactional
public class FunctionService {
	@Autowired
	private FunctionRepository functionRepository;
	
	//关系的尾巴节点&关系的类
	@Autowired
	Device_Has_Function_Repository device_Has_Function_Repository;
	
	
	public long getNumberOfFunctions() {
		return functionRepository.count();
	}
	
	public Function createFunction(Function f) {
		return functionRepository.save(f);
	}
	
	public Result<Function> getAllFunctions() {
		return functionRepository.findAll();
	}
	
	public Function findFunctionById(Long id) {
		return functionRepository.findOne(id);
	}
	public List<Function> findByFunctionName(String functionName) {
		return functionRepository.findByFunctionName(functionName);
	}


	public void deleteall(){
		functionRepository.deleteAll();
	}
	
	public void addFunction(Device device,Function function,int num){
		Device_Has_Function device_Has_Function=new Device_Has_Function();
		device_Has_Function.setFunction(function);
		device_Has_Function.setDevice(device);
		device_Has_Function.setNumber(num);
		device_Has_Function_Repository.save(device_Has_Function);
	}
	
	public void addFunction(Device device,Function function){
		Device_Has_Function device_Has_Function=new Device_Has_Function();
		device_Has_Function.setFunction(function);
		device_Has_Function.setDevice(device);
		device_Has_Function_Repository.save(device_Has_Function);
	}
	public List<Function> findFunctionByServiiceName(@Param("serviiceName") String serviiceName){
		return functionRepository.findFunctionByServiiceName(serviiceName);
	}

}
