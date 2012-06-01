import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	BoardTest.class,
	SquareTest.class,
	FilePersistenceStrategyTest.class
})
public class AllTests {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
