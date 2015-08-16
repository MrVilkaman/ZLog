package ru.zolotarev.zlog;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LogWriterTest {

	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();

	@Test
	public void testSimplePrint() throws IOException {
		File file = temporaryFolder.newFile();
		final String res = "Test string\n";
		final String str = "Test string";

		FileUtils.print(file, str);
		String readStr = readFile(file);
		Assert.assertEquals(res,readStr);
	}

	@Test
	public void testReOpenPrint() throws IOException {
		File file = temporaryFolder.newFile();
		final String res = "Test string\nTest string\nTest string\n";
		final String str = "Test string";

		FileUtils.print(file, str);
		FileUtils.print(file, str);
		FileUtils.print(file, str);
		String readStr = readFile(file);
		Assert.assertEquals(res,readStr);
	}

	private String readFile(File file) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader in = new BufferedReader(new FileReader(file));
		try {
			String s;
			while ((s = in.readLine()) != null) {
				sb.append(s);
				sb.append("\n");
			}
		} finally {
			in.close();
		}

		return sb.toString();
	}
}