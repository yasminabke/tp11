/**
 * @(#) Personne.java
 */

package FFSSM;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

public abstract class Personne{

	@Getter @Setter
	public String numeroINSEE;
	
	@Getter @Setter
	public String nom;
	
	@Getter @Setter
	public String prenom;
	
	@Getter @Setter
	public String adresse;
	
	@Getter @Setter
	public String telephone;
	
	@Getter @Setter
	public LocalDate naissance;

	public Personne(String nom){
		this.nom = nom;
	}

    public Personne(String nom, String prenom, String numeroINSEE, String adresse, String telephone, LocalDate naissance) {
        this.nom = nom;
		this.prenom = prenom;
		this.numeroINSEE = numeroINSEE;
		this.adresse = adresse;
		this.telephone = telephone;
		this.naissance = naissance;
    }



}
