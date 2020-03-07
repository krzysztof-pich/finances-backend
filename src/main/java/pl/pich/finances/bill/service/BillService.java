package pl.pich.finances.bill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.pich.finances.bill.model.Bill;
import pl.pich.finances.bill.repository.BillRepository;
import pl.pich.finances.user.model.User;

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

    public Iterable<Bill> getBillsByUser(User user) {
        return this.billRepository.findByUser(user);
    }

    public boolean delete(Integer id) {
        try {
            this.billRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }

    }
}
