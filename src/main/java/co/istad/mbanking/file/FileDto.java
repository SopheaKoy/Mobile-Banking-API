package co.istad.mbanking.file;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
public record FileDto(String fileName ,
                      String url,
                      String extension,
                      long size) {
}
