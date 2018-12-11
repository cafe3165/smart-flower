package base.dao;

import java.util.List;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.neo4j.annotation.Query;

import base.po.Device;
import base.po.Serviice;

public interface ServiiceRepository extends GraphRepository<Serviice>{
	List<Serviice> findByServiiceName(String serviiceName);
	List<Serviice> findByServiiceNameAndDescribe(String seviiceName,String describe);
	
	/**
	 * 根据context的contextName,查找所有的指向该状态的服务 
	 */
	@Query("MATCH(a:Context{contextName:{contextName}})-[r:serviice_couldchange_context]-(b:Serviice) return b")
	List<Serviice> findServiiceByContextName(@Param("contextName") String contextName);
	 
}
