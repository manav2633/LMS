package com.exam.portal.Controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.exam.portal.Model.Organization;
import com.exam.portal.Repository.OrganizationRepository;

@Controller
public class OrganizationController {

	public OrganizationRepository getRepo() {

		return repo;
	}

	public void setRepo(OrganizationRepository repo) {
		this.repo = repo;
	}

	@Autowired
	OrganizationRepository repo;

	@GetMapping("/organiser/organization")
	public String showOrg(Model model) {

		List<Organization> org = repo.findAll();
		model.addAttribute("org", org);
		return "organiser/organization/org";
	}

	@GetMapping("/organiser/organization/new")
	public String newOrg() {

		return "organiser/organization/createOrg";
	}

	@PostMapping("/organiser/organization/register")
	public String registerOrganization(@ModelAttribute Organization organization) {

		// Save the organization to the database
		repo.save(organization);
		// Redirect to a success page or return a success message
		return "redirect:/organiser/organization/";
	}

	// Delete item from Config Campaign
	@RequestMapping(value = "/organiser/delcampaign", method = RequestMethod.GET)
	public String deleteConfig(@RequestParam("cid") Integer[] configId) {
		System.out.println("hello");
		repo.delOrg(Arrays.asList(configId));

		return "redirect:/organiser/organization";
	}
	

	@GetMapping("/organiser/organization/view/{id}")
	public String viewOrganization(@PathVariable("id") Integer id, Model model) {

		Organization org = repo.findByOrganizationId(id);
		System.out.println(org);
		if (org != null) {
			model.addAttribute("org", org);
		} else {
			System.out.println("not shown view");
		}
		return "organiser/organization/oview";
	}

	// @PostMapping("/organiser/organization/edit")

	// public String editOrganization(@ModelAttribute("org") Organization org) {
	// // Handle organization edit logic here
	// // You can use the organization object passed from the form to update the
	// // organization details in the database
	// // For example:
	// Organization existingOrg =
	// repo.findByOrganizationId(org.getOrganizationId());
	// if (existingOrg != null) {
	// existingOrg.setOrganization_name(org.getOrganization_name());
	// // Set other fields as needed
	// repo.save(existingOrg); // Save the updated organization
	// }
	// return "redirect:/organiser/organization/view/" + org.getOrganizationId();
	// }

	@GetMapping("/organiser/org/edit")
	public String editExam(@RequestParam(name = "id") Integer id, Model model) {
		Organization org = repo.findByOrganizationId(id);
		model.addAttribute("org", org);
		return "organiser/organization/updateOrg";
	}

	@PostMapping("/organiser/org/edit")
	public String editSaveOrg(Organization org) {

		Organization org1 = repo.findByOrganizationId(org.getOrganizationId());
		if (org1 != null) {
			// Update the properties of the retrieved organization object
			// with the properties of the org object passed as a parameter
			// (Assuming that you want to update the existing organization)
			org1.setOrganization_name(org.getOrganization_name());
			org1.setDescription(org.getDescription());
			// Update other properties as needed
			
			// Save the updated organization object back to the repository
			repo.save(org1);
		} else {
			System.out.println("does not print ");
			// Handle the case where the organization is not found
			// You may want to log an error message or handle it differently
		}
	
		// Redirect to the appropriate page after saving
		return "redirect:/organiser/organization";

	}
}