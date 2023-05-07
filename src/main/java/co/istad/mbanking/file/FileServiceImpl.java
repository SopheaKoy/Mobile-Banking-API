package co.istad.mbanking.file;

import co.istad.mbanking.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class FileServiceImpl implements  FileService{
    private  FileUtil fileUtil;

    @Autowired
    public void setFileUtil(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    @Override
    public FileDto uploadSingle(MultipartFile multipartFile) {
         return fileUtil.upload(multipartFile);
    }

    @Override
    public List<FileDto> uploadMultiple(List<MultipartFile> fileList) {
        List<FileDto> filesDto = new ArrayList<>();
        for (MultipartFile multipartFile : fileList) {
            filesDto.add(fileUtil.upload(multipartFile));
        }
        return filesDto;
    }

    @Override
    public List<FileDto> getListOfFiles() {
        List<FileDto> filesDto = new ArrayList<>();
        File files = new File(fileUtil.fileServerPath);
        File [] getListOfFiles = files.listFiles();
        for(File file : getListOfFiles){
            if(file.isFile()){
                String name = file.getName();
                String url = fileUtil.fileBaseUrl + name;
                long size = files.length();
                int lastDotIndex = name.indexOf(".");
                String extension = name.substring(lastDotIndex +1);
                filesDto.add(new FileDto(name, url , extension ,size));
            }
        }
        return filesDto;
    }

    @Override
    public FileDto getFileByName(String fileName) {
        File files = new File(fileUtil.fileServerPath);
        File [] getFileByName = files.listFiles(((dir, name) -> name.equals(fileName)));
        if(getFileByName.length > 0){
            String name = files.getName();
            String url = fileUtil.fileBaseUrl + name;
            long size = files.length();
            int lastDotIndex = name.indexOf(".");
            String extension = name.substring(lastDotIndex +1);
            File file = getFileByName[0];
            return new FileDto(file.getName(), url,extension,size);
        }else{
            return null;
        }
    }

    @Override
    public boolean deleteFileByName(String fileName) {
        File files = new File(fileUtil.fileServerPath);
        File [] getFileByName = files.listFiles(((dir, name) -> name.equals(fileName)));
        if(getFileByName.length > 0){
            File file = getFileByName[0];
            return file.delete();
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteAllFile() {
        File files = new File(fileUtil.fileServerPath);
        File [] getFileByName = files.listFiles();
        boolean isSuccess = true;
        for (File file : getFileByName) {
            if(!file.delete()){
                isSuccess=false;
            }
        }
        return isSuccess;
    }

    @Override
    public FileDownloadDto downloadFileByName(String fileName){
        List<FileDownloadDto> downloads = new ArrayList<>();
        File files = new File(fileUtil.fileServerPath);
        File[] fileDownload = files.listFiles();
        for (File file : fileDownload) {
            if (file.isFile()) {
                String name = file.getName();
                String url = fileUtil.fileBaseUrl + name;
                String downloadUrl = fileUtil.fileBaseUrl + name;
                long size = file.length();
                int lastDotIndex = name.lastIndexOf(".");
                String extension = name.substring(lastDotIndex + 1);
                downloads.add(new FileDownloadDto(name, url, downloadUrl, extension, size));
                System.out.println(file);
            }
        }
        return null;
    }
}
