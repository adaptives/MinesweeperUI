import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	BoardTest.class,
	SquareTest.class
})
public class AllTests {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
