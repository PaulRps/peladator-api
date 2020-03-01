package com.paulrps.peladator.controllers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.paulrps.peladator.domain.dto.SortTeamDto;
import com.paulrps.peladator.services.TeamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

// @RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
// @WebMvcTest(TeamController.class)
class TeamControllerIT {

  @Autowired private MockMvc mvc;
  @MockBean private TeamService service;

  @Test
  void loadPage() throws Exception {
    SortTeamDto dto = new SortTeamDto();

    given(service.loadPage()).willReturn(dto);

    mvc.perform(get("/team/load-page").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void sortTeams() {}
}
