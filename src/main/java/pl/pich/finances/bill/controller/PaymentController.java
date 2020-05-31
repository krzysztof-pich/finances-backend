package pl.pich.finances.bill.controller;

import org.springframework.web.bind.annotation.*;
import pl.pich.finances.app.exceptions.NotFoundException;
import pl.pich.finances.bill.dto.BillPaymentDto;
import pl.pich.finances.bill.service.BillPaymentService;
import pl.pich.finances.jwt.service.RegisteredUser;

@RequestMapping(path = "/bills/{billId}/payments")
@RestController
@CrossOrigin
public class PaymentController {
    private final RegisteredUser registeredUser;
    private final BillPaymentService billPaymentService;

    public PaymentController(RegisteredUser registeredUser, BillPaymentService billPaymentService) {
        this.registeredUser = registeredUser;
        this.billPaymentService = billPaymentService;
    }

    @PostMapping
    public BillPaymentDto addPayment(@PathVariable("billId") Integer billId, @RequestBody BillPaymentDto newPayment) throws NotFoundException {
        return newPayment;
    }
}
