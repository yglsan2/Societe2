package test;

import com.benja2.entites.Adresse;
import com.benja2.entites.Prospect;
import com.benja2.utilitaires.PersoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test unitaire pour la classe Prospect.
 * Les tests valident le bon fonctionnement des méthodes et des règles de validation spécifiques aux prospects.
 */
public class ProspectTest {

    private Prospect prospect;

    @BeforeEach
    void setUp() throws PersoException {
        // Initialisation d'une instance Prospect avec des valeurs valides.
        prospect = new Prospect(
                "Raison Sociale",
                new Adresse("123", "Rue de Paris", "75000", "Paris"),
                "0123456789",
                "test@example.com",
                "Commentaire",
                "01/01/2023",
                true
        );
    }

    @Test
    void testConstructeurComplet() throws PersoException {
        // Test du constructeur complet pour vérifier que tous les champs sont correctement initialisés.
        assertEquals("Raison Sociale", prospect.getRaisonSociale());
        assertEquals("0123456789", prospect.getTelephone());
        assertEquals("test@example.com", prospect.getEmail());
        assertEquals("Commentaire", prospect.getCommentaire());
        assertNotNull(prospect.getAdresse());
        assertEquals(LocalDate.of(2023, 1, 1), prospect.getDateProspection());
        assertTrue(prospect.getInteresse());
    }

    @Test
    void testSetDateProspectionValide() throws PersoException {
        // Test de l'attribution d'une date de prospection valide.
        assertDoesNotThrow(() -> prospect.setDateProspection("15/08/2023"));
        assertEquals(LocalDate.of(2023, 8, 15), prospect.getDateProspection());
    }

    @Test
    void testSetDateProspectionInvalide() {
        // Test de l'attribution d'une date de prospection invalide.
        PersoException exception = assertThrows(PersoException.class, () -> prospect.setDateProspection("invalid-date"));
        assertEquals("Format de date invalide. Le format attendu est dd/MM/yyyy.", exception.getMessage());
    }

    @Test
    void testSetInteresseValide() throws PersoException {
        // Test de l'attribution d'un état d'intérêt valide.
        assertDoesNotThrow(() -> prospect.setInteresse(false));
        assertFalse(prospect.getInteresse());
    }

    @Test
    void testGetIdProspect() {
        // Test pour vérifier que l'ID prospect est correctement incrémenté.
        assertTrue(prospect.getId() > 0);
    }
    @Test

    void testToString() {

        // Test de la méthode toString pour vérifier la représentation en chaîne du prospect.

        String expected = "Prospect{" +

                "id=" + prospect.getId() +

                ", raisonSociale='Raison Sociale', " +

                "adresse=Adresse{numeroRue='123', nomRue='Rue de Paris', codePostal='75000', ville='Paris'}, " +

                "telephone='0123456789', email='test@example.com', " +

                "commentaire='Commentaire', dateProspection=" + prospect.getDateProspection() +

                ", interesse=OUI}";


        assertEquals(expected, prospect.toString());

    }

}