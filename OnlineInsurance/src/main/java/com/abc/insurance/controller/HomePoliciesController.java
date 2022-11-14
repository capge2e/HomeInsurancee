package com.abc.insurance.controller;




import java.util.List;





import javax.validation.Valid;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abc.insurance.dto.DefaultResponseDTO;
import com.abc.insurance.dto.ErrorDTO;
import com.abc.insurance.dto.MyDTO;
import com.abc.insurance.entity.HomeInsurance;
import com.abc.insurance.entity.HomePolicies;
import com.abc.insurance.service.HomeInsuranceService;
import com.abc.insurance.service.HomePoliciesService;



import lombok.Value;


@RestController
@RequestMapping("/policy/client/homePolicies")
@Validated
public class HomePoliciesController {
	@Autowired
	HomePoliciesService homePoliciesService;
	
	@Autowired
	HomeInsuranceService homeInsuranceService;
	
	@GetMapping("/welcome")
	public String welcome()
	{
		
		return "Welcome to Home Policies";
		
	}
private final Logger mylogs = LoggerFactory.getLogger(this.getClass());
	
@PostMapping("/addHomePolicies")//..localhost:8009/policy/client/homePolicies/viewAllPolices
public ResponseEntity<String> addHomePolicies(@RequestBody HomePolicies homePolicies) 
{
	
	try {
		System.out.println(" --- > "+mylogs);
		mylogs.info("---->>>Inside try of adding home insurance");
		HomePolicies savedPolicies =   homePoliciesService.addHomePolicies(homePolicies);
		String responseMsg = savedPolicies.getClientName()+  "   save with premium:   "+savedPolicies.getHomePolicyNo();
		return new ResponseEntity<String>(responseMsg,HttpStatus.OK);
	} catch (Exception e) {
		String errorMsg =  " Please, Contact to customer care 1800-250-960 or mail us :- care@gmail.com";
		return new ResponseEntity<String>(errorMsg,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

@GetMapping("/viewAllPolices")
public List<HomePolicies> viewAllHomePolicies()
{

	try {
		List<HomePolicies>  allExtractedPolicies =homePoliciesService.viewAllPolicies();
		
		return allExtractedPolicies ;
		
	} catch (Exception e) {
	
		System.out.println(e);
		
	}
	
	return null;
}

	@GetMapping("/policiesByPolicyNo")//localhost:8009/policy/client/homePolicies/policiesByPolicyNo?r1=432&r2=475
	public List<HomePolicies> policiesBetweenPolicyNo(@RequestParam int r1 , @RequestParam int r2)throws Exception
	{
		
		return homePoliciesService.getHomePoliciesBetweenHomePolicyNo(r1, r2);
	}	
	
	@GetMapping("/policies/{searchClientName}")//localhost:8009/policy/client/homePolicies/policies/Raam...not ex
	public HomePolicies getByClientName(@PathVariable String clientName)throws Exception
	{
		return homePoliciesService.getHomePoliciesByClientName(clientName);
	}
	@GetMapping("/isClaimed/{claimedDate}")//localhost:8009/policy/client/homePolicies/Yes?18-11-2018...not ex
	public List<HomePolicies> getPoliciesOnClaimedDate(@PathVariable String isClaimed,@RequestParam String claimedDate)throws Exception
	{
		if(claimedDate != null)
		{
			return homePoliciesService.getHomePoliciesBasedOnisClaimedDate(isClaimed, claimedDate);
		}
		else return null;
	}
	 @PutMapping("/updateHomePolicies")//.........localhost:8009/policy/client/homePolicies/updateHomePolicies
		public HomePolicies updateHomePolicies(@RequestBody HomePolicies homePolicies)throws Exception
		{
			
			return homePoliciesService.updateHomePolicies(homePolicies);
			
			
		}
	    @DeleteMapping("/deleteHomePolicies")//.....localhost:8009/policy/client/homePolicies/deleteHomePolicies?homePolicyId=2..cannot do bcoz of foreign key
	    public String deleteHomePolicies(@RequestParam int homePolicyId) throws Exception
	    {
	    	homePoliciesService.deleteHomePoliciesByHomePolicyId(homePolicyId);
	    	mylogs.info("Deleted Policies=" +homePolicyId+"Data");
	    	return "Deleted id =" +homePolicyId+ "Data";
	    }
	    @GetMapping("/{field}")//..localhost:8009/policy/client/homePolicies/homePolicyNo
	    public List<HomePolicies> getHomePoliciesWithSort(@PathVariable String field) throws Exception
	    {
	    	List<HomePolicies> allHomePolicies=homePoliciesService.findHomePoliciesWithSorting(field);
	    	return allHomePolicies;
	    }
	
}


