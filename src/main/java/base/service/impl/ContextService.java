package base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import base.dao.ContextRepository;
import base.dao.Location_Has_Context_Repository;
import base.dao.Serviice_CouldChange_Context_Repository;
import base.po.Context;
import base.po.Device;
import base.po.Device_Has_Function;
import base.po.Function;
import base.po.Location;
import base.po.Location_Has_Context;
import base.po.Serviice;
import base.po.Serviice_CouldChange_Context;

@Service
@Transactional
public class ContextService {
	@Autowired
	private ContextRepository contextRepository;
	
	//关系的尾巴节点&关系的类	
	@Autowired
	Serviice_CouldChange_Context_Repository serviice_CouldChange_Context_Repository;
	@Autowired
	Location_Has_Context_Repository location_Has_Context_Repository;
	
	public long getNumberOfContext() {
		return contextRepository.count();
	}
	
	public Context createContext(Context c) {
		return contextRepository.save(c);
	}
	
	public Context saveContextValue(Context c) {
		return contextRepository.save(c);
	}
	
	public Iterable<Context> getAllContexts() {
		return contextRepository.findAll();
	}
	
	public Context findContextById(Long id) {
		return contextRepository.findOne(id);
	}

	public void deleteall(){
		contextRepository.deleteAll();
	}
	
	public List<Context> findByContextName(String contextName){
		return contextRepository.findByContextName(contextName);
	}
	public List<Context> findByContextNameAndDescribe(String contextName,String describe){
		return contextRepository.findByContextNameAndDescribe(contextName,describe);
	}
	
	public void addContext(Serviice serviice,Context context,int num){
		Serviice_CouldChange_Context serviice_CouldChange_Context=new Serviice_CouldChange_Context();
		serviice_CouldChange_Context.setContext(context);
		serviice_CouldChange_Context.setServiice(serviice);
		serviice_CouldChange_Context.setNumber(num);
		serviice_CouldChange_Context_Repository.save(serviice_CouldChange_Context);
	}
	
	public void addContext(Serviice service,Context context){
		Serviice_CouldChange_Context serviice_CouldChange_Context=new Serviice_CouldChange_Context();
		serviice_CouldChange_Context.setContext(context);
		serviice_CouldChange_Context.setServiice(service);
		serviice_CouldChange_Context_Repository.save(serviice_CouldChange_Context);
	}
	

	public void addContext(Location location,Context context,int num){
		Location_Has_Context ocation_Has_Context=new Location_Has_Context();
		ocation_Has_Context.setContext(context);
		ocation_Has_Context.setLocation(location);
		ocation_Has_Context.setNumber(num);
		location_Has_Context_Repository.save(ocation_Has_Context);
	}
	
	public void addContext(Location location,Context context){
		Location_Has_Context ocation_Has_Context=new Location_Has_Context();
		ocation_Has_Context.setContext(context);
		ocation_Has_Context.setLocation(location);
		location_Has_Context_Repository.save(ocation_Has_Context);
	}
	
	public Context setContextValueByFunction(@Param("contextName") String contextName,@Param("contextValue") double contextValue){
		return contextRepository.setContextValueByFunction(contextName, contextValue);
	}
	
	
}
