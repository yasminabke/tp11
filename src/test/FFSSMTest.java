package FFSSM;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class FFSSMTest {

    Plongeur plongeur1;
    Plongeur plongeur2;
    DiplomeDeMoniteur moniteur;
    Club club;
    Site site;
    Plongee plongee;
    Licence licenceValide;
    Licence licenceExpiree;

    @BeforeEach
    void setUp() {
        plongeur1 = new Plongeur("Alice", 2, GroupeSanguin.APLUS);
        plongeur2 = new Plongeur("Bob", 1, GroupeSanguin.OMOINS);

        moniteur = new DiplomeDeMoniteur(plongeur1, 123);

        club = new Club(moniteur, "ClubTest");
        site = new Site("SiteTest", "Test details");

        LocalDate aujourdHui = LocalDate.now();
        licenceValide = new Licence(plongeur1, "L1", aujourdHui.minusMonths(6), club);
        licenceExpiree = new Licence(plongeur2, "L2", aujourdHui.minusYears(2), club);

        plongeur1.ajouteLicence(licenceValide);
        plongeur2.ajouteLicence(licenceExpiree);

        plongee = new Plongee(site, moniteur, aujourdHui, 20, 30);
    }

    @Test
    void testLicenceValide() {
        assertTrue(licenceValide.estValide(LocalDate.now()));
        assertFalse(licenceExpiree.estValide(LocalDate.now()));
    }

    @Test 
    void testDerniereLicence() {
        assertEquals(licenceValide, plongeur1.derniereLicence());
        assertEquals(licenceExpiree, plongeur2.derniereLicence());
    }

    @Test
    void testAjouteParticipant() {
        plongee.ajouteParticipant(plongeur1);
        assertEquals(1, plongee.getParticipants().size());
        assertEquals(licenceValide, plongee.getParticipants().get(0));
    }

    @Test
    void testEstConforme() {
        plongee.ajouteParticipant(plongeur1);
        assertTrue(plongee.estConforme());

        plongee.ajouteParticipant(plongeur2);
        assertFalse(plongee.estConforme());
    }

    @Test
    void testEmployeurActuelEtEmbauche() {
        assertNull(moniteur.employeurActuel());
        moniteur.nouvelleEmbauche(club, LocalDate.now().minusDays(10));
        assertEquals(club, moniteur.employeurActuel());

        moniteur.terminerEmbauche(LocalDate.now());
        assertNull(moniteur.employeurActuel());
    }

    @Test
    void testOrganisePlongee() {
        plongee.ajouteParticipant(plongeur1);
        club.organisePlongee(plongee);

        // On vérifie que la plongée a bien été ajoutée au club
        assertTrue(club.getPlongees().contains(plongee));
    }

    @Test
    void testPlongeesNonConformes() {
        // Plongée conforme
        plongee.ajouteParticipant(plongeur1);
        club.organisePlongee(plongee);
        Set<Plongee> nonConformes = club.plongeesNonConformes();
        assertTrue(nonConformes.isEmpty());

        // Plongée non conforme
        Plongee plongee2 = new Plongee(site, moniteur, LocalDate.now(), 10, 20);
        plongee2.ajouteParticipant(plongeur2);
        club.organisePlongee(plongee2);

        nonConformes = club.plongeesNonConformes();
        assertEquals(1, nonConformes.size());
        assertTrue(nonConformes.contains(plongee2));
    }


}
