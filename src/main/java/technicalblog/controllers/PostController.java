package technicalblog.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import technicalblog.model.Post;
import technicalblog.service.PostService;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping("posts")
    public String getUserPosts(Model model) {
        //Post onePost = postService.getOnePost();
        List<Post> posts = postService.getAllPosts();
       // posts.add(onePost);
        model.addAttribute("posts", posts);
        return "posts";
    }

    @RequestMapping(value = "/posts/new", method = RequestMethod.POST)
    public String createPost() {
        return "posts/create";
    }

    @RequestMapping(value = "/posts/create", method = RequestMethod.POST)
    public String createPost(Post newPost) {
        postService.createPost(newPost);
        return "redirect:/posts";
    }

    @RequestMapping(value = "/editPost", method =  RequestMethod.GET)
    public String editPost(@RequestParam(name="postId") Integer postId, Model model) {
        Post posts = postService.getPost(postId);
        model.addAttribute("post",posts);
        return "posts/edit";
    }

    @RequestMapping(value ="/editPost",method = RequestMethod.PUT)
    public String editPostSubmit(@RequestParam(name="postId") Integer postId, Post updatedPost) {
        updatedPost.setId(postId);
        postService.updatePost(updatedPost);
        return "redirect:/posts";
    }

    @RequestMapping(value = "/deletePost",method = RequestMethod.DELETE)
    public String deletePostSubmit(@RequestParam(name="postId") Integer postId) {
        postService.deletePost(postId);
        return "redirect:/posts";
    }
}