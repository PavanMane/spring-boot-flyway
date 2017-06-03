package org.p1.flyway;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseSpringTest {
	
	@Test
	public void test() {
		System.out.println("Success....");
	}
	
}
