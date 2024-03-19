package cz.czechitas.ukol06.svatky;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.MonthDay;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SvatkySluzba {

    private final ObjectMapper objectMapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();
    private final Path cestaKDatum = Path.of("data/svatky.json");
    private final SeznamSvatku seznamSvatku;

    public SvatkySluzba() throws IOException {
        //načíst seznam svátků ze souboru svatky.json
        String jsonData = Files.readString(cestaKDatum);
        seznamSvatku = objectMapper.readValue(jsonData, new TypeReference<SeznamSvatku>() {
        });
    }

    public List<String> vyhledatSvatkyDnes() {
        return vyhledatSvatkyKeDni(MonthDay.now());
    }

    public List<String> vyhledatSvatkyKeDni(MonthDay day) {

        // získat seznam svátků
        List<Svatek> svatekSeznam = seznamSvatku.getSvatky();

        // převést na Stream
        Stream<Svatek> svatekStream = svatekSeznam.stream();

        // pomocí metody filter() vybrat jen ty, které odpovídají zadanému dni (porovnat MonthDay pomocí metodyequals())
        Stream<Svatek> filteredStream = svatekStream.filter(svatek -> svatek.getDen().equals(day));

        // pomocí metody map() získat z objektu jméno
        Stream<String> jmenaStream = filteredStream.map(Svatek::getJmeno);

        // pomocí toList() převést na List
        List<String> jmenaSeznam = jmenaStream.collect(Collectors.toList());

        return jmenaSeznam;

        //return svatekList.stream().filter(svatek -> svatek.getDen().equals(day)).map(Svatek::getJmeno).collect(Collectors.toList());
    }
}
