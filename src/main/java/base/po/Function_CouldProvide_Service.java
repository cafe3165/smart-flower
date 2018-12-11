package base.po;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

@RelationshipEntity(type="function_couldprovide_service")
public class Function_CouldProvide_Service {
	@GraphId
	private Long id;
	
	@StartNode
	private Function function;
	
	@EndNode
	private Serviice service;
	
	private String describe;
	private int number;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Function getFunction() {
		return function;
	}
	public void setFunction(Function function) {
		this.function = function;
	}
	public Serviice getService() {
		return service;
	}
	public void setService(Serviice service) {
		this.service = service;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Function_CouldProvide_Service() {

	}
	public Function_CouldProvide_Service(Function function, Serviice service, int number) {
		this.function = function;
		this.service = service;
		this.number = number;
	}

	
	
	
	
	
	
}
