package java.com.epam.spring.hometask.domain;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.epam.spring.hometask.domain.Auditorium;
import org.junit.Test;

/**
 * @author Volkov_Mikhail
 */
public class TestAuditorium {
	
	@Test
	public void testCountVips() {
		Auditorium a = new Auditorium();
		a.setVipSeats(Stream.of(1L,2L,3L).collect(Collectors.toSet()));
		assertEquals(0, a.countVipSeats(Arrays.asList(10L, 20L, 30L)));
		assertEquals(1, a.countVipSeats(Arrays.asList(10L, 2L, 30L)));
		assertEquals(2, a.countVipSeats(Arrays.asList(10L, 2L, 3L, 4L, 5L, 6L)));
	}
	
	public void testGetAllSeats() {
	    Auditorium a = new Auditorium();
	    a.setNumberOfSeats(10);
	    assertEquals(10, a.getAllSeats().size());
	}

}