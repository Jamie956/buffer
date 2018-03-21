package com.example;

import java.util.List;

public class Order {
	private String id;
	private String name;
	private List<Item> itemlist;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Item> getItemlist() {
		return itemlist;
	}
	public void setItemlist(List<Item> itemlist) {
		this.itemlist = itemlist;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", name=" + name + ", itemlist=" + itemlist + "]";
	}
	
}
