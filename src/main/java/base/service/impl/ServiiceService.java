package base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import base.dao.ContextRepository;
import base.dao.Function_CouldProvide_Serviice_Repository;
import base.dao.ServiiceRepository;
import base.po.Context;
import base.po.Device;
import base.po.Device_Has_Function;
import base.po.Function;
import base.po.Function_CouldProvide_Service;
import base.po.Serviice;

@Service
@Transactional
public class ServiiceService {
	@Autowired
	private ServiiceRepository serviiceRepository;
	
	//关系的尾巴节点&关系的类	
	@Autowired
	Function_CouldProvide_Serviice_Repository function_CouldProvide_Serviice_Repository;
	
	public long getNumberOfServiice() {
		return serviiceRepository.count();
	}
	
	public Serviice createServiice(Serviice s) {
		return serviiceRepository.save(s);
	}
	
	public Iterable<Serviice> getAllServiices() {
		return serviiceRepository.findAll();
	}
	
	public Serviice findServiiceById(Long id) {
		return serviiceRepository.findOne(id);
	}

	public void deleteall(){
		serviiceRepository.deleteAll();
	}
	
	public List<Serviice> findByServiiceName(String serviiceName){
		return serviiceRepository.findByServiiceName(serviiceName);
	}
	public List<Serviice> findByServiiceNameAndDescribe(String serviiceName,String describe){
		return serviiceRepository.findByServiiceNameAndDescribe(serviiceName,describe);
	}
	
	public void addServiice(Function function,Serviice serviice,int num){
		Function_CouldProvide_Service function_CouldProvide_Service=new Function_CouldProvide_Service();
		function_CouldProvide_Service.setService(serviice);
		function_CouldProvide_Service.setFunction(function);
		function_CouldProvide_Service.setNumber(num);
		function_CouldProvide_Serviice_Repository.save(function_CouldProvide_Service);
	}
	
	public void addServiice(Function function,Serviice serviice){
		Function_CouldProvide_Service function_CouldProvide_Service=new Function_CouldProvide_Service();
		function_CouldProvide_Service.setService(serviice);
		function_CouldProvide_Service.setFunction(function);
		function_CouldProvide_Serviice_Repository.save(function_CouldProvide_Service);
	}
	
	public List<Serviice> findServiiceByContextName(@Param("contextName") String contextName){
		return serviiceRepository.findServiiceByContextName(contextName);
	}


	
	
}
