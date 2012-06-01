package com.diycomputerscience.minesweepercore;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.diycomputerscience.minesweepercore.FileConnectionFactory;


public class MockFileConnectionFactory implements FileConnectionFactory {

	private BufferedReader reader;
	private PrintWriter writer;
	
	public MockFileConnectionFactory(BufferedReader reader,
									 PrintWriter writer) {
		this.reader = reader;
		this.writer = writer;
	}
	
	@Override
	public PrintWriter getWriter() throws IOException {
		return this.writer;
	}

	@Override
	public BufferedReader getReader() throws IOException {
		return this.reader;
	}

}
