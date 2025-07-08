## ✅ In JOINED inheritance strategy:
`@DiscriminatorColumn` and `@DiscriminatorValue` are optional, not required.

JPA will handle table joins using foreign keys, so it knows which subclass a row belongs to.

## 🔍 When to use Discriminator with `JOINED`:
You can use it if you want a discriminator column in the base `resources` table (like resource_type) — useful if you need to query polymorphically and quickly determine the subclass type without joining.

But if you don’t need that column, you can skip it entirely.

## ✅ Clean version without Discriminator
### `Resources.java`
```java
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Resources {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Long size;
    private String url;
}
```
### `Video.java`
```java
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Video extends Resources {
    private int length;
}
```
### `File.java`
```java
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class File extends Resources {
    private String type;
}
```
### `Text.java`
```java
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Text extends Resources {
    private String content;
}
```

## ✅ 1. `Repositories`

```java
public interface ResourcesRepository extends JpaRepository<Resources, Long> {}
public interface VideoRepository extends JpaRepository<Video, Long> {}
public interface FileRepository extends JpaRepository<File, Long> {}
public interface TextRepository extends JpaRepository<Text, Long> {}
```
## ✅ 2. Controller: `ResourcesController.java`
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

    // ✅ READ ALL
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

    // ✅ READ BY ID
    @GetMapping("/{id}")
    public Resources getResourceById(@PathVariable Long id) {
        return resourcesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resource not found"));
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
### ✅ 3. Sample `JSON` Payloads
`POST /api/resources/video`
```json
{
  "name": "Java Tutorial",
  "size": 2048,
  "url": "http://example.com/java.mp4",
  "length": 600
}
```
`POST /api/resources/file`
```json
{
  "name": "Project Report",
  "size": 500,
  "url": "http://example.com/report.pdf",
  "type": "PDF"
}
```
`POST /api/resources/text`
```json
{
  "name": "Lecture Notes",
  "size": 100,
  "url": "http://example.com/notes.txt",
  "content": "Inheritance in Java explained..."
}
```