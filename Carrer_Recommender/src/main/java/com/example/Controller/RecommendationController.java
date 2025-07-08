package com.example.Controller;

import com.example.Entity.SkillInput;
import com.example.Entity.User;
import com.example.Repository.SkillInputRepository;
import com.example.Repository.UserRepository;
import com.example.util.MarkdownUtil;
import com.example.util.pdfUtil;
import com.example.Entity.Message;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class RecommendationController {

    @Autowired
    private SkillInputRepository skillinputrepo;

    @Autowired
    private UserRepository userrepo;

    // Home Page
    @GetMapping("/skills-home")
    public String home() {
        return "home";
    }

    // Registration Page
    @GetMapping("/about")
    public String aboutPage(Model model) {
        model.addAttribute("user", new User());
        return "about";
    }

    // Register User
    @PostMapping("/do_register")
    public String registerUser(@Valid @ModelAttribute("user") User user,
                               BindingResult results,
                               @RequestParam(value = "agreement", defaultValue = "false") boolean agreement,
                               Model model,
                               HttpSession session) {
        try {
            if (!agreement) throw new Exception("You have not agreed to the terms and conditions.");
            if (results.hasErrors()) {
                model.addAttribute("user", user);
                return "about";
            }
            userrepo.save(user);
            session.setAttribute("currentUser", user);
            session.setAttribute("message", new Message("Successfully registered", "alert-success"));
            return "redirect:/recommendation";
        } catch (Exception e) {
            model.addAttribute("user", user);
            session.setAttribute("message", new Message(e.getMessage(), "alert-danger"));
            return "about";
        }
    }

    // Show Recommendation Form
    @GetMapping("/recommendation")
    public String showRecommendationForm(Model model) {
        model.addAttribute("skillInput", new SkillInput());
        return "recommendation";
    }

    // Process Recommendation Form
    @PostMapping("/careerForm")
    public String processForm(@ModelAttribute SkillInput skillInput, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        skillInput.setUser(currentUser);
        skillinputrepo.save(skillInput);

        String interest = skillInput.getInterestArea().toLowerCase();

        // Redirect to dynamic result URL based on interest
        if (interest.contains("backend")) return "redirect:/result/backend";
        else if (interest.contains("frontend")) return "redirect:/result/frontend";
        else if (interest.contains("android")) return "redirect:/result/android";
        else if (interest.contains("ai") || interest.contains("ml")) return "redirect:/result/ai";
        else if (interest.contains("desktop")) return "redirect:/result/desktop";
        else if (interest.contains("game")) return "redirect:/result/game";
        else if (interest.contains("fullstack") || interest.contains("full stack")) return "redirect:/result/fullstack";

        return "redirect:/result/others"; // fallback
    }

    // If someone accesses careerForm via GET directly
    @GetMapping("/careerForm")
    public String fallbackGetCareerForm() {
        return "redirect:/recommendation";
    }

    // Unified dynamic result page
    @GetMapping("/result/{career}")
    public String showResult(@PathVariable("career") String career, Model model) {
        String title;
        String fileName;

        switch (career.toLowerCase()) {
            case "backend":
                title = "Backend Development Roadmap";
                fileName = "backend.md";
                break;
            case "frontend":
                title = "Frontend Development Roadmap";
                fileName = "frontend.md";
                break;
            case "ai":
            case "ai/ml":
                title = "AI/ML Roadmap";
                fileName = "ai-ml.md";
                break;
            case "android":
                title = "Android Development Roadmap";
                fileName = "android.md";
                break;
            case "desktop":
                title = "Desktop Application Roadmap";
                fileName = "desktop.md";
                break;
            case "game":
                title = "Game Development Roadmap";
                fileName = "game.md";
                break;
            case "fullstack":
                title = "Full Stack Development Roadmap";
                fileName = "fullstack.md";
                break;
            default:
                title = "Explore Your Career Path";
                fileName = "fallback.md";
                break;
        }

        try {
            String roadmapHtml = MarkdownUtil.parseMarkdownToHtml("src/main/resources/roadmaps/" + fileName);
            model.addAttribute("roadmap", roadmapHtml);
        } catch (IOException e) {
            model.addAttribute("roadmap", "<p>Sorry, roadmap not available.</p>");
        }

        model.addAttribute("title", title);
        model.addAttribute("career", career.toLowerCase()); // Needed for PDF download

        return "result";
    }

    // Download roadmap as PDF
    @GetMapping("/download/{career}")
    public ResponseEntity<byte[]> downloadRoadmapPdf(@PathVariable String career) throws IOException {
        String title = career.substring(0, 1).toUpperCase() + career.substring(1) + " Development Roadmap";

        // ✅ Load the correct .md file from resources
        String filePath = "src/main/resources/roadmaps/" + career.toLowerCase() + ".md";

        // ✅ Convert Markdown to HTML
        String roadmapHtml = MarkdownUtil.parseMarkdownToHtml(filePath);

        // ✅ Generate PDF from HTML
        ByteArrayOutputStream pdfStream = pdfUtil.generatePdfFromHtml(roadmapHtml);

        // ✅ Return the response
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + career + "-roadmap.pdf")
                .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                .body(pdfStream.toByteArray());
    }


}
