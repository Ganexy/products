package com.store.products.infrastructure.rest.prices;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;
import java.util.stream.Stream;

@Sql(scripts = "/schema.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource("classpath:application-it.properties")
public class ProductsPriceControllerTestIT {

    @Autowired
    private MockMvc mockMvc;


    @ParameterizedTest
    @MethodSource("provideTestCases")
    void shouldReturnCorrectPrice(String productId, String brandId, LocalDateTime applicationDate, int expectedStatus, String expectedPrice) throws Exception {

        String url = String.format("/products/price?productId=%s&brandId=%s&applicationDate=%s", productId, brandId, applicationDate);

        mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(expectedStatus))
                .andExpect(expectedPrice != null
                        ? jsonPath("$.price").value(expectedPrice)
                        : jsonPath("$.price").doesNotExist())
                .andDo(print());
    }

    static Stream<Arguments> provideTestCases() {
        return Stream.of(
                Arguments.of("35455", "1", "2020-06-14T10:00:00", 200 , "35.50"),
                Arguments.of("35455", "1", "2020-06-14T16:00:00", 200 , "35.50"),
                Arguments.of("35455", "1", "2020-06-14T21:00:00", 200 , "35.50"),
                Arguments.of("35455", "1", "2020-06-15T10:00:00", 200 , "35.50"),
                Arguments.of("35455", "1", "2020-06-16T21:00:00", 200 , "35.50")
        );
    }

}