package base.po;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class Function {

	@GraphId
	private Long id;
	private String functionName;
	private String functionURI;
	private String describe;
	
	
	
	
	public Function() {

	}
	public Function(String functionName, String functionURI) {
		this.functionName = functionName;
		this.functionURI = functionURI;
	}
	
	public Function(String functionName, String functionURI, String describe) {
		this.functionName = functionName;
		this.functionURI = functionURI;
		this.describe = describe;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public String getFunctionURI() {
		return functionURI;
	}
	public void setFunctionURI(String functionURI) {
		this.functionURI = functionURI;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	@Override
	public String toString() {
		return "Function [id=" + id + ", functionName=" + functionName + ", functionURI=" + functionURI + ", describe="
				+ describe + "]";
	}

	
	
	
	
	
}
