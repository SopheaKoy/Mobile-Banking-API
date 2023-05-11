package co.istad.mbanking.util;


import co.istad.mbanking.file.FileDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class FileUtil {
    @Value("${file.server-path}")
    public String fileServerPath;

    @Value("${file.base-url}")
    public String fileBaseUrl;

    @Value("${file.download-url}")
    public String fileDownloadUrl;
    public FileDto upload(MultipartFile multipartFile){
        String extension = getExtension(multipartFile.getOriginalFilename());
        long size = multipartFile.getSize();
        String name = String.format("%s.%s" , UUID.randomUUID() , extension);
        String url = String.format("%s%s" , fileBaseUrl,name);
        Path path = Paths.get(fileServerPath + name);
        try {
            Files.copy(multipartFile.getInputStream() , path);
            return FileDto.builder()
                            .fileName(name)
                            .url(url)
                            .extension(extension)
                    //                            .downloadUrl(String.format("%s.%s" , fileDownloadUrl+name))
                            .size(size)
                            .build();
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Upload file failed....!");
        }
    }

    /**
     * In spring have one  class resource
     * Create path object
     * method is (exists) use check has or not
     */
    public Resource findByName(String name){
        Path path = Paths.get(fileServerPath+name);
        try {
            Resource resource = new UrlResource(path.toUri());

            if(resource.exists()){
                return  resource;
            }
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND ,
                    "File is not found");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
    // create method for extension
    public String getExtension(String fileName){
        int lastDtoIndex = fileName.indexOf(".");
        return fileName.substring(lastDtoIndex);
    }

}
