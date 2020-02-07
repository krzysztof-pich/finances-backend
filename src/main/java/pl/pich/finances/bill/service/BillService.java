package pl.pich.finances.bill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pich.finances.bill.model.Bill;
import pl.pich.finances.bill.repository.BillRepository;

@Service
public class BillService {

    @Autowired
    private final BillRepository billRepository;

    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public Bill addBill(Bill bill) {
        return this.billRepository.save(bill);
    }

    public Iterable<Bill> getBillList() {
        return this.billRepository.findAll();
    }
}
