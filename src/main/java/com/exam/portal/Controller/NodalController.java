package com.exam.portal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.exam.portal.Model.NodalOfficer;
import com.exam.portal.Repository.NodalOfficerRepository;

@Controller
public class NodalController {

	@Autowired
	NodalOfficerRepository nRepository;

	@GetMapping("/organiser/nodal")
	public String viewNodal(Model model) {
		List<NodalOfficer> nodal = nRepository.findAll();
		model.addAttribute("nodal", nodal);
		return "organiser/nodal/nodal_view";
	}

	@GetMapping("/organiser/nodalCreate")
	public String createNodal() {
		return "organiser/nodal/nodal_create";
	}

	@PostMapping("/organiser/nodal/register")
	public String registerNodal(@ModelAttribute NodalOfficer nodelOfficer) {
		// Save the organization to the database
		nRepository.save(nodelOfficer);
		// Redirect to a success page or return a success message
		return "organiser/nodal/nodal_view";
	}

}
