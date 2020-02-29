package com.example.assignment.dataengineer;

import com.example.assignment.dataengineer.Session.ChargingSession;
import com.example.assignment.dataengineer.Session.SessionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SessionRestController.class)
class SessionRestControllerTest {

	ObjectMapper mapper = new ObjectMapper();
	@Autowired
	private MockMvc mvc;
	private TestRestTemplate restTemplate;
	@SpyBean
	private SessionService service;


	@Test
	void GetPostTesting() throws Exception {
		ChargingSession cs = new ChargingSession(null,"Amsterdam", null, null, null);

		// ***************************** POSTING First Message*************************
		// Posting the message and testing for the status
		 mvc.perform(MockMvcRequestBuilders.post("/chargingSessions")
				.content(mapper.writeValueAsString(cs))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(201));

		 //*********************GETTING the message after posting and testing the result
		 // Getting the posted message and reading it testing the result
	MvcResult getafterpost=	mvc.perform(MockMvcRequestBuilders.get("/chargingSessions")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String expected_output= "[{\"stationId\":\"Amsterdam\",\"stoppedAt\":null,\"status\":\"INPROGRESS\"}]";
		String valueFaterPost = getafterpost.getResponse().getContentAsString();
		System.out.println(getafterpost.getResponse().getContentAsString());
		JSONAssert.assertEquals(expected_output, valueFaterPost, false);

		// ********************************GETTING the summary after first post
		// Testing the GetSummary after requesting for station
		MvcResult getSummaryafterPost=	mvc.perform(MockMvcRequestBuilders.get("/chargingSessions/summary")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String expected_summary= "{\"totalCount\":1,\"startedCount\":1,\"stoppedCount\":0}";
		System.out.println(getSummaryafterPost.getResponse().getContentAsString());
		JSONAssert.assertEquals(expected_summary, getSummaryafterPost.getResponse().getContentAsString(), false);

		//****************************Issue the PUT request for the above id which is generated***********
		// Testing the put request

		//Getting the ID for previous put
		Charging_session_String putvalue = mapper.reader().forType(Charging_session_String.class).readValue(valueFaterPost.substring(1,valueFaterPost.length()-1));
		System.out.println(putvalue.getId());
		String uri = "/chargingSessions/" + putvalue.getId();
		// performing put
			mvc.perform(MockMvcRequestBuilders.put(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON));

		//*******************************Getting the staus after put and testing it **********

		MvcResult getafterput=	mvc.perform(MockMvcRequestBuilders.get("/chargingSessions/")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		System.out.println(getafterput.getResponse().getContentAsString());

		String expectedOutputAfterPut= "[{\"stationId\":\"Amsterdam\",\"status\":\"FINISHED\"}]";
		JSONAssert.assertEquals(expectedOutputAfterPut, getafterput.getResponse().getContentAsString(), false);


		// ********************************GETTING the summary after put and testing it **********************

		MvcResult getSummaryAfterPut=	mvc.perform(MockMvcRequestBuilders.get("/chargingSessions/summary")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String expectedSummaryAfterPut = "{\"totalCount\":1,\"startedCount\":0,\"stoppedCount\":1}";

		System.out.println(getSummaryAfterPut.getResponse().getContentAsString());

		JSONAssert.assertEquals(expectedSummaryAfterPut, getSummaryAfterPut.getResponse().getContentAsString(), false);




	}


}

