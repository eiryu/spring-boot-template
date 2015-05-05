package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("comments", commentService.get());
        return "comment";
    }

    @RequestMapping("/register")
    public String registerComment(@RequestParam String comment, Model model, HttpServletResponse response) {
        try {
            commentService.register(comment);
        } catch (AppException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("comments", commentService.get());

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "comment";
        }
        return "redirect:/comment";
    }
}
