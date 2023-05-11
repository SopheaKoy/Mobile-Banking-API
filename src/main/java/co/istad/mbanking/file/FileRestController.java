package co.istad.mbanking.file;

import co.istad.mbanking.base.BaseRest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/files")
@Slf4j
@RequiredArgsConstructor
public class FileRestController {
    // upload files use request path

    private final FileService fileService;

    @Value("${file.base-url}")
    public String fileBaseUrl;

    @PostMapping
    public BaseRest<?> uploadSingle(@RequestPart MultipartFile file){
        FileDto fileDto=fileService.uploadSingle(file);
        return BaseRest.builder()
                .status(true)
                .message("Files uploaded successfully.")
                .timestamp(LocalDateTime.now())
                .data(fileDto)
                .code(HttpStatus.OK.value())
                .build();
    }
    @PostMapping("/multiple")
    public BaseRest<?> uploadMultiple(@RequestPart List<MultipartFile> files){
        List<FileDto> fileDto = fileService.uploadMultiple(files);
        return BaseRest.builder()
                .status(true)
                .message("Files uploaded successfully.")
                .timestamp(LocalDateTime.now())
                .data(fileDto)
                .code(HttpStatus.OK.value())
                .build();
    }
    @GetMapping
    public BaseRest<?> getListOfFiles(){
        List<FileDto>  fileDto = fileService.getListOfFiles();
        if(fileDto.isEmpty()){
            System.out.println("Files not found..!");
        }
        return BaseRest.builder()
                .status(true)
                .message("Files select successfully.")
                .timestamp(LocalDateTime.now())
                .data(fileDto)
                .code(HttpStatus.OK.value())
                .build();
    }

    @GetMapping("/{fileName}")
    public BaseRest<?> getFileByName(@PathVariable String fileName){
        FileDto fileDto = fileService.getFileByName(fileName);
        return BaseRest.builder()
                .status(true)
                .message("Select by fileName is found.")
                .timestamp(LocalDateTime.now())
                .data(fileDto)
                .code(HttpStatus.OK.value())
                .build();
    }
    @GetMapping("/{name}")
    public BaseRest<?> findFileByName(@PathVariable String name){
        FileDto fileDto = fileService.getFileByName(name);
        return BaseRest.builder()
                .status(true)
                .message("Select by fileName is found.")
                .timestamp(LocalDateTime.now())
                .data(fileDto)
                .code(HttpStatus.OK.value())
                .build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{fileName}")
    public BaseRest<?> deleteByName(@PathVariable String fileName){
       boolean isDeleted = fileService.deleteFileByName(fileName);
        return BaseRest.builder()
                .status(true)
                .message("Deleted by fileName is successfully.")
                .timestamp(LocalDateTime.now())
                .data(isDeleted)
                .code(HttpStatus.OK.value())
                .build();
    }
    @DeleteMapping
    public BaseRest<?> deleteByName(){
        boolean isDeletedAll = fileService.deleteAllFile();
        return BaseRest.builder()
                .status(true)
                .message("Deleted all files is successfully.")
                .timestamp(LocalDateTime.now())
                .data(isDeletedAll)
                .code(HttpStatus.OK.value())
                .build();
    }
//    @GetMapping("/download/{fileName}")
//    public ResponseEntity<?> downloadFile(@PathVariable("fileName") String fileName){
//        Resource resource = fileService.downloadFileByName(fileName);
//        System.out.println(fileBaseUrl+"api/v1/files/download/"+fileName);
//        return ResponseEntity
//                .ok()
//                .contentType(MediaType.parseMediaType(MediaType.APPLICATION_ATOM_XML_VALUE))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() +"\"")
//                .body(resource);
//
//        }
    @GetMapping("/download/{name}")
    public ResponseEntity<?> fileDownload(@PathVariable String name){
        Resource resource = fileService.downloadFile(name);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + resource.getFilename())
                .contentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE))
                .body(resource);
    }

}
