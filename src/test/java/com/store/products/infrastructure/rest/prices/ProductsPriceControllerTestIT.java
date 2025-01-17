package com.store.products.infrastructure.rest.prices;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@TestPropertySource("classpath:application-it.properties")
class ProductsPriceControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    static Stream<Arguments> provideTestCases() {
        return Stream.of(
                Arguments.of("35455", "1", "2020-06-14T10:00:00", "35.50"),
                Arguments.of("35455", "1", "2020-06-14T16:00:00", "25.45"),
                Arguments.of("35455", "1", "2020-06-14T21:00:00", "35.50"),
                Arguments.of("35455", "1", "2020-06-15T10:00:00", "30.50"),
                Arguments.of("35455", "1", "2020-06-16T21:00:00", "38.95")
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void shouldReturnCorrectPrice(String productId, String brandId, LocalDateTime applicationDate, String expectedPrice) throws Exception {

        mockMvc.perform(
                        get("/products/price")
                                .param("productId", productId)
                                .param("brandId", brandId)
                                .param("applicationDate", applicationDate.toString())
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.brandId").value(brandId))
                .andExpect(jsonPath("$.price").value(expectedPrice));
    }

}