package co.istad.mbanking.file;

import lombok.Builder;

@Builder
public record FileDownloadDto(String fileName ,
                              String url,
                              String extension,
                              String downloadUrl,
                              long size) {
}
