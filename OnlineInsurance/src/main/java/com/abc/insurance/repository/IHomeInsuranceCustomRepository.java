package com.abc.insurance.repository;

import java.util.List;


import org.springframework.stereotype.Repository;

import com.abc.insurance.entity.HomeInsurance;


@Repository
public interface IHomeInsuranceCustomRepository {
	public List<HomeInsurance> getHomeInsuranceByPremium(int premium)throws Exception;
	public HomeInsurance getHomeInsuranceBySumInsured(int sumInsured)throws Exception;
	public HomeInsurance getHomeInsuranceByInsuranceName(String insuranceName)throws Exception;
}
