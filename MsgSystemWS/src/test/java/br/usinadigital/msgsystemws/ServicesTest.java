package br.usinadigital.msgsystemws;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import br.usinadigital.msgsystemws.model.Category;

public class ServicesTest {
	private static final Logger logger = LoggerFactory.getLogger(JdbcSpringTest.class);

	public static void main(String[] args) throws ParseException {

		RestTemplate restTemplate = new RestTemplate();
		logger.info("Send request...");
        Category[] categories = restTemplate.getForObject("http://localhost:8080/MsgSystemWS-0.0.1/rest/category/all", Category[].class);
        logger.info("categories="+categories.toString());
        logger.error("Errore");
	}
}
