package technicalblog.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import technicalblog.model.Post;
import technicalblog.service.PostService;

import java.util.ArrayList;
import java.util.List;


@Controller
public class HomeControllerLocal {
    //Writing a constructor just to print the statement in console
    public HomeControllerLocal(){
        System.out.println("**** HomeControllerLocal**********");
    }
    @Autowired //To say that controller is dependent on PostService service..Called as Dependency Injection.
    //Creates a Postservice object without new operator using @Autowired annotation.
    private PostService postService;

    @RequestMapping("/")
    public String getAllPosts(Model model) {
        List<Post> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "index";
    }
}
