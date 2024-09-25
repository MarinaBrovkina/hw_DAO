package org.example.repository;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class ProductRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private static final String PRODUCT_NAME_BY_NAME_SCRIPT = "product_name_by_name.sql";

    public ProductRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getProductName(String name) {
        String sql = read();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", name);
        List<String> productNames = jdbcTemplate.queryForList(sql, params, String.class);
        return productNames.isEmpty() ? null : productNames.get(0);
    }

    private static String read() {
        try (InputStream is = new ClassPathResource(ProductRepository.PRODUCT_NAME_BY_NAME_SCRIPT).getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
