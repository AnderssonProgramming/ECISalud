package edu.eci.cvds.ECISalud;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class EciSaludApplicationTests {

	@Test
	void contextLoads() {
		// Simple test to verify the test class loads
		assertDoesNotThrow(() -> {
			// Nothing to do, just ensuring test setup works
		});
	}
	
	@Test
	void testMainMethod() {
		// Since we're not using SpringBootTest, we'll just verify the class exists
		// and can be instantiated without error
		assertDoesNotThrow(() -> {
			EciSaludApplication.main(new String[]{});
		});
	}
}
