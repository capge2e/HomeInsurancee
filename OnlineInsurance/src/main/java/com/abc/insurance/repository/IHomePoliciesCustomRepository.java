package com.abc.insurance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.abc.insurance.entity.HomePolicies;



@Repository
public interface IHomePoliciesCustomRepository {
	@Query("from HomePolicies where homePolicyNo >= :range1 and homePolicyNo <= :range2")
	public List<HomePolicies> getHomePoliciesBetweenHomePolicyNo(@Param("range1") int range1,@Param("range2") int range2)throws Exception;
	public HomePolicies getHomePoliciesByClientName(String clientName)throws Exception;
	

}
