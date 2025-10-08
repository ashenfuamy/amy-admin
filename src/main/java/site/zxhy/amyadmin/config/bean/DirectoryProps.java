package site.zxhy.amyadmin.config.bean;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("directory")
@ToString
public class DirectoryProps {
    private String release;
}
