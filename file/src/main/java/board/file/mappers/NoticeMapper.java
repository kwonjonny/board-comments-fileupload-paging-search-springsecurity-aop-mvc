package board.file.mappers;

import org.apache.ibatis.annotations.Mapper;

import board.file.dto.notice.NoticeCreateDTO;
import board.file.dto.notice.NoticeDTO;
import board.file.dto.notice.NoticeUpdateDTO;

// Notice Mapper Interface
@Mapper
public interface NoticeMapper {
    
    // Create Notice 
    int createNotice(NoticeCreateDTO noticeCreateDTO);

    // Update Notice 
    int updateNotice(NoticeUpdateDTO noticeUpdateDTO);

    // Delete Notice 
    int deleteNotice(Long nno);

    // Read Notice 
    NoticeDTO readNotice(Long nno);

    // List Notice 
    


}
