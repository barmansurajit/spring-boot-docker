package com.triton;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class DockControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenContainers_whenSearched_returnListOfContainers() throws Exception {
        this.mockMvc.perform(get("/api/containers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].image", is("alpine")))
                .andExpect(jsonPath("$[0].status", is("EXITED")))
                .andExpect(jsonPath("$[1].image", is("ubuntu")))
                .andExpect(jsonPath("$[1].status", is("UP")))
                .andDo(document("containers", responseFields(
                        fieldWithPath("[].image").description("The name of the container"),
                        fieldWithPath("[].status").description("The status of the container"))));
    }
}
