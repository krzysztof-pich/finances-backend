package pl.pich.finances.bill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import pl.pich.finances.app.exceptions.NotFoundException;
import pl.pich.finances.bill.model.Bill;
import pl.pich.finances.bill.repository.BillRepository;
import pl.pich.finances.user.model.User;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

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

    public Bill getBill(User user, Integer id) throws NotFoundException {
        return this.billRepository
                .findByUserAndId(user, id)
                    .orElseThrow(() -> new NotFoundException("Bill not found"));
    }

    public Bill modifyBill(User user, Integer id, Bill newBill) {
        Bill oldBill = this.billRepository.findByUserAndId(user, id).orElseThrow();

        oldBill.setName(newBill.getName());
        oldBill.setTimeOfPayment(newBill.getTimeOfPayment());
        oldBill.setPeriod(newBill.getPeriod());
        oldBill.setIntervalModulo(newBill.getIntervalModulo());
        oldBill.setStartDate(newBill.getStartDate());
        oldBill.setEndDate(newBill.getEndDate());
        oldBill.setAmount(newBill.getAmount());

        return this.billRepository.save(oldBill);
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
