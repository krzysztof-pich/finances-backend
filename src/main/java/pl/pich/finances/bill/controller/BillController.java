package pl.pich.finances.bill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.pich.finances.bill.model.Bill;
import pl.pich.finances.bill.service.BillService;
import pl.pich.finances.jwt.service.RegisteredUser;

import java.security.Principal;

@RequestMapping(path = "/bills")
@RestController
@CrossOrigin
public class BillController {
    @Autowired
    private BillService billService;

    @Autowired
    private RegisteredUser registeredUser;

    @PostMapping
    public Bill addBill(@RequestBody Bill bill, @AuthenticationPrincipal Principal principal) {
        bill.setUser(registeredUser.getUser());
        return billService.addBill(bill);
    }

    @PutMapping(path = {"/{id}"})
    public Bill modifyBill(@PathVariable("id") Integer id, @RequestBody Bill newBill) {
        return billService.modifyBill(registeredUser.getUser(), id, newBill);
    }

    @GetMapping(path = {"/{id}"})
    public Bill getBill(@PathVariable("id") Integer id) {
        return billService.getBill(registeredUser.getUser(), id);
    }

    @GetMapping
    public Iterable<Bill> getBills() {
        return billService.getBillsByUser(registeredUser.getUser());
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        if (billService.delete(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
