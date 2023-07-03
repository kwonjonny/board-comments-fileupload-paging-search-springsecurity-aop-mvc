package board.file.controller;

import java.io.File;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import board.file.dto.file.FileCreateDTO;
import board.file.dto.file.FileListDTO;
import board.file.dto.file.FileUploadResultDTO;
import board.file.service.FileService;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;

@Log4j2
@RestController
public class FileController {
    
    // 의존성 주입 
    private final FileService fileService;

    @Value("${org.zerock.upload.path}")
    private String uploadPath;

    // Autowired 명시적 표시 
    @Autowired
    public FileController(FileService fileService) {
        log.info("Constrctor Called, Service Injected.");
        this.fileService = fileService;
    }

    // Delete File 
    @DeleteMapping("{imgId}")
    public ResponseEntity<Map<String, String>> deleteFile(@PathVariable("imgId") Long imgId) {
        log.info("RestConroller : Is Running Delete File");
        fileService.deleteFile(imgId);
        return new ResponseEntity<>(Map.of("message", "삭제가 완료되었습니다."), HttpStatus.NO_CONTENT);
    }

    // Read File
    @GetMapping("{tno}")
    public ResponseEntity<FileListDTO> getListFile(@PathVariable("tno") Long tno) {
        log.info("RestController : Is Running Read File");
        FileListDTO readFile = fileService.readListFile(tno);
          return new ResponseEntity<>(readFile, HttpStatus.OK);
    }
    
    // // Create File 
    // @PostMapping("/upload")
    // public List<FileUploadResultDTO> postCreateFile(MultipartFile[] multipartFiles) {
    //     if(multipartFiles == null || multipartFiles.length ==0){
    //         return null;
    //     }
    //     List<FileUploadResultDTO> resultList = new ArrayList<>();
    //     for(MultipartFile file : multipartFiles) {
    //         FileUploadResultDTO result = null;
    //         String fileName = file.getOriginalFilename();
    //         log.info(fileName);

    //         Long size = file.getSize();
    //         String uuidStr = UUID.randomUUID().toString();
    //         String saveFileName = uuidStr+"_"+fileName;
    //         File savFile = new File(uploadPath, saveFileName);

    //         try {
    //             FileCopyUtils.copy(file.getBytes(), savFile);
    //             result = FileUploadResultDTO.builder()
    //             .uuid(uuidStr)
    //             .fileName(fileName)
    //             .build();
    //             // 이미지 파일 여부 확인
    //             String mimeType = Files.probeContentType(savFile.toPath());
    //             log.info(mimeType);

    //             if(mimeType.startsWith("image")){
    //                 // 업로드 성공시 썸네일 생성 
    //                 File thumbFile = new File(uploadPath, "s_"+saveFileName);
    //                Thumbnailator.createThumbnail(savFile, thumbFile, 100, 100);
    //                result.setImg(true);
    //             } // end if
    //             resultList.add(result);

    //         } catch (Exception e) {
    //             e.printStackTrace();
    //         }
    //     }
    //      // 생성된 UUID 값을 파일 서비스로 전달
    //     List<String> uuidList = resultList.stream()
    //         .map(FileUploadResultDTO::getUuid)
    //         .collect(Collectors.toList());
    //     fileService.createFile(uuidList);
    //     return resultList;
    // }

 @GetMapping("/view/{fileName}")
  public ResponseEntity<Resource> viewFileGET(@PathVariable String fileName){

      Resource resource = new FileSystemResource(uploadPath+File.separator + fileName);
      String resourceName = resource.getFilename();
      HttpHeaders headers = new HttpHeaders();

      try{
          headers.add("Content-Type", Files.probeContentType( resource.getFile().toPath() ));
      } catch(Exception e){
          return ResponseEntity.internalServerError().build();
      }
      return ResponseEntity.ok().headers(headers).body(resource);
  }


}
