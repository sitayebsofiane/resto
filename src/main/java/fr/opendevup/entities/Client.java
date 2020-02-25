package fr.opendevup.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idClient;
	@NotNull
	@Size(min = 2, max = 20)
	private String nom;
	@NotNull
	@Size(min = 2, max = 30)
	private String prenom;
	@Email
	private String email;
	@NotNull
	@Size(min = 5)
	private String motDePasse;
	@NotNull
	@Size(min = 5)
	private String adresse;
	@Size(min = 10, max = 17)
	private String telephone;
	
	public Client() {
		super();
	}

	public Client(String nom, String prenom, String email, String adresse, String telephone, String motDePasse) {
		this.telephone = telephone;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.adresse = adresse;
		this.motDePasse = motDePasse;
		
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int id) {
		this.idClient = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		String hash = "";
		for (int i = 0; i < motDePasse.length(); i++) {
			hash += motDePasse.charAt(i)+this.getEmail().charAt(i) ; 
		}
		this.motDePasse =hash;
	}

	@Override
	public String toString() {
		return "Client [idClient=" + idClient + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email
				+ ", motDePasse=" + motDePasse + ", adresse=" + adresse + ", telephone=" + telephone + "]";
	}


	

}
