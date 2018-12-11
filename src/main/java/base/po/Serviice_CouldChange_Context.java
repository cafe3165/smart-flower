package base.po;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

@RelationshipEntity(type="serviice_couldchange_context")
public class Serviice_CouldChange_Context {
	@GraphId
	private Long id;
	
	@StartNode
	private Serviice serviice;
	
	@EndNode
	private Context context;
	
	private String describe;
	private int number;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Serviice getServiice() {
		return serviice;
	}
	public void setServiice(Serviice serviice) {
		this.serviice = serviice;
	}
	public Context getContext() {
		return context;
	}
	public void setContext(Context context) {
		this.context = context;
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
	public Serviice_CouldChange_Context() {

	}
	public Serviice_CouldChange_Context(Serviice serviice, Context context, int number) {
		this.serviice = serviice;
		this.context = context;
		this.number = number;
	}
	
	
	
	
	

}
