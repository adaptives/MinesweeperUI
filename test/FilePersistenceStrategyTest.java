import static org.easymock.EasyMock.capture;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.easymock.Capture;
import org.easymock.CaptureType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class FilePersistenceStrategyTest {

	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLoadRegexForNoMatch() {
		Pattern regexPattern = Pattern.compile(FilePersistenceStrategy.SQUARE_LOAD_REGEX);
		
		Matcher matcher = regexPattern.matcher("");
		assertFalse(matcher.matches());
		
		matcher = regexPattern.matcher("0,0:true-COVERED");
		assertTrue(matcher.matches());
	}
	
	@Test
	public void testLoadRegexForMatch() {
		Pattern regexPattern = Pattern.compile(FilePersistenceStrategy.SQUARE_LOAD_REGEX);
		
		Matcher matcher = regexPattern.matcher("0,0:true-COVERED");
		assertTrue(matcher.matches());
	}	
	
	@Test
	public void testLoadRegexForMatchingGroups() {
		Pattern regexPattern = Pattern.compile(FilePersistenceStrategy.SQUARE_LOAD_REGEX);
		
		Matcher matcher = regexPattern.matcher("0,0:true-COVERED");
		assertEquals(4, matcher.groupCount());
		
		assertTrue(matcher.find());
		assertEquals("0", matcher.group(1));
		assertEquals("0", matcher.group(2));
		assertEquals("true", matcher.group(3));
		assertEquals("COVERED", matcher.group(4));
	}
	
	@Test
	public void testSave() throws Exception {
		
		PrintWriter writer = createMock(PrintWriter.class);
		Capture<String> captureOfString = new Capture<String>(CaptureType.ALL);
		writer.println(capture(captureOfString));
		//Note: It is last call 36 times, and NOT last call + 36 times
		expectLastCall().times(36);
		replay(writer);
		
		PersistenceStrategy persistence = new FilePersistenceStrategy();
		persistence.save(MockFileSystemConnectionFactory.expectedSquares);
		verify(writer);
		List<String> squaresAsText = captureOfString.getValues();
		for(int i=0; i<MockFileSystemConnectionFactory.expectedSquaresAsText.length; i++) {
			assertEquals(MockFileSystemConnectionFactory.expectedSquaresAsText[i], squaresAsText.get(i));
		}
	}
	
	@Test
	public void testLoad() throws Exception {
		BufferedReader reader = createMock(BufferedReader.class);
		for(String line : MockFileSystemConnectionFactory.expectedSquaresAsText) {
			expect(reader.readLine()).andReturn(line);
		}
		expect(reader.readLine()).andReturn(null);
		replay(reader);
		
		PersistenceStrategy persistence = new FilePersistenceStrategy();
		Square squares[][] = persistence.load();
		assertNotNull(squares);
	}
	
	


}
