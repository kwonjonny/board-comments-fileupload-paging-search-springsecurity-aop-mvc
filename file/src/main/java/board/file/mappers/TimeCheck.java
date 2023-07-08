package board.file.mappers;

import org.apache.ibatis.annotations.Mapper;

// MyBatis Time Check Interface 
@Mapper
public interface TimeCheck {
    String getTime();
}
