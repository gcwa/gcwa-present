package ca.gc.collectionscanada;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;

import ca.gc.collectionscanada.gcwa.GCWebArchiveApplication;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GCWebArchiveApplication.class)
@WebAppConfiguration
public class GcwebarchiveApplicationTests {

	@Test
	public void contextLoads() {
	}

}
