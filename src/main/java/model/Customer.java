package model;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document
@Getter
@Setter
public class Customer {
	@Id
	private int id;
	private String name;
	private String mobile;
}