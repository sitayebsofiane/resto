package fr.opendevup.entities;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
	@Id
	@Column(name = "role")
	private String role;
	
	
	
}
