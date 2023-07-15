package board.file.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

// FileMapper Interface 
@Mapper
public interface FileMapper {

    // Create Image
    int createImage(List<Map<String, String>> imageList);

    // Delete Image
    int deleteImage(Long tno);

    // Update Image
    int updateImage(List<Map<String, String>> imageList);

    // Create Image Notice
    int createImageNotice(List<Map<String,String>> imageList);

    // Delete Image Notice
    int deleteImageNotice(Long nno);

    // Update Image Notice
    int updateImageNotice(List<Map<String,String>> imageList);
}
