package com.skyspacefiftyfive.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.time.Year;
import jakarta.persistence.Transient;



@Entity
@Table(name="payment_details")
@Getter @Setter @NoArgsConstructor
public class PaymentDetail {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="payment_detail_id")
	private Integer paymentDetailId;
    
  	@Column(name="payment_amount")
	private double paymentAmount;
    
  	@Column(name="payment_date")
	private Date paymentDate;
    
	




}
