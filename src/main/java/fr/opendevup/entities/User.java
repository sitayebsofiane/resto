package fr.opendevup.entities;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;



@Entity
@Table(name = "users")
public class User
{
	@Id
	@Column(name = "login")
	private String login;
	
	@Column(name = "pass")
	private String pass;
	
	@Column(name = "active")
	private int active;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles",joinColumns= @JoinColumn(name = "login"),inverseJoinColumns =@JoinColumn(name = "role"))
	private Set<Role> roles;	
}
