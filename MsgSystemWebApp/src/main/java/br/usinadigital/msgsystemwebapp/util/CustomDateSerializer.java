package br.usinadigital.msgsystemwebapp.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CustomDateSerializer extends JsonSerializer<Date> {    
	
	@Override
	 public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) 
	   throws IOException, JsonProcessingException {
		
		SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);  
		String formattedDate = formatter.format(value);  
		jgen.writeString(formattedDate);
	 }
}
