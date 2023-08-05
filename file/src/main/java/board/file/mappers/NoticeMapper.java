package board.file.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import board.file.dto.notice.NoticeCreateDTO;
import board.file.dto.notice.NoticeDTO;
import board.file.dto.notice.NoticeListDTO;
import board.file.dto.notice.NoticeUpdateDTO;
import board.file.dto.page.PageRequestDTO;

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
    List<NoticeListDTO> listNotice(PageRequestDTO pageRequestDTO);

    // total
    int total(PageRequestDTO pageRequestDTO);

    // View Count 
    int viewCount(Long nno);

    // Like Count 
    int likeCount(Long nno);

    // Check Nno 
    int checkNno(Long nno);
}
