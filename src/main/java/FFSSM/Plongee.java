/**
 * @(#) Plongee.java
 */
package FFSSM;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class Plongee {

	public Site lieu;

	public DiplomeDeMoniteur chefDePalanquee;

	public LocalDate date;

	public int profondeur;

	public int duree;

	@Getter
	public List<Licence> participants;


	public Plongee(Site lieu, DiplomeDeMoniteur chefDePalanquee, LocalDate date, int profondeur, int duree) {
		this.lieu = lieu;
		this.chefDePalanquee = chefDePalanquee;
		this.date = date;
		this.profondeur = profondeur;
		this.duree = duree;
		this.participants = new ArrayList<>();
	}

	public void ajouteParticipant(Plongeur participant) {
		if (participant == null) {
			throw new IllegalArgumentException("Le participant n'existe pas.");
		} 
		if (!participants.contains(participant.derniereLicence())) {
			participants.add(participant.derniereLicence());
		}
	}

	/**
	 * Détermine si la plongée est conforme. 
	 * Une plongée est conforme si tous les plongeurs de la palanquée ont une
	 * licence valide à la date de la plongée
	 * @return vrai si la plongée est conforme
	 */
	public boolean estConforme() {
		if (participants.isEmpty()) {
			return true;
		}
		for (Licence l : participants) {
			if (l == null || !l.estValide(this.date)) {
				return false;
			}
		}
		return true;
	}

}
