package board.file;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

// DataBase Connection Check Class
@Log4j2
@SpringBootTest
class FileApplicationTests {
	
	// 의존성 주입 
	@Autowired
	private DataSource dataSource;

	// DataBase Connection Check 
	@Test
	public void dataConnectionTest() {
		try(Connection connection = dataSource.getConnection()) {
			log.info("Let's Do It");
		} catch (Exception e) {
			log.info("Find Your Errors Your Connection Is Not Ok");
		}
	}

}
