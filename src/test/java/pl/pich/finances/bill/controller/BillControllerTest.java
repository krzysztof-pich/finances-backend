package pl.pich.finances.bill.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import pl.pich.finances.bill.model.Bill;
import pl.pich.finances.bill.model.Period;
import pl.pich.finances.bill.service.BillService;
import pl.pich.finances.jwt.service.RegisteredUser;
import pl.pich.finances.user.model.User;
import security.SpringSecurityWebAuxTestConfig;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = SpringSecurityWebAuxTestConfig.class
)
@AutoConfigureMockMvc
public class BillControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private BillService billService;

    @MockBean
    private RegisteredUser registeredUser;

    private Bill testBill;
    private User testUser;



    @Before
    public void prepareTestData() {
        testUser =  new User();
        testUser.setEmail("test@pich.pl");
        testUser.setPassword("test");
        testUser.setId(3);

        testBill = new Bill();
        testBill.setId(4);
        testBill.setAmount(BigDecimal.valueOf(39));
        testBill.setIntervalModulo(1);
        testBill.setPeriod(Period.fromCode("monthly"));
        testBill.setStartDate(new GregorianCalendar(2020, Calendar.MARCH, 11).getTime());
        testBill.setTimeOfPayment(14);
        testBill.setUser(testUser);
        testBill.setName("test");
    }

    @Test
    @WithUserDetails("test@pich.pl")
    public void givenBills_whenGetBills_thenReturnJsonArray() throws Exception {
        List<Bill> allBills = Arrays.asList(testBill);
        given(billService.getBillsByUser(testUser)).willReturn(allBills);
        given(registeredUser.getUser()).willReturn(testUser);

        mvc.perform(get("/bills")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(testBill.getId())))
                .andExpect(jsonPath("$[0].name", is(testBill.getName())))
                .andExpect(jsonPath("$[0].timeOfPayment", is(testBill.getTimeOfPayment())))
                .andExpect(jsonPath("$[0].period", is("monthly")))
                .andExpect(jsonPath("$[0].intervalModulo", is(testBill.getIntervalModulo())))
                .andExpect(jsonPath("$[0].startDate", is("2020-03-10")))
                .andExpect(jsonPath("$[0].endDate", nullValue()))
                .andExpect(jsonPath("$[0].amount", is(39)))
                .andExpect(jsonPath("$[0].user").doesNotExist())
            ;
    }

    @Test
    @WithUserDetails("test@pich.pl")
    public void givenIncorrectBillId_whenGetBill_thenThrowError() throws Exception {
        List<Bill> allBills = Arrays.asList(testBill);
        given(billService.getBillsByUser(testUser)).willReturn(allBills);
        given(registeredUser.getUser()).willReturn(testUser);

        mvc.perform(get("/bills/4")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.errorMessage", is("Bill not found")))
            .andExpect(jsonPath("$.details", hasSize(0)))
        ;
    }
}