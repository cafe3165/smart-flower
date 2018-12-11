package base.dao;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import base.po.Function;
import base.po.Order;
import base.po.Serviice;

public interface FunctionRepository extends GraphRepository<Function>{
	List<Function> findByFunctionName(String functionName);
	List<Function> findByFunctionNameAndDescribe(String functionName,String describe);
	
	@Query("MATCH(a:Serviice{serviiceName:{serviiceName}})-[r:function_couldprovide_service]-(b:Function) return b")
	List<Function> findFunctionByServiiceName(@Param("serviiceName") String serviiceName);
}
