package com.coading.jpa;

import org.springframework.web.bind.annotation.RestController;

import com.coading.jpa.models.Author;
import com.coading.jpa.models.Video;
import com.coading.jpa.repositories.AuthorRepository;
import com.coading.jpa.repositories.ResourcesRepository;
import com.coading.jpa.repositories.VideoRepository;
import com.github.javafaker.Faker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;





@RestController
public class jpaControler {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private ResourcesRepository resourcesRepository;

    @Autowired
    private AuthorRepository authorRepository;

    Faker faker = new Faker();

    @PostMapping("/power")
    public List<?> power() {
		Video video = new Video();
		video.setName(faker.name().fullName());
		video.setSize(4);
		video.setUrl("www.com");
		video.setLength(faker.number().randomDigit());
		videoRepository.save(video);
		return resourcesRepository.findAll();
    }

    @PostMapping("/author")
    public String authorAdd() {
        for(int i=0; i<10; i++){
          Author author = new Author(faker.name().firstName(), faker.name().lastName(), faker.number().numberBetween(15, 50), faker.name().firstName()+faker.number().randomDigit()+"@gmail.com");
          authorRepository.save(author);
        }
        return "The data is added";
    }

    @GetMapping("/author")
    public List<Author> getAllAuthor() {
        return authorRepository.findAll(Sort.by("id"));
    }

    @PutMapping("/author/{id}")
    public String updateData(@PathVariable Integer id) {
      authorRepository.updateById(id, 100);
        
        return "The data is updated";
    }
    

    @DeleteMapping("/author")
    public String authorDelete(){
      authorRepository.deleteAll();
      return "All data is deleted";
    }
    
    
}   
