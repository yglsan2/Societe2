
package test;

import com.benja2.entites.Client;
import com.benja2.entites.Adresse;
import com.benja2.utilitaires.PersoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour la classe Client.
 */
class ClientTest {

    private Client client;
    private Adresse adresse;

    /**
     * Configuration préalable pour initialiser les objets nécessaires aux tests.
     */
    @BeforeEach
    void setUp() throws PersoException {
        adresse = new Adresse("123", "Rue Exemple", "75008", "Paris");
        // Initialisation d'un client avec des valeurs par défaut
        client = new Client("Société Test", adresse, "0123456789", "contact@societe.fr", 5000L, 50, "Commentaire");
    }

    /**
     * Test de la création d'un client avec des données valides.
     */
    @Test
    void testClientCreation() {
        assertDoesNotThrow(() -> {
            client = new Client("Société Test", adresse, "0123456789", "contact@societe.fr", 5000L, 50, "Commentaire");
        });
        assertEquals("Société Test", client.getRaisonSociale());
        assertEquals("0123456789", client.getTelephone());
        assertEquals("contact@societe.fr", client.getEmail());
        assertEquals(50, client.getNombreEmployes());
        assertEquals("Commentaire", client.getCommentaire());
    }

    /**
     * Test de la mise à jour des informations du client.
     */
    @Test
    void testUpdateClientInfo() {
        assertDoesNotThrow(() -> {
            client.setTelephone("0987654321");
            client.setEmail("nouveaucontact@societe.fr");
        });
        assertEquals("0987654321", client.getTelephone());
        assertEquals("nouveaucontact@societe.fr", client.getEmail());
    }

    /**
     * Test de la mise à jour du nombre d'employés.
     */
    @Test
    void testSetNombreEmployes() {
        assertDoesNotThrow(() -> client.setNombreEmployes(100));
        assertEquals(100, client.getNombreEmployes());
    }

    /**
     * Test de la tentative de définition d'un nombre d'employés négatif.
     */
    @Test
    void testSetNombreEmployesNegatif() {
        assertThrows(PersoException.class, () -> new Client("Société Test", adresse, "0123456789", "contact@societe.fr", 5000L, -10, "Commentaire"));
    }

    /**
     * Test de la création d'un client avec un email invalide.
     */
    @Test
    void testClientCreationWithInvalidEmail() {
        assertThrows(PersoException.class, () -> new Client("Société Test", adresse, "0123456789", "mauvaisemail.com", 5000L, 50, "Commentaire"));
    }

    /**
     * Test de la création d'un client avec un numéro de téléphone invalide.
     */
    @Test
    void testClientCreationWithInvalidTelephone() {
        assertThrows(PersoException.class, () -> new Client("Société Test", adresse, "01234", "contact@societe.fr", 5000L, 50, "Commentaire"));
    }

    /**
     * Test de la méthode getClientInfo.
     */
    @Test
    void testGetClientInfo() {
        String expectedInfo = "Client [Raison Sociale: Société Test, Téléphone: 0123456789, Email: contact@societe.fr, Nombre d'employés: 50]";
        assertEquals(expectedInfo, client.getClientInfo());
    }

    /**
     * Test de la méthode getClientInfo avec un nombre d'employés mis à jour.
     */
    @Test
    void testGetClientInfoWithUpdatedEmployes() throws PersoException {
        client.setNombreEmployes(100);
        String expectedInfo = "Client [Raison Sociale: Société Test, Téléphone: 0123456789, Email: contact@societe.fr, Nombre d'employés: 100]";
        assertEquals(expectedInfo, client.getClientInfo());
    }

    /**
     * Test de la création d'un client avec une raison sociale vide.
     */
    @Test
    void testClientCreationWithEmptyRaisonSociale() {
        assertThrows(PersoException.class, () -> new Client("", adresse, "0123456789", "contact@societe.fr", 5000L, 50, "Commentaire"));
    }

    /**
     * Test de la création d'un client avec une adresse nulle.
     */
    @Test
    void testClientCreationWithNullAdresse() {
        assertThrows(PersoException.class, () -> new Client("Société Test", null, "0123456789", "contact@societe.fr", 5000L, 50, "Commentaire"));
    }
}
