package board.file.mappers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

// MyBatis Time Check Class 
@Log4j2
@SpringBootTest
public class TimeCheckTests {
    
    // 의존성 주입 
    @Autowired
    private TimeCheck timeCheck;

    @Test
    @DisplayName("MyBatis Time Check & Connection Check")
    public void timeCheckTest() {
        log.info("===== Start Time Check =====");
        log.info(timeCheck.getTime());
        log.info("==== End Time Check =====");
    }
}
