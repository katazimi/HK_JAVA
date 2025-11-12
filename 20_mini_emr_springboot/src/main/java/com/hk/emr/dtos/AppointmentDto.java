package com.hk.emr.dtos;

import java.time.LocalDateTime;

import org.apache.ibatis.type.Alias;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Alias(value="appointmentDto")
@Getter
@Setter
@ToString
public class AppointmentDto {
	private Integer appointmentId;
	
	@NotNull
	private Integer patientId;
	
	private String patientName;
	
	@NotNull
	private Integer doctorId;
	
	private String doctorName;
	
	private Integer staffId;
	
	private String dateTime;
	
	private String status;
	
	private String chartNumber;
}
