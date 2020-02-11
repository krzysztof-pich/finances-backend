package pl.pich.finances.bill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.pich.finances.bill.model.Bill;
import pl.pich.finances.bill.service.BillService;
import pl.pich.finances.jwt.model.ExtendedUserDetails;

import java.security.Principal;

@RequestMapping(path = "/bills")
@RestController
@CrossOrigin
public class BillController {
    @Autowired
    private BillService billService;

    @PostMapping
    public Bill addBill(@RequestBody Bill bill, @AuthenticationPrincipal Principal principal) {
        ExtendedUserDetails userDetails =
                (ExtendedUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        bill.setUser(userDetails.getDbUser());
        return billService.addBill(bill);
    }

    @GetMapping
    public Iterable<Bill> getBills() {
        ExtendedUserDetails userDetails =
                (ExtendedUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return billService.getBillsByUser(userDetails.getDbUser());
    }
}
