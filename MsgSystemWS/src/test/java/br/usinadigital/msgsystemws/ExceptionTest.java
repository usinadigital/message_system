package br.usinadigital.msgsystemws;

import java.text.ParseException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionTest {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionTest.class);

	public static void main(String[] args) throws ParseException {

		logger.info("Test started...");
        try{
        	new NullPointerException("ciao");
        	int i = 100 / 0;
        }catch(Exception ex){
        	logger.error("ECCEZIIONE:" + ex);
        }
	}	
}
