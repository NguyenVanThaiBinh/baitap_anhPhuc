package com.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String email;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province;

    public Customer(String fullName, String email, String phone) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
    }

    public Customer(String email, String fullName, String phone, Province province) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.province = province;
    }


}
