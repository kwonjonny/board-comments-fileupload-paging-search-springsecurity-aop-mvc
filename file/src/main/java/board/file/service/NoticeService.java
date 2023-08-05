package board.file.service;

import board.file.dto.notice.NoticeCreateDTO;
import board.file.dto.notice.NoticeDTO;
import board.file.dto.notice.NoticeListDTO;
import board.file.dto.notice.NoticeUpdateDTO;
import board.file.dto.page.PageRequestDTO;
import board.file.dto.page.PageResponseDTO;

// Notice Service Interface 
public interface NoticeService {
    
    // List Notice Service
    PageResponseDTO<NoticeListDTO> listNotice(PageRequestDTO pageRequestDTO);

    // Create Notice Service 
    Long createNotice(NoticeCreateDTO noticeCreateDTO);

    // Delete Notice Service
    void deleteNotice(Long nno);

    // Read Notice Service
    NoticeDTO readNotice(Long nno);

    // Count View Notice 
    void viewCount(Long nno);

    // Update Notice Service 
    Long updateNotice(NoticeUpdateDTO noticeUpdateDTO);

    // Not Found Nno 
    void notFoundNno(Long nno);
}
