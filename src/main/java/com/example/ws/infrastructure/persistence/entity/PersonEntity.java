package com.example.ws.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class PersonEntity extends Base {

	private String name;

	@Column(unique = true)
	private String dni;

	private String gender;
	private int age;
	private String address;
	private String phone;

	@Column(name = "dni_type", nullable = false, length = 20)
	private String dniType;
}
