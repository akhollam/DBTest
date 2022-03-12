package com.modals;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductCategory {

	private int id;
	private String name;
	private String description;
	private Date creationDate;
	private Date updationDate;
	
}
