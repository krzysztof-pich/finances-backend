package pl.pich.finances.bill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pich.finances.app.exceptions.NotFoundException;
import pl.pich.finances.bill.model.Bill;
import pl.pich.finances.bill.service.BillService;
import pl.pich.finances.jwt.service.RegisteredUser;

@RequestMapping(path = "/bills")
@RestController
@CrossOrigin
public class BillController {
    private final BillService billService;

    private final RegisteredUser registeredUser;

    public BillController(BillService billService, RegisteredUser registeredUser) {
        this.billService = billService;
        this.registeredUser = registeredUser;
    }

    @PostMapping
    public Bill addBill(@RequestBody Bill bill) {
        bill.setUser(registeredUser.getUser());
        return billService.addBill(bill);
    }

    @PutMapping(path = {"/{id}"})
    public Bill modifyBill(@PathVariable("id") Integer id, @RequestBody Bill newBill) throws NotFoundException {
        return billService.modifyBill(registeredUser.getUser(), id, newBill);
    }

    @GetMapping(path = {"/{id}"})
    public Bill getBill(@PathVariable("id") Integer id) throws NotFoundException {
        return billService.getBill(registeredUser.getUser(), id);
    }

    @GetMapping
    public Iterable<Bill> getBills() {
        return billService.getBillsByUser(registeredUser.getUser());
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) throws NotFoundException {
        billService.delete(id);
        return ResponseEntity.ok().build();
    }
}
