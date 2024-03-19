package cz.czechitas.ukol06.svatky;

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

        List<String> aktualniSvatek = svatkySluzba.vyhledatSvatkyKeDni(testDate);
        assertEquals(ocekavanySvatek, aktualniSvatek);

    }
}