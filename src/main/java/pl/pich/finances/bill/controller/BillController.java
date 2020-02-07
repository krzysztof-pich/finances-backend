package pl.pich.finances.bill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.pich.finances.bill.model.Bill;
import pl.pich.finances.bill.service.BillService;

@RequestMapping(path = "/bills")
@RestController
@CrossOrigin
public class BillController {
    @Autowired
    private BillService billService;

    @PostMapping
    public Bill addBill(@RequestBody Bill bill) {
        return billService.addBill(bill);
    }

    @GetMapping
    public Iterable<Bill> getBills() {
        return billService.getBillList();
    }
}
