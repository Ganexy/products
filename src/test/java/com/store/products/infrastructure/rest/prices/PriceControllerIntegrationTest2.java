package com.store.products.infrastructure.rest.prices;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerIntegrationTest2 {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Sql(scripts = "/schema.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    @DisplayName("Debería retornar 200 OK y el precio aplicable")
    void shouldReturnApplicablePrice() throws Exception {

        mockMvc.perform(
                        get("/products/price")
                                .param("productId", "35455")
                                .param("brandId", "1")
                                .param("applicationDate", "2020-06-14T10:00:00")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value("35455"))
                .andExpect(jsonPath("$.brandId").value("1"))
                .andExpect(jsonPath("$.price").value("35.50"));
    }
}
