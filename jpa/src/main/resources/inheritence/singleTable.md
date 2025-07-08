## 1. `Resources.java` (Base Class)
```java
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "resource_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Resources {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Long size;
    private String url;
}
```
## 2. `Video.java`
```java
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@DiscriminatorValue("VIDEO")
@EqualsAndHashCode(callSuper = true)
public class Video extends Resources {
    private int length;
}
```
## 3. `File.java`
```java
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@DiscriminatorValue("FILE")
@EqualsAndHashCode(callSuper = true)
public class File extends Resources {
    private String type;
}
```
## 4. `Text.java`
```java
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@DiscriminatorValue("TEXT")
@EqualsAndHashCode(callSuper = true)
public class Text extends Resources {
    private String content;
}
```
## ✅ Now you're all set!
This is a clean and production-ready setup using:

- JPA Inheritance with `SINGLE_TABLE`

- Lombok for reducing boilerplate

- Correct equality semantics via `@EqualsAndHashCode(callSuper = true)`

## ✅ 1. Repository Interfaces
Since `Video`, `Text`, and `File` extend `Resources`, you can create separate repositories for each.

`ResourcesRepository.java`
```java
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourcesRepository extends JpaRepository<Resources, Long> {
}
```
`VideoRepository.java`
```java
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}
```
`FileRepository.java`
```java
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
```
`TextRepository.java`
```java
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextRepository extends JpaRepository<Text, Long> {
}
```
## ✅ 2. REST Controller
Here's a simple controller with basic CRUD operations for all three types.

`ResourcesController.java`
```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/resources")
public class ResourcesController {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private TextRepository textRepository;

    @Autowired
    private ResourcesRepository resourcesRepository;

    // ✅ CREATE
    @PostMapping("/video")
    public Video createVideo(@RequestBody Video video) {
        return videoRepository.save(video);
    }

    @PostMapping("/file")
    public File createFile(@RequestBody File file) {
        return fileRepository.save(file);
    }

    @PostMapping("/text")
    public Text createText(@RequestBody Text text) {
        return textRepository.save(text);
    }

    // ✅ READ
    @GetMapping
    public List<Resources> getAllResources() {
        return resourcesRepository.findAll();
    }

    @GetMapping("/video")
    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    @GetMapping("/file")
    public List<File> getAllFiles() {
        return fileRepository.findAll();
    }

    @GetMapping("/text")
    public List<Text> getAllTexts() {
        return textRepository.findAll();
    }

    // ✅ UPDATE
    @PutMapping("/video/{id}")
    public Video updateVideo(@PathVariable Long id, @RequestBody Video video) {
        video.setId(id);
        return videoRepository.save(video);
    }

    @PutMapping("/file/{id}")
    public File updateFile(@PathVariable Long id, @RequestBody File file) {
        file.setId(id);
        return fileRepository.save(file);
    }

    @PutMapping("/text/{id}")
    public Text updateText(@PathVariable Long id, @RequestBody Text text) {
        text.setId(id);
        return textRepository.save(text);
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public void deleteResource(@PathVariable Long id) {
        resourcesRepository.deleteById(id);
    }
}
```
## ✅ 3. Example JSON Payloads
`POST /api/resources/video`
```json
{
  "name": "Intro Video",
  "size": 1024,
  "url": "http://example.com/video.mp4",
  "length": 300
}
```
`POST /api/resources/file`
```json
{
  "name": "Document",
  "size": 200,
  "url": "http://example.com/doc.pdf",
  "type": "PDF"
}
```
`POST /api/resources/text`
```json
{
  "name": "Lecture Notes",
  "size": 150,
  "url": "http://example.com/notes.txt",
  "content": "This is a note."
}
```