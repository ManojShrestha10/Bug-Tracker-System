package com.Controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.Entity.BugReport;
import com.Forms.BugForms;
import com.services.BugService;
import com.services.serviceImplement.BugServiceImplement;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    // Logger instance
    Logger logger = LoggerFactory.getLogger(AdminController.class);

    // autowired BugService to handle business logic
    @Autowired
    private BugService bugService;
    @Autowired
    private BugServiceImplement bugServiceImplement;

    // Admin home page
    @RequestMapping("/home")
    public String adminHome() {
        return "/admin/home";
    }

    // Bug report form page
    @GetMapping(value = "/bugReport")
    public String bugReportForm(Model model) {
        model.addAttribute("BugForms", new BugForms());
        return "admin/bugReportForm";
    }

    // Controller methods for handling bug reports will go here
    @PostMapping(value = "/do-bugReport")
    public String handleBugReport(@Valid @ModelAttribute("BugForms") BugForms bugForms, BindingResult result,
            RedirectAttributes redirectAttributes) {
        // If there are validation errors, return to the bug report page
        if (result.hasErrors()) {
            return "admin/bugReportForm";
        }
        // Create a new BugReport entity
        BugReport bugReport = new BugReport();
        // set the title from the form to the entity
        bugReport.setTitle(bugForms.getTitle());
        // set the status from the form to the entity
        bugReport.setStatus(bugForms.getStatus());
        // set the project from the form to the entity
        bugReport.setProject(bugForms.getProject());
        // set the type from the form to the entity
        bugReport.setType(bugForms.getType());
        // set the reporter name from the form to the entity
        bugReport.setReporterName(bugForms.getReporterName());
        // save the bug report to the database
        bugService.saveBugReport(bugReport);
        // Log the successful submission
        logger.info("Bug report submitted successfully: {}", bugReport.getTitle());
        // Redirect to a success page or back to the form with a success message
        redirectAttributes.addFlashAttribute("message", "Bug report submitted successfully!");
        // return to the bug report form page
        return "redirect:bugReport";
    }

    // Send all bug reports to the model and display them in the admin view
    @GetMapping("/home")
    public String viewAllBugs(Model model) {
        List<BugReport> viewBugs = bugServiceImplement.findAllBugReports();
        model.addAttribute("viewBugs", viewBugs);
        System.out.println("Bugs fetched: " + viewBugs.size());
        return "/admin/home";
    }

    // Update bug report page
    @GetMapping("/admin/bug/edit/{id}")
    public String updateBugReport(@PathVariable("id") Long id) {
        // Fetch the bug report by id
        BugReport bugReport = bugServiceImplement.findBugReportById(id);
        // Return the update bug report page
        bugServiceImplement.updateBugReport(bugReport);
        return "admin/updateBugReport";
    }

    // Delete bug report by id
    @GetMapping("/bug/delete/{id}")
    public String deleteBugReportById(@PathVariable("id") Long id) {
        // delete the bug
        bugServiceImplement.deleteBugReportById(id);
        // logger the deletion
        logger.info("Admin delete the bug report");
        // return to the admin home page
        return "redirect:/admin/home";
    }

}
