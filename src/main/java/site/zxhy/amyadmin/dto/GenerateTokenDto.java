package site.zxhy.amyadmin.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class GenerateTokenDto {
    private List<String> permissions;
}
