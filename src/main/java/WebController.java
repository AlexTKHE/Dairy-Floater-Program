import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebController {

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("formInput", new FormInput()); // FormInput is a simple class to hold your form data
        return "form";
    }

    @PostMapping("/processForm")
    public String processForm(FormInput formInput, Model model) {
        // Call your Java logic here with formInput
        try {
            HalfHourIntervals.main(formInput.getStartOfDay(), formInput.getEndOfDay(), formInput.getNumberOfLines());
            model.addAttribute("result", "Data processed successfully!");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
            model.addAttribute("result", "Error processing data!");
        }

        return "result";
    }
}
