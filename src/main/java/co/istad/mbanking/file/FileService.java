package co.istad.mbanking.file;

import org.springframework.web.multipart.MultipartFile;

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

}
