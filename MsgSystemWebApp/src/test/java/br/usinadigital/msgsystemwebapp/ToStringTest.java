package br.usinadigital.msgsystemwebapp;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ToStringTest {

	private static final Logger logger = LoggerFactory.getLogger(ToStringTest.class);

	List<String> cats = new ArrayList<String>(0);
	
	public static void main(String[] args) {
		
		ToStringTest test = new ToStringTest();
		
		test.cats.add("un");
		test.cats.add("dois");
		test.cats.add("tres");
		
		logger.info("Catsaaa="+test.cats);
		
		test.printCats(test.cats);
	}

	private void printCats(List<String> cats){
		String c="";
		for(String item : cats){
			c = c + item + " | ";
		}
		logger.info("Cats="+c);
	}
	
}
