/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.time.LocalDate;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author waityouc
 */

/// 

public class FFSSMTest {
    Club club;
    Site Marseille,Brest;
    Moniteur moniteur1;
    Plongee mars, Renn;
    Plongeur wass, lucille;
    Licence licenceWass, licenceLucille;
    
    
    @BeforeEach
    public void setUp(){
        club = new Club(moniteur1, "club", "0607080910");
        Marseille = new Site("Marseille", "Découvrir les fonds marins");
        Brest = new Site("Brest", "Découvrir le parc naturel et ses fonds marins incroyables");
        moniteur1 = new Moniteur("1234", "Elfarissi", "Hamza", "2 rue des Pieds", "0701010101", LocalDate.of(1985, 5, 17), 5, 7777);
        mars = new Plongee(Marseille, moniteur1, LocalDate.of(2020, 11, 29), 12, 20);
        Renn = new Plongee(Brest, moniteur1, LocalDate.of(2020, 11, 30), 20, 30);
        wass = new Plongeur("2504", "Aityoucef", "Wassim", "24 rue de la tolosae", "0660066066", LocalDate.of(1964, 1, 7), 2);
        lucille = new Plongeur("1101", "Delbos", "Lucille", "15 rue de la Soif", "0770771707", LocalDate.of(2000, 2, 13), 3);
        licenceWass = new Licence(wass, "1566129", LocalDate.of(2020, 2, 21), club);
        licenceLucille = new Licence(lucille, "5191265", LocalDate.of(2019, 7, 27), club);
    }
    
    @Test
    public void testOrganisePlongee(){
        //Ajoute la plongée à la liste 
        club.organisePlongee(mars);
        //Vérifie sa présence
        assertEquals(mars, club.plongeeOragnisees.get(0), 
                "La plongée n'a pas été ajoutée");
    }
    
    
    @Test
    public void testAjouteParticipant(){
    
        mars.ajouteParticipant(wass);
 
        assertEquals(wass, mars.listeParticipants.get(0),
                "Le plongeur n'a pas été ajouté à la plongée");
        
        
    }
    
    
    @Test
    public void testEstValide(){
        
        assertTrue(licenceWass.estValide(LocalDate.now()), "La licence n'est pas valide");
    }
    
    
    @Test
    public void testEmployeurActuel(){
       
        moniteur1.nouvelleEmbauche(club, LocalDate.of(2020, 1, 1));
        
        
        assertEquals(moniteur1.employeurActuel().get() , club, "L'employeur actuel est le club Passion Kite");
    }
    
    
    @Test
    public void testTerminerEmbauche(){
     
        moniteur1.nouvelleEmbauche(club, LocalDate.of(2020, 1, 1));

        moniteur1.embauche.terminer(LocalDate.now());
        
     
        assertTrue(moniteur1.embauche.estTerminee(), "L'embauche doit être terminée");
    }
    @Test
    public void testEstConforme(){
       
        wass.ajouteLicence("2504", LocalDate.of(2020, 2, 21), club);
        lucille.ajouteLicence("1101", LocalDate.of(2019, 7, 27), club);
        

        mars.ajouteParticipant(lucille);
        mars.ajouteParticipant(wass);
        
        
 
        assertFalse(mars.estConforme(),  "La plongée n'est pas conforme car la licence de wass ne l'est pas");
    }
    
    
    @Test
    public void testAjouteLicence(){
      
        wass.ajouteLicence("2504", LocalDate.now(), club);
    
        assertEquals(wass.licence.getDelivrance(), LocalDate.now(), "L'attribution de la licence a rencontré une erreur");
    }
    
 @Test
    public void testNouvelleEmbauche(){
        
        moniteur1.nouvelleEmbauche(club, LocalDate.of(2020, 1, 1));
        
       
        assertTrue(moniteur1.emplois().contains(moniteur1.embauche));
    }

}
