package cz.czechitas.ukol06.svatky;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class SvatkySluzba {

    private final ObjectMapper objectMapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();
    private final Path cestaKDatum = Path.of("data/svatky.json");
    private final SeznamSvatku seznamSvatku;

    public SvatkySluzba() throws IOException {
        //načíst seznam svátků ze souboru svatky.json
        String jsonData = Files.readString(cestaKDatum);
        seznamSvatku = objectMapper.readValue(cestaKDatum.toFile(), SeznamSvatku.class);
    }

    public List<String> vyhledatSvatkyDnes() {
        return vyhledatSvatkyKeDni(MonthDay.now());
    }

    public List<String> vyhledatSvatkyKeDni(MonthDay day) {

        // získat seznam svátků

        List<Svatek> svatekSeznam = seznamSvatku.getSvatky();

        return svatekSeznam
                .stream() //převést na Stream
                .filter(svatek -> svatek.getDen().equals(day)) // pomocí metody filter() vybrat jen ty, které odpovídají zadanému dni (porovnat MonthDay pomocí metodyequals())
                .map(Svatek::getJmeno) // pomocí metody map() získat z objektu jméno
                .collect(Collectors.toList()); // pomocí toList() převést na List
    }

    public List<MonthDay> seznamDniBezSvatku() {
        List<MonthDay> dniBezSvatku = new ArrayList<>();

        // Získat seznam všech dní
        List<Svatek> seznamDnuBezSvatku = seznamSvatku.getSvatky();

        // Projít všechny dny a zkontrolovat, zda mají svátek
        for (Svatek svatek : seznamDnuBezSvatku) {
            MonthDay day = svatek.getDen();
            if (vyhledatSvatkyKeDni(day).isEmpty()) {
                dniBezSvatku.add(day);
            }
        }

        return dniBezSvatku;
    }


}

