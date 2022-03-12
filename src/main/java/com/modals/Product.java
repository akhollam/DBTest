package com.modals;

import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Product {
	
	private int id;
	private String name;
	private String category;
	private int quantity;
	private String code;
	private Date availableDate;
	private Date creationDate;

	public String getCategory() {
		return category;
	}
	
	public static void main(String[] args) {
		
		log.debug("some messae");
	}

}
