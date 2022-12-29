package com.geekrains.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@Column(name = "title")
	private String title;

	@Column(name = "cost")
	private Double cost;

	public Product() {
	}

	public Product(String title, Double cost) {
		this.title = title;
		this.cost = cost;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "Product{" +
				", title='" + title + '\'' +
				", cost=" + cost +
				'}';
	}

}
