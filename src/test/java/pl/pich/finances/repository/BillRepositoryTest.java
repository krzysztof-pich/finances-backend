package pl.pich.finances.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pl.pich.finances.bill.model.Bill;
import pl.pich.finances.bill.repository.BillRepository;
import pl.pich.finances.user.model.User;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BillRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BillRepository billRepository;

    @Test
    public void whenFindByUserAndId_thenReturnBill() {
        User user = new User();
        user.setEmail("test@pich.pl");
        Bill phoneBill = new Bill();
        phoneBill.setName("test");
        phoneBill.setUser(user);
        phoneBill.setAmount(BigDecimal.valueOf(34));
        phoneBill.setStartDate(new GregorianCalendar(2020, Calendar.MARCH, 11).getTime());

        entityManager.persist(user);
        entityManager.persist(phoneBill);
        entityManager.flush();

        Bill found = billRepository.findByUserAndId(user, phoneBill.getId()).get();
        assertEquals(found.getName(), phoneBill.getName());
    }

}