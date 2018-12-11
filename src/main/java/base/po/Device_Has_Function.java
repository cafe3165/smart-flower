package base.po;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

@RelationshipEntity(type="device_has_function")
public class Device_Has_Function {
	@GraphId
	private Long id;
	
	@StartNode
	private Device device;
	
	@EndNode
	private Function function;
	
	private String describe;
	private int number;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Device getDevice() {
		return device;
	}
	public void setDevice(Device device) {
		this.device = device;
	}
	public Function getFunction() {
		return function;
	}
	public void setFunction(Function function) {
		this.function = function;
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
	public Device_Has_Function(Device device, Function function,int number) {
		this.device = device;
		this.function = function;
		this.number = number;
	}
	public Device_Has_Function() {
		
	}
	
	
	
	
	
	
}
