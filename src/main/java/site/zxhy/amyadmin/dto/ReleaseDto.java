package site.zxhy.amyadmin.dto;

import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Data
@ToString
public class ReleaseDto {
    private MultipartFile artifact;
    private String platform;
    private String arch;
    private String version;
    private String fileName;
    private String bucket;
}
