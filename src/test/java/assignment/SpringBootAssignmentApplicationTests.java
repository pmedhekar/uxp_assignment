package assignment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.uxpsystems.assignment.SpringBootAssignmentApplication;
import com.uxpsystems.assignment.dao.UserProfile;
import com.uxpsystems.assignment.dao.UserProfileRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = SpringBootAssignmentApplication.class)// Package structure
@AutoConfigureMockMvc
public class SpringBootAssignmentApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private UserProfileRepository userProfileRepository;
	
	
	@Test // POST
	public void createUserProfileTest() throws Exception 
	{
		mockMvc.perform( MockMvcRequestBuilders
	      .post("/profile")
	      .contentType(MediaType.APPLICATION_JSON)
	      .content("{\"firstName\":\"Jack\",\"lastName\":\"Ma\",\"address\":\"Beijing, China\",\"phone\":\"97747655\"}")
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test // GET
	public void getUserProfileTest () throws Exception{
		UserProfile user = new UserProfile(1, "prathamesh", "medhekar", "Cyprus", 1234);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/profile/{id}", 1)
			      .contentType(MediaType.APPLICATION_JSON))
				  .andDo(MockMvcResultHandlers.print())
			      .andExpect(MockMvcResultMatchers.status().isOk())
			      .andExpect(MockMvcResultMatchers.content()
			      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}
	
	@Test //PUT
	public void updateUserProfileSuccessTest() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.put("/profile/{id}", 1)
				.content("{\"address\":\"Rome, Italy\"}")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.address").value("Rome, Italy"));
	}
}