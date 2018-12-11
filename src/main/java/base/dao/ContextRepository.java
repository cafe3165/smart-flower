package base.dao;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import base.po.Context;
import base.po.Device;
import base.po.Serviice;

public interface ContextRepository extends GraphRepository<Context>{
	List<Context> findByContextName(String contextName);
	List<Context> findByContextNameAndDescribe(String contextName,String describe);
	
	//根据状态的名称修改状态的值。。。
	@Query("MATCH (n:Context{contextName:{contextName}}) set n.contextValue = {contextValue} return n")
	Context setContextValueByFunction(@Param("contextName") String contextName,@Param("contextValue") double contextValue);
	
}
