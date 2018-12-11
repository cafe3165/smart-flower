package base.dao;


import java.util.List;

import org.springframework.data.neo4j.repository.GraphRepository;

import base.po.Serviice;
import base.po.Serviice_CouldChange_Context;

public interface Serviice_CouldChange_Context_Repository extends GraphRepository<Serviice_CouldChange_Context>{
	
}
