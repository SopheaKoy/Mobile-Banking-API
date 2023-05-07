package co.istad.mbanking.file;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.connector.Response;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {

    /**
     *  use to upload a single file
     * @param file
     * @return
     */
    FileDto uploadSingle(MultipartFile file);

    /**
     * use to upload multiple files
     * @param fileList
     * @return
     */

    List<FileDto> uploadMultiple (List<MultipartFile> fileList);

    /**
     *  the method use for get all files
     * @return
     */
    List<FileDto> getListOfFiles();


    // find image by filename
    FileDto getFileByName(String name);

    // Delete by filename
    boolean deleteFileByName(String name);

    // Delete all File
    boolean deleteAllFile();


    // file Download by name
    Resource downloadFileByName(String fileName);


}
