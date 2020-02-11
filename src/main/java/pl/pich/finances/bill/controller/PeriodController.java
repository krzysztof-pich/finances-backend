package pl.pich.finances.bill.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pich.finances.bill.model.Period;

import java.util.Arrays;
import java.util.stream.Stream;

@RequestMapping(path = "/bills/period")
@RestController
@CrossOrigin
public class PeriodController {

    @GetMapping
    public Stream<String> getPeriod() {
        return Arrays.stream(Period.class.getEnumConstants()).map(Period::getCode);
    }
}
