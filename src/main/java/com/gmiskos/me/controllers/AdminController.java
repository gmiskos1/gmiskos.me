package com.gmiskos.me.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/api/v1/blogs")
public class AdminController {

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')")
    public String getBlogs(){
        System.out.println(">>>>> get all blogs >>>>>");
        return "All blogs";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('blog:write')")
    public void addNewBlog(@RequestBody String blog) {
        System.out.println(">>>> add new blog >>>>");
        System.out.println(blog);
    }

    @DeleteMapping(path = "{blogId}")
    @PreAuthorize("hasAuthority('blog:write')")
    public void deleteBlog(@PathVariable("blogId") Integer blogId) {
        System.out.println(">>> delete blog id >>>>");
        System.out.println(blogId);
    }

    @PutMapping(path = "{blogId}")
    @PreAuthorize("hasAuthority('blog:write')")
    public void updateBlog(@PathVariable("blogId") Integer blogId, @RequestBody String blog) {
        System.out.println(">>>> update blog >>>");
        System.out.println(String.format("%s %s", blogId, blog));
    }
}
