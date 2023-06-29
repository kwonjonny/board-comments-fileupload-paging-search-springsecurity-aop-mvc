package board.file.mappers;

import org.apache.ibatis.annotations.Mapper;

// MyBatis Time Check Class 
@Mapper
public interface TimeCheck {
    String getTime();
}
