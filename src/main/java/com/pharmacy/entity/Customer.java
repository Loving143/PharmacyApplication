package com.pharmacy.entity;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.UniqueConstraint;

@Entity
public class Customer extends Person{
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer id;
	  @ManyToMany(fetch = FetchType.EAGER)
	  @JoinTable(
	        name = "customer_roles", 
	        joinColumns = @JoinColumn(name = "customer_id"), 
	        inverseJoinColumns = @JoinColumn(name = "role_id"),
	        uniqueConstraints = @UniqueConstraint(columnNames = {"customer_id", "role_id"})
	    )
	  private Set<Role> roles;
	
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<Document>documents;
	
	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Cart cart;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Orderr> orders;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Prescription> prescriptions; 
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<Document> getDocuments() {
		return documents;
	}
	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public List<Orderr> getOrders() {
		return orders;
	}
	public void setOrders(List<Orderr> orders) {
		this.orders = orders;
	}
	public List<Prescription> getPrescriptions() {
		return prescriptions;
	}
	public void setPrescriptions(List<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}

}
