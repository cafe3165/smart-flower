package base.po;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

@RelationshipEntity(type="location_has_context")
public class Location_Has_Context {
	@GraphId
	private Long id;
	
	@StartNode
	private Location location;
	
	@EndNode
	private Context context;
	
	private String describe;
	private int number;
	public Location_Has_Context() {

	}
	public Location_Has_Context(Location location, Context context) {
		this.location = location;
		this.context = context;
	}
	public Location_Has_Context(Location location, Context context, int number) {
		this.location = location;
		this.context = context;
		this.number = number;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
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
	
	
	
	
}
