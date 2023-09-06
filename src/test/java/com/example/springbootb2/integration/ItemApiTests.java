package com.example.springbootb2.integration;

import com.example.springbootb2.dto.ItemDto;
import com.example.springbootb2.security.JwtService;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.Collections;

import static com.example.springbootb2.util.TestUtil.createItemDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
public class ItemApiTests {
    @Value("${test.url}")
    private String url;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private JwtService jwtService;

    @Test
    public void itShouldCreateItem() {
        var itemDtoRequest = createItemDto();

        var response = testRestTemplate
                .postForEntity("/items", itemDtoRequest, ItemDto.class);
        var itemDtoResponse = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getHeaders().getContentType()).isEqualTo(APPLICATION_JSON);
        assertThat(itemDtoResponse).isNotNull();
        assertThat(itemDtoResponse.getName()).isEqualTo(itemDtoRequest.getName());
    }

    @Test
    public void itShouldGetAllItems() {
        var response = testRestTemplate
                .getForEntity("/items", ItemDto[].class);
        var items = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getHeaders().getContentType()).isEqualTo(APPLICATION_JSON);
        assertThat(items).isNotNull();
    }

    @PostConstruct
    protected void init() {
        String token = "Bearer " + jwtService.generateToken("admin", "ROLE_ADMIN");
        url += port;
        testRestTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(url));
        testRestTemplate.getRestTemplate().setInterceptors(
                Collections.singletonList(((request, body, execution) -> {
                    request.getHeaders().add(AUTHORIZATION, token);
                    return execution.execute(request, body);
                }))
        );
    }
}
