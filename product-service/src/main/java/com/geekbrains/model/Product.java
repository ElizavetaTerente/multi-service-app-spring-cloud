package com.geekbrains.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Product product = (Product) o;
		return Objects.equals(title, product.title);
	}

	@Override
	public int hashCode() {
		return Objects.hash(title);
	}
}
