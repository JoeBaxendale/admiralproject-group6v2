package admiral.web;

import admiral.service.TimeSheetCreator;
import com.nsa.cm6213.charity2019walk.domain.Charity;
import com.nsa.cm6213.charity2019walk.service.CharityFinder;
import com.nsa.cm6213.charity2019walk.service.DonationCreator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(com.nsa.cm6213.charity2019walk.web.DonorController.class)
public class MakeTimeSheet {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private TimeSheetCreator creator;

  @Test
  public void theOneWhereTheTimeSheetDetailsAreOK() throws Exception {

    mvc.perform
            (post
                    ("/TimesheetDetails")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .param("number_of_days", "3")
                    .param("overtime", "4")
                    .param("start_date", "")
                    .param("end_date", "Belfast")
                    .param("notes", "Bank holiday"))
            .andDo(
                    print()
            )
            .andExpect(
                    status().isOk()
            );
  }
}
