package pl.pich.finances.bill.service;

import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.pich.finances.app.exceptions.NotFoundException;
import pl.pich.finances.bill.dto.BillDto;
import pl.pich.finances.bill.model.Bill;
import pl.pich.finances.bill.repository.BillRepository;
import pl.pich.finances.user.model.User;


@Service
public class BillService {
    private final BillRepository billRepository;
    private final ModelMapper modelMapper;

    public BillService(BillRepository billRepository, ModelMapper modelMapper) {
        this.billRepository = billRepository;
        this.modelMapper = modelMapper;
    }

    public Bill addBill(BillDto billDto, User user) {
        Bill bill = modelMapper.map(billDto, Bill.class);
        bill.setUser(user);
        return this.billRepository.save(bill);
    }

    public Bill getBill(User user, Integer id) throws NotFoundException {
        return this.billRepository
                .findByUserAndId(user, id)
                    .orElseThrow(() -> new NotFoundException("Bill not found"));
    }

    public Bill modifyBill(User user, Integer id, Bill newBill) throws NotFoundException {
        Bill oldBill = this.getBill(user, id);

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

    public void delete(Integer id) throws NotFoundException {
        try {
            this.billRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Bill not found");
        }

    }
}
