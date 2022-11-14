package com.abc.insurance.controller;


import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.abc.insurance.util.HomePoliciesDTOConvertor;



@RestController
@RequestMapping("/safeInsurance/admin/homeInsurance")
public class HomeInsuranceWebController {
	@Autowired
	HomeInsuranceService homeInsuranceService;
	
	@Autowired
	HomePoliciesService homePoliciesService;
	@Autowired
	HomePoliciesDTOConvertor dtoConvertor;
	
	public  HomeInsuranceWebController() {
		System.out.println("\n\n\n====>> Inside Constructor "+this);
	}
	
	
	
	private final Logger mylogs = LoggerFactory.getLogger(this.getClass());
	
		
	@PostMapping("/addinsurance")  // ....../localhost:8009/safeInsurance/admin/homeInsurance/addinsurance?clientName=Ananya
	public ResponseEntity<MyDTO> doHomeInsuranceThings(@RequestBody @Valid HomeInsurance homeInsurance,@RequestParam String clientName)
	{
		HomePolicies alreadySavedPolicies = null;
		try
		{
			System.out.println(" --- > "+mylogs);
			mylogs.info("---->>>Inside try of doHomeInsurance Things");
			HomeInsurance  savedInsurance = homeInsuranceService.addHomeInsurance(homeInsurance);
			if(savedInsurance .getHId()!= 0)
			{
				mylogs.info("---->>>Inside if get home policy");
				alreadySavedPolicies  = homePoliciesService.getHomePoliciesByClientName(clientName);
				if(alreadySavedPolicies!= null)
				{ 
					mylogs.info("---->>>Inside if alreadySavedPolices not equal to null");
					HomePolicies homeInsuranceAddPolicy = homePoliciesService.linkHomeInsurance(savedInsurance, alreadySavedPolicies);
					
					DefaultResponseDTO dtoResponse = dtoConvertor.getHomePoliciesDefaultDTO(homeInsuranceAddPolicy);
					
					return new ResponseEntity<>(HttpStatus.OK);
					
				}
				else
				{
					mylogs.error("Insurance not found in post mapping uri : add");
					throw new Exception("Insurance not found ,  "+alreadySavedPolicies+" for "+clientName);
					//"errorMsg": "Insurance not found ,  null for Fire Insurance"
				}
				
			}
		}
		catch (Exception e) {
			System.out.println(e);
			ErrorDTO errorDTo = new ErrorDTO(e.getMessage());
			return new ResponseEntity<>(errorDTo, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return null;
		
	
	}
	@GetMapping("/viewHomeInsurance")//....localhost:8009/safeInsurance/admin/homeInsurance/viewHomeInsurance
	public List<HomeInsurance> viewAllInsurance()
	{

		try {
			List<HomeInsurance>  allExtractedInsurance =homeInsuranceService.getAllHomeInsurance();
			
			return allExtractedInsurance;
			
		} catch (Exception e) {
		
			System.out.println(e);
			
		}
		
		return null;
	}
	@GetMapping("/sumInsured/{sumInsured}")//....localhost:8009/safeInsurance/admin/homeInsurance/sumInsured/1000000
	public HomeInsurance getHomeInsuranceBySumInsured(@PathVariable int sumInsured)throws Exception
	{
		
		return homeInsuranceService.getHomeInsuranceBySumInsured(sumInsured);
		
	}
	
	@GetMapping("/premium/{premium}")//.........localhost:8009/safeInsurance/admin/homeInsurance/premium/7000
	public List<HomeInsurance> getHomeInsuranceByPremiumamount(@PathVariable  int premium) throws Exception
	{
		
		return  homeInsuranceService.getHomeInsuranceByPremium(premium);
		
	}
	
	
    @GetMapping("/insuranceName/{name}")
    public HomeInsurance getHomeInsuranceByInsuranceName(@PathVariable String insuranceName) throws Exception
    {
    	return homeInsuranceService.getHomeInsuranceByInsuranceName(insuranceName);
    }
    @PutMapping("/updateHomeInsurance")//.........localhost:8009/safeInsurance/admin/homeInsurance/updateHomeInsurance
	public HomeInsurance updateHomeInsurance(@RequestBody HomeInsurance homeInsurance)throws Exception
	{
		
		return homeInsuranceService.updateHomeInsurance(homeInsurance);
		
		
	}
    @DeleteMapping("/deleteHomeInsurance")//.....localhost:8009/safeInsurance/admin/homeInsurance/deleteHomeInsurance?hId=5..cannot do bcoz of foreign key
    public String deleteInsurance(@RequestParam int hId) throws Exception
    {
    	homeInsuranceService.deleteInsuranceByHId(hId);
    	mylogs.info("Deleted Insurance=" +hId+"Data");
    	return "Deleted id =" +hId+ "Data";
    }
    @GetMapping("/{field}")//..localhost:8009/safeInsurance/admin/homeInsurance/premium
    public List<HomeInsurance> getHomeInsuranceWithSort(@PathVariable String field) throws Exception
    {
    	List<HomeInsurance> allHomeInsurance=homeInsuranceService.findHomeInsuranceWithSorting(field);
    	return allHomeInsurance;
    }
    @GetMapping("/filterInsuranceName/{insuranceName}")
    public List<HomeInsurance> filterInsurance(@PathVariable String insuranceName)throws Exception
    {
    	List<HomeInsurance> allfiltered=homeInsuranceService.filterByInsuranaceName(insuranceName);
    	return  allfiltered;
    }
    
    
}//end of class

