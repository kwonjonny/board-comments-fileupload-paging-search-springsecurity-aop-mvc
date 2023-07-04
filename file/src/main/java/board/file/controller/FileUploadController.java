package board.file.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import board.file.dto.File.UploadResultDTO;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;

@Log4j2
@RestController
public class FileUploadController {

    // File Upload Path = Enginx
    @Value("${org.zerock.upload.path}")
    private String uploadPath;

    @PostMapping("/upload")
    public List<UploadResultDTO> postFileUpload(MultipartFile[] files) {
        if(files == null || files.length == 0) {
            return null;
        }
        List<UploadResultDTO> resultList = new ArrayList<>();
        for(MultipartFile file : files) {
            UploadResultDTO result = null;
            String fileNmae = file.getOriginalFilename();
            Long size = file.getSize();
            String uuidStr = UUID.randomUUID().toString();
            String saveFileName = uuidStr+"_"+fileNmae;
            File saveFile = new File(uploadPath, saveFileName);
            try {
                FileCopyUtils.copy(file.getBytes(), saveFile);
                result = UploadResultDTO.builder().uuid(uuidStr).fileName(fileNmae).build();
                // Values Check Img 
                String mineType = Files.probeContentType(saveFile.toPath());
                if(mineType.startsWith("image")) {
                    File thumFile = new File(uploadPath, "s_"+saveFileName);
                    Thumbnailator.createThumbnail(saveFile, thumFile, 100, 100);
                    result.setImg(true);
                }
                resultList.add(result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    } 
    
}
