package br.usinadigital.msgsystemws.model;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.usinadigital.msgsystemws.util.CustomDateDeserializer;
import br.usinadigital.msgsystemws.util.CustomDateSerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class WSRequestGetMessagesFromDateByCategories {

	private Date fromDate;
	private int[] categoriesId;
	
	public WSRequestGetMessagesFromDateByCategories(){		
	}
	
	@JsonSerialize(using = CustomDateSerializer.class)
	@JsonDeserialize(using = CustomDateDeserializer.class)
	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	
	public int[] getCategoriesId() {
		return categoriesId;
	}
	
	public void setCategoriesId(int[] categoriesId) {
		this.categoriesId = categoriesId;
	}
	
	@Override
	public String toString(){
	        return ToStringBuilder.reflectionToString(this);
	}
}
