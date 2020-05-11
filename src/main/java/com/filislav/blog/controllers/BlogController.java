package com.filislav.blog.controllers;

import com.filislav.blog.models.Post;
import com.filislav.blog.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    private PostRepository postRepository;
    @GetMapping("/blog")
    public String blogMain(Model model){
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts",posts);
        return "blogMain";
    }
    @GetMapping("/blog/add")
    public String blogAdd(Model model){
        return "blogAdd";
    }
    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title,@RequestParam String anons,@RequestParam String full_text, Model model){
        Post post = new Post(title,anons,full_text);
        postRepository.save(post);
        return "redirect:/blog";
    }
    @GetMapping("/blog/{id}")
    public String blogDetail(@PathVariable(value = "id") long id, Model model){
        if(postRepository.existsById(id)) {
            Optional<Post> post = postRepository.findById(id);
            ArrayList<Post> res = new ArrayList<>();
            post.ifPresent(res::add);
            model.addAttribute("post", res);
            return "blogDetails";
        }
        return "redirect:/blog";
    }

}
