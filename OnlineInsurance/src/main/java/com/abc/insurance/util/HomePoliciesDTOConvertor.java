package com.abc.insurance.util;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.abc.insurance.dto.DefaultResponseDTO;
import com.abc.insurance.entity.HomePolicies;

@Component
@Scope("singleton")
public class HomePoliciesDTOConvertor {
	public static DefaultResponseDTO  getHomePoliciesDefaultDTO(HomePolicies homePolicies)
	{
		DefaultResponseDTO dto = new DefaultResponseDTO(
				homePolicies.getClientName(), 
				homePolicies.getHomePolicyId(), 
				homePolicies.getHomeInsurance().getInsuranceName(),
				                 "User Policies Created , policy Id : "+homePolicies.getHomeInsurance().getHId());
		
		return dto;
	}
	
}
