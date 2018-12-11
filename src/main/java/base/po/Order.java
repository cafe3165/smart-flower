package base.po;


import java.util.HashSet;
import java.util.Set;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Order {
	@GraphId
	private Long id;
	private String customer;
	private String type;
	
//	AlisaYe 2017/07/20
//	这个节点指向的另外一个节点，关系和关系的type是直接打标签的
//	这个节点直接的关系是通过哈希集合来实现的
 	@RelatedTo(type="has_item")
 	Set<Item> items=new HashSet<Item>();
	public Order() {
	}
	
	

	public Order( String customer, String type) {
		this.customer = customer;
		this.type = type;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public Set<Item> getItems() {
//		return items;
//	}
//
//	public void setItems(Set<Item> items) {
//		this.items = items;
//	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

//	AlisaYe 2017/07/20
//	order[id="01",costomer="mark",type="VIP"]
//	描述某一个节点的信息
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", customer=" + customer + ", type=" + type
				+ "]";
	}

	
}
