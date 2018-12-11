package base.po;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class Serviice {
	
	@GraphId
	private Long id;
	private String serviiceName;
	private String describe;
	
	
	public Serviice() {

	}
	
	public Serviice(String serviiceName) {
		this.serviiceName = serviiceName;
	}
	

	public Serviice(String serviiceName, String describe) {
		this.serviiceName = serviiceName;
		this.describe = describe;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getServiiceName() {
		return serviiceName;
	}
	public void setServiiceName(String serviiceName) {
		this.serviiceName = serviiceName;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}

	@Override
	public String toString() {
		return "Serviice [id=" + id + ", serviiceName=" + serviiceName + ", describe=" + describe + "]";
	}
	
	
	
}
