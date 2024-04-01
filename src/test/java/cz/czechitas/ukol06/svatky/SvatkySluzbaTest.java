package cz.czechitas.ukol06.svatky;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Month;
import java.time.MonthDay;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SvatkySluzbaTest {

    @Test
    void vyhledatSvatkyKeDni() throws IOException {
        //implementovat test metody vyhledatSvatkyKeDni

        //Arrange
        SvatkySluzba svatkySluzba = new SvatkySluzba();

        MonthDay testDate = MonthDay.of(Month.JANUARY, 3);
        List<String> ocekavanySvatek = List.of("Radmila", "Jenovefa", "Radomil");

        List<String> svatekKeDni = svatkySluzba.vyhledatSvatkyKeDni(testDate);
        assertEquals(ocekavanySvatek, svatekKeDni);
    }

    @Test
    void vyhledatSvatkyDnes() throws IOException {

        // Arrange
        SvatkySluzba svatkySluzba = new SvatkySluzba();
        List<String> ocekavanySvatek = svatkySluzba.vyhledatSvatkyKeDni(MonthDay.now());

        // Act
        List<String> svatekKeDni = svatkySluzba.vyhledatSvatkyDnes();

        // Assert
        assertEquals(ocekavanySvatek, svatekKeDni);
    }

    @Test
    void vyhledatSvatkyJednoJmeno() throws IOException {
        // Arrange
        SvatkySluzba svatkySluzba = new SvatkySluzba();
        MonthDay testDate = MonthDay.of(Month.DECEMBER, 31);
        List<String> expectedSvatek = List.of("Silvestr");

        // Act
        List<String> actualSvatek = svatkySluzba.vyhledatSvatkyKeDni(testDate);

        // Assert
        assertEquals(expectedSvatek, actualSvatek);
    }

    @Test
    void vyhledatSvatkyViceJmen() throws IOException {
        // Arrange
        SvatkySluzba svatkySluzba = new SvatkySluzba();
        MonthDay testDate = MonthDay.of(Month.DECEMBER, 24);
       int ocekavanyPocetJmen = 4;

        // Act
        List<String> actualSvatek = svatkySluzba.vyhledatSvatkyKeDni(testDate);

        // Assert
        assertEquals(ocekavanyPocetJmen, actualSvatek.size());
    }

    @Test
    void vyhledatSvatkyZadneJmeno() throws IOException {
        // Arrange
        SvatkySluzba svatkySluzba = new SvatkySluzba();
        MonthDay testDate = MonthDay.of(Month.DECEMBER, 24);
        int ocekavanyPocetJmen = 0;
        // Act
        List<String> actualSvatek = svatkySluzba.vyhledatSvatkyKeDni(testDate);
        // Assert
        assertNotEquals(ocekavanyPocetJmen, actualSvatek.size());
    }


}
