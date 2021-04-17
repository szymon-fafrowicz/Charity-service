package pl.coderslab.charity.web.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.coderslab.charity.persistence.service.DonationService;
import pl.coderslab.charity.persistence.service.InstitutionService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HomeController.class)
public class HomeControllerMockMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InstitutionService institutionService;
    @MockBean
    private DonationService donationService;


    private final String PAGE_VIEW_NAME = "index";
    private final String MODEL_ATTRIBUTE_INSTITUTIONS = "institutions";
    private final String MODEL_ATTRIBUTE_SUM_OF_BAGS = "sumOfBags";
    private final String MODEL_ATTRIBUTE_SUM_OF_GIFTS = "sumOfGifts";

    @Test
    public void test_home_action_return_index() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(model().attributeExists(MODEL_ATTRIBUTE_SUM_OF_GIFTS))
                .andExpect(model().attributeExists(MODEL_ATTRIBUTE_INSTITUTIONS))
                .andExpect(model().attributeExists(MODEL_ATTRIBUTE_SUM_OF_BAGS))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name(PAGE_VIEW_NAME));
    }
}