package pl.pich.finances.bill.repository;

import org.springframework.data.repository.CrudRepository;
import pl.pich.finances.bill.model.Bill;

public interface BillRepository extends CrudRepository<Bill, Integer> {
}
