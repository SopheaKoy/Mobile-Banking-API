package co.istad.mbanking.file;

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


    /**
     * find image by filename
     * st 1
     * @param name
     * @return
     */
    FileDto getFileByName(String name);

    /**
     * sc 2
     * @param name
     * @return
     */

    FileDto findByName(String name) throws IOException;


    // Delete by filename
    boolean deleteFileByName(String name);

    /**
     * use for delete all files
     * @return
     */
    boolean deleteAllFile();


    // file Download by name
    Resource downloadFileByName(String fileName);

    // File Download by file Name

    Resource downloadFile(String name);

    // In spring it has clas resource



}
