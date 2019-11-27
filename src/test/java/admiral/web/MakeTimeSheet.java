package admiral.web;

import admiral.service.TimeSheetCreator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(admiral.web.TimeSheetController.class)
public class MakeTimeSheet {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private TimeSheetCreator creator;

  @Test
  public void theOneWhereTheTimeSheetDetailsAreOK() throws Exception {

//    mvc.perform
//            (post
//                    ("/TimesheetDetails")
//                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                    .param("number_of_days", "3")
//                    .param("overtime", "4")
//                    .param("start_date", "")
//                    .param("end_date", "Belfast")
//                    .param("notes", "Bank holiday"))
//            .andDo(
//                    print()
//            )
//            .andExpect(
//                    status().isOk()
//            );
  }
}
