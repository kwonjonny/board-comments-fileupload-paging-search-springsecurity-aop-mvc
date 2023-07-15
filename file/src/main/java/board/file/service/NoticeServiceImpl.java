package board.file.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import board.file.dto.notice.NoticeCreateDTO;
import board.file.dto.notice.NoticeDTO;
import board.file.dto.notice.NoticeListDTO;
import board.file.dto.notice.NoticeUpdateDTO;
import board.file.dto.page.PageRequestDTO;
import board.file.dto.page.PageResponseDTO;
import board.file.mappers.FileMapper;
import board.file.mappers.NoticeMapper;
import lombok.extern.log4j.Log4j2;

// Notice ServiceImpl Class
@Log4j2
@Service
public class NoticeServiceImpl implements NoitceService {

    // 의존성 주입
    private final NoticeMapper noticeMapper;
    private final FileMapper fileMapper;

    // Autowired 명시적 표시 
    @Autowired
    public NoticeServiceImpl(NoticeMapper noticeMapper, FileMapper fileMapper) {
        this.noticeMapper = noticeMapper;
        this.fileMapper = fileMapper;
    }

    // List NoticeServiceImpl
    @Override
    @Transactional(readOnly = true)
    public PageResponseDTO<NoticeListDTO> listNotice(PageRequestDTO pageRequestDTO) {
        log.info("List NoticeServiceImpl Is Running");
        List<NoticeListDTO> list = noticeMapper.listNotice(pageRequestDTO);
        int total = noticeMapper.total(pageRequestDTO);
        return PageResponseDTO.<NoticeListDTO>withAll()
                .list(list)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }

    // Create NoticeServiceImpl
    @Override
    @Transactional
    public Long createNotice(NoticeCreateDTO noticeCreateDTO) {
        log.info("Create NoticeServiceImpl Is Running");
        int count = noticeMapper.createNotice(noticeCreateDTO);
        AtomicInteger index = new AtomicInteger(0);
        List<String> fileNames = noticeCreateDTO.getFileNames();
        Long nno = noticeCreateDTO.getNno();

        if (noticeCreateDTO.getFileNames() != null && !noticeCreateDTO.getFileNames().isEmpty()) {
            List<Map<String, String>> list = fileNames.stream().map(str -> {
                String uuid = str.substring(0, 36);
                String fileName = str.substring(37);
                return Map.of("uuid", uuid, "fileName", fileName, "nno", "" + nno, "ord", "" + index.getAndIncrement());
            }).collect(Collectors.toList());
            fileMapper.createImageNotice(list);
        }
        return noticeCreateDTO.getNno();
    }

    // Update NoticeServiceImpl
    @Override
    @Transactional
    public Long updateNotice(NoticeUpdateDTO noticeUpdateDTO) {
        log.info("Update NoticeServiceImpl Is Running");
        int count = noticeMapper.updateNotice(noticeUpdateDTO);
        fileMapper.deleteImageNotice(noticeUpdateDTO.getNno());
        AtomicInteger index = new AtomicInteger(0);
        List<String> fileNames = noticeUpdateDTO.getFileNames();
        Long nno = noticeUpdateDTO.getNno();

        if(noticeUpdateDTO.getFileNames() != null && !noticeUpdateDTO.getFileNames().isEmpty()) {
            List<Map<String,String>> list = fileNames.stream().map(str -> {
                String uuid = str.substring(0, 36);
                String fileName = str.substring(37);
                return Map.of("uuid", uuid, "fileName", fileName, "nno", "" + nno, "ord", "" + index.getAndIncrement());
            }).collect(Collectors.toList());
            fileMapper.updateImageNotice(list);
        }
        return noticeUpdateDTO.getNno();
    }

    // Delete NoticeServiceImpl
    @Override
    @Transactional
    public void deleteNotice(Long nno) {
        log.info("Delete NoticeServiceImpl Is Running");
        noticeMapper.deleteNotice(nno);
    }

    // Read NoticeServiceImpl
    @Override
    @Transactional(readOnly = true)
    public NoticeDTO readNotice(Long nno) {
        log.info("Read NoticeServiceImpl Is Running");
        return noticeMapper.readNotice(nno);
    }

    // View Count NoticeServiceImpl
    @Override
    @Transactional
    public void viewCount(Long nno) {
        log.info("ViewCount NoticeServiceImpl Is Running");
        noticeMapper.viewCount(nno);
    }
}
