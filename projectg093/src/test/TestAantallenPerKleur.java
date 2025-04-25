package test;

import enums.EdelsteenType;
import exceptions.IllegalArguments;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.AantallenPerKleur;

import java.util.Arrays;

public class TestAantallenPerKleur {

    private AantallenPerKleur aantallenPerKleur;

    @BeforeEach
    public void setUp() {
        aantallenPerKleur = new AantallenPerKleur();
    }

    @Test
    public void testGetAantal() {
        aantallenPerKleur.setAantal(EdelsteenType.WIT, 3);
        Assertions.assertEquals(3, aantallenPerKleur.getAantal(EdelsteenType.WIT));
    }

    @Test
    public void testSetAantal() {
        aantallenPerKleur.setAantal(EdelsteenType.ROOD, 2);
        Assertions.assertEquals(2, aantallenPerKleur.getAantal(EdelsteenType.ROOD));
    }

    @Test
    public void testSetAantalZero() {
        aantallenPerKleur.setAantal(EdelsteenType.BLAUW, 0);
        Assertions.assertEquals(0, aantallenPerKleur.getAantal(EdelsteenType.BLAUW));
    }

    @Test
    public void testVerlaagAantal() {
        aantallenPerKleur.setAantal(EdelsteenType.GROEN, 5);
        aantallenPerKleur.verlaagAantal(EdelsteenType.GROEN, 2);
        Assertions.assertEquals(3, aantallenPerKleur.getAantal(EdelsteenType.GROEN));
    }

    @Test
    public void testVerlaagAantalNegative() {
        aantallenPerKleur.setAantal(EdelsteenType.ZWART, 1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> aantallenPerKleur.verlaagAantal(EdelsteenType.ZWART, 2));
    }

    @Test
    public void testVerhoogAantal() {
        aantallenPerKleur.verhoogAantal(EdelsteenType.WIT, 1);
        aantallenPerKleur.verhoogAantal(EdelsteenType.WIT, 1);
        Assertions.assertEquals(2, aantallenPerKleur.getAantal(EdelsteenType.WIT));
    }

    @Test
    public void testVerhoogAantalNegative() {
        Assertions.assertThrows(IllegalArguments.class, () -> aantallenPerKleur.verhoogAantal(EdelsteenType.BLAUW, -1));
    }

    @Test
    public void testKanBetalen() {
        aantallenPerKleur.setAantal(EdelsteenType.WIT, 3);
        aantallenPerKleur.setAantal(EdelsteenType.ROOD, 2);
        AantallenPerKleur kosten = new AantallenPerKleur();
        kosten.setAantal(EdelsteenType.WIT, 2);
        kosten.setAantal(EdelsteenType.ROOD, 1);
        Assertions.assertTrue(aantallenPerKleur.kanBetalen(kosten));
    }

    @Test
    public void testBonussenToAantallenPerKleur() {
        AantallenPerKleur aantallenPerKleur = AantallenPerKleur.bonussenToAantallenPerKleur(Arrays.asList(EdelsteenType.WIT, EdelsteenType.WIT, EdelsteenType.ROOD));
        Assertions.assertEquals(2, aantallenPerKleur.getAantal(EdelsteenType.WIT));
        Assertions.assertEquals(1, aantallenPerKleur.getAantal(EdelsteenType.ROOD));
    }

    @Test
    public void kanBetalen() {
        AantallenPerKleur aantallenPerKleur = new AantallenPerKleur();
        aantallenPerKleur.setAantal(EdelsteenType.WIT, 2);
        aantallenPerKleur.setAantal(EdelsteenType.ROOD, 1);
        AantallenPerKleur kosten = new AantallenPerKleur();
        kosten.setAantal(EdelsteenType.WIT, 1);
        kosten.setAantal(EdelsteenType.ROOD, 1);
        Assertions.assertTrue(aantallenPerKleur.kanBetalen(kosten));
    }

    @Test
    public void kanNietBetalen() {
        AantallenPerKleur aantallenPerKleur = new AantallenPerKleur();
        aantallenPerKleur.setAantal(EdelsteenType.WIT, 1);
        aantallenPerKleur.setAantal(EdelsteenType.ROOD, 1);
        AantallenPerKleur kosten = new AantallenPerKleur();
        kosten.setAantal(EdelsteenType.WIT, 2);
        kosten.setAantal(EdelsteenType.ROOD, 1);
        Assertions.assertFalse(aantallenPerKleur.kanBetalen(kosten));
    }

    @Test
    public void kanNetBetalen() {
        AantallenPerKleur aantallenPerKleur = new AantallenPerKleur();
        aantallenPerKleur.setAantal(EdelsteenType.WIT, 1);
        aantallenPerKleur.setAantal(EdelsteenType.ROOD, 1);
        AantallenPerKleur kosten = new AantallenPerKleur();
        kosten.setAantal(EdelsteenType.WIT, 1);
        kosten.setAantal(EdelsteenType.ROOD, 1);
        Assertions.assertTrue(aantallenPerKleur.kanBetalen(kosten));
    }

    @Test
    public void testVoegAantallenPerKleurSamen() {
        AantallenPerKleur aantallenPerKleur1 = new AantallenPerKleur();
        aantallenPerKleur1.setAantal(EdelsteenType.WIT, 2);
        AantallenPerKleur aantallenPerKleur2 = new AantallenPerKleur();
        aantallenPerKleur2.setAantal(EdelsteenType.WIT, 1);
        AantallenPerKleur aantallenPerKleur = AantallenPerKleur.voegAantallenPerKleurSamen(aantallenPerKleur1, aantallenPerKleur2);
        Assertions.assertEquals(3, aantallenPerKleur.getAantal(EdelsteenType.WIT));
    }

    @Test
    public void testVoegAantallenPerKleurSamenEmpty() {
        AantallenPerKleur aantallenPerKleur1 = new AantallenPerKleur();
        AantallenPerKleur aantallenPerKleur2 = new AantallenPerKleur();
        AantallenPerKleur aantallenPerKleur = AantallenPerKleur.voegAantallenPerKleurSamen(aantallenPerKleur1, aantallenPerKleur2);
        Assertions.assertEquals(0, aantallenPerKleur.size());
    }

    @Test
    public void testTrekAantallenPerKleurAf() {
        AantallenPerKleur aantallenPerKleur1 = new AantallenPerKleur();
        aantallenPerKleur1.setAantal(EdelsteenType.GROEN, 3);
        AantallenPerKleur aantallenPerKleur2 = new AantallenPerKleur();
        aantallenPerKleur2.setAantal(EdelsteenType.GROEN, 1);
        AantallenPerKleur aantallenPerKleur = AantallenPerKleur.trekAantallenPerKleurAf(aantallenPerKleur1, aantallenPerKleur2);
        Assertions.assertEquals(2, aantallenPerKleur.getAantal(EdelsteenType.GROEN));
    }

    @Test
    public void testTrekAantallenPerKleurAfEmpty() {
        AantallenPerKleur aantallenPerKleur1 = new AantallenPerKleur();
        AantallenPerKleur aantallenPerKleur2 = new AantallenPerKleur();
        AantallenPerKleur aantallenPerKleur = AantallenPerKleur.trekAantallenPerKleurAf(aantallenPerKleur1, aantallenPerKleur2);
        Assertions.assertEquals(0, aantallenPerKleur.size());
    }

    @Test
    public void testTrekAantallenPerKleurAfSafe() {
        AantallenPerKleur aantallenPerKleur1 = new AantallenPerKleur();
        aantallenPerKleur1.setAantal(EdelsteenType.GROEN, 3);
        AantallenPerKleur aantallenPerKleur2 = new AantallenPerKleur();
        aantallenPerKleur2.setAantal(EdelsteenType.GROEN, 1);
        AantallenPerKleur aantallenPerKleur = AantallenPerKleur.trekAantallenPerKleurAfSafe(aantallenPerKleur1, aantallenPerKleur2);
        Assertions.assertEquals(2, aantallenPerKleur.getAantal(EdelsteenType.GROEN));
    }

    @Test
    public void testTrekAantallenPerKleurAfSafeEmpty() {
        AantallenPerKleur aantallenPerKleur1 = new AantallenPerKleur();
        AantallenPerKleur aantallenPerKleur2 = new AantallenPerKleur();
        AantallenPerKleur aantallenPerKleur = AantallenPerKleur.trekAantallenPerKleurAfSafe(aantallenPerKleur1, aantallenPerKleur2);
        Assertions.assertEquals(0, aantallenPerKleur.size());
    }

    @Test
    public void testTrekAantallenPerKleurAfSafeDifferentColors() {
        AantallenPerKleur aantallenPerKleur1 = new AantallenPerKleur();
        aantallenPerKleur1.setAantal(EdelsteenType.GROEN, 3);
        AantallenPerKleur aantallenPerKleur2 = new AantallenPerKleur();
        aantallenPerKleur2.setAantal(EdelsteenType.ROOD, 1);
        AantallenPerKleur aantallenPerKleur = AantallenPerKleur.trekAantallenPerKleurAfSafe(aantallenPerKleur1, aantallenPerKleur2);
        Assertions.assertEquals(3, aantallenPerKleur.getAantal(EdelsteenType.GROEN));
        Assertions.assertEquals(0, aantallenPerKleur.getAantal(EdelsteenType.ROOD));
    }
}