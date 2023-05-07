package co.istad.mbanking.file;

import co.istad.mbanking.base.BaseRest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpStatus;
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
}
