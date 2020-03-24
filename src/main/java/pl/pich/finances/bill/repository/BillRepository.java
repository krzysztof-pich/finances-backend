package pl.pich.finances.bill.repository;

import org.springframework.data.repository.CrudRepository;
import pl.pich.finances.bill.model.Bill;
import pl.pich.finances.user.model.User;
import java.util.List;
import java.util.Optional;

public interface BillRepository extends CrudRepository<Bill, Integer> {
    public List<Bill> findByUser(User user);
    public Optional<Bill> findByUserAndId(User user, Integer id);
}
