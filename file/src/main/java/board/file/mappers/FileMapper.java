package board.file.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {
    
    // Create Image 
    int createImage(List<Map<String,String>> imageList);

    // Delete Image 
    int deleteImage(String fileName);

    // Update Image
    int updateImage(List<Map<String,String>> imageList);

    // Read Image 

}
