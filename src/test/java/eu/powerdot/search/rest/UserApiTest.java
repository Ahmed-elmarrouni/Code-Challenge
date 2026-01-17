package eu.powerdot.search.rest;

import eu.powerdot.search.AbstractIntegrationTest;
import eu.powerdot.search.data.entity.AddressDbo;
import eu.powerdot.search.data.entity.UserDbo;
import eu.powerdot.search.data.repository.AddressRepository;
import eu.powerdot.search.data.repository.UserRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

class UserApiTest extends AbstractIntegrationTest {

    MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;

    @BeforeEach
    void beforeEach() throws IOException {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        addressRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void shouldSearchUserSuccessfully() throws Exception {

        createUser("John", 18);
        createUser("Jane", 18);

        mvc.perform(MockMvcRequestBuilders.get("/users")).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(Matchers.equalTo("John")))
                .andReturn();

    }

    @Test
    void shouldUpadtrdUsersAgeSuccessfully() throws Exception {

        createUser("John", 18);
        createUser("Jane", 18);

        mvc.perform(MockMvcRequestBuilders.post("/users/activate").content("[\"John\",\"Jane\"]").header("content-type",
                MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].active").value(true))
                .andReturn();

    }

    private void createUser(String name, int age) {
        AddressDbo addressDbo = new AddressDbo();
        addressDbo.setStreet("Street " + name);
        UserDbo user = new UserDbo();
        user.setName(name);
        user.setAge(age);
        userRepository.save(user);
        addressDbo.setUser(user);
        addressRepository.save(addressDbo);
    }

}
