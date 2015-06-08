package br.usinadigital.msgsystemws.util;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomDateDeserializer extends JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) throws IOException, JsonProcessingException {
		String str = paramJsonParser.getText().trim();
		try {
			return Utils.fromStringToDate(str);
		} catch (ParseException e) {

		}
		return paramDeserializationContext.parseDate(str);
	}
}
