package base.po;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class Context {	
	@GraphId
	private Long id;
	private String contextName;
	private String describe;
	private double contextValue;
	
	public Context() {
		
	}
	
	public Context(String contextName) {
		this.contextName = contextName;
	}
	
	public Context(String contextName, String describe) {
		this.contextName = contextName;
		this.describe = describe;
	}
	
	public Context(String contextName, String describe, double contextValue) {
		this.contextName = contextName;
		this.describe = describe;
		this.contextValue = contextValue;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContextName() {
		return contextName;
	}
	public void setContextName(String contextName) {
		this.contextName = contextName;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
	public double getContextValue() {
		return contextValue;
	}

	public void setContextValue(double contextValue) {
		this.contextValue = contextValue;
	}

	@Override
	public String toString() {
		return "Context [id=" + id + ", contextName=" + contextName + ", describe=" + describe + ", contextValue="
				+ contextValue + "]";
	}

	
	
}
