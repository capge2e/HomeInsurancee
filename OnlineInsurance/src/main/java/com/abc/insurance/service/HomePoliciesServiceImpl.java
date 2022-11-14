package com.abc.insurance.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abc.insurance.entity.HomeInsurance;
import com.abc.insurance.entity.HomePolicies;
import com.abc.insurance.repository.HomePoliciesRepository;




@Service
public class HomePoliciesServiceImpl implements HomePoliciesService {
	@Autowired
	HomePoliciesRepository homePoliciesRepository;
	
	@Override
	@Transactional
	public HomePolicies addHomePolicies(HomePolicies homePolicies) throws Exception {
		HomePolicies savedHomePolicy =homePoliciesRepository.save(homePolicies);
		return  savedHomePolicy;
	}

	@Override
	public List<HomePolicies> viewAllPolicies() throws Exception {
		List<HomePolicies> allPolicies = homePoliciesRepository.findAll(); // Note : same as save
		return allPolicies;
	}

	@Override
	public List<HomePolicies> getHomePoliciesBetweenHomePolicyNo(int range1, int range2) throws Exception {
		// TODO Auto-generated method stub
		return  homePoliciesRepository.getHomePoliciesBetweenHomePolicyNo(range1, range2);
	}

	@Override
	public HomePolicies getHomePoliciesByClientName(String clientName) throws Exception {
		// TODO Auto-generated method stub
		return homePoliciesRepository.getHomePoliciesByClientName(clientName);
	}

	
	@Override
	public List<HomePolicies> getHomePoliciesBasedOnisClaimedDate(String isClaimed, String claimedDate)
			throws Exception {

		return ((HomePoliciesServiceImpl) homePoliciesRepository).getHomePoliciesBasedOnisClaimedDate(isClaimed, claimedDate);
	
	}
	

	@Override
	@Transactional
	public HomePolicies linkHomeInsurance(HomeInsurance homeInsurance, HomePolicies homePolicies) {
		homePolicies.setHomeInsurance(homeInsurance);
		return homePolicies;
	}

	@Override
	public HomePolicies updateHomePolicies(HomePolicies homePolicies) throws Exception {
		return homePoliciesRepository.save(homePolicies);
	}

	@Override
	public void deleteHomePoliciesByHomePolicyId(int homePolicyId) throws Exception {
		homePoliciesRepository.deleteById(homePolicyId);
		
	}

	@Override
	public List<HomePolicies> findHomePoliciesWithSorting(String field) throws Exception {
		return homePoliciesRepository.findAll(Sort.by(Sort.Direction.DESC,field));
	}
	

	

}
