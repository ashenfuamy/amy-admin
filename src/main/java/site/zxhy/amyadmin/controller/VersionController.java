package site.zxhy.amyadmin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import site.zxhy.amyadmin.config.bean.DirectoryProps;
import site.zxhy.amyadmin.dto.ReleaseDto;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/version")
@RequiredArgsConstructor
public class VersionController {

    private final DirectoryProps directoryProps;

    @PostMapping("/release")
    @PreAuthorize("hasAuthority('RELEASE')")
    public void release(ReleaseDto releaseDto) throws IOException {
        String arch = releaseDto.getArch();
        String platform = releaseDto.getPlatform();
        MultipartFile artifact = releaseDto.getArtifact();

        System.out.println(directoryProps);

        File bucketFile = new File(directoryProps.getRelease(), releaseDto.getBucket());
        if (!bucketFile.exists()) bucketFile.mkdir();

        File platformFile = new File(bucketFile, platform);
        if (!platformFile.exists()) platformFile.mkdir();

        File archRoot = new File(platformFile, arch);
        if (!archRoot.exists()) archRoot.mkdir();

        File artifactFile = new File(archRoot, releaseDto.getFileName());

        artifact.transferTo(artifactFile);
    }
}
