/*
 * Copyright 2014-2021 Web Firm Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.webfirmframework.wffweb.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

public class FileUtilTest {

	@Test
	public void testRemoveDirRecursively() throws IOException {

		String basePath = Paths.get(Files.createTempDirectory("testpath").toString(), "basePath").toString();
        Path path = Paths.get(basePath, "dir1", "dir2");
		Files.createDirectories(path);
		
		Path textFilePath = Paths.get(path.toString() + "/somefile.txt");
        Files.write(textFilePath, "somevalue".getBytes(StandardCharsets.UTF_8));
		
		assertEquals("somevalue", new String(Files.readAllBytes(textFilePath), StandardCharsets.UTF_8));
		
		String baseDir = basePath.substring(0, basePath.length() - "basePath".length());

		boolean removed = FileUtil.removeDirRecursively(baseDir, "basePath");
		
		assertTrue(removed);
		
		assertTrue(textFilePath.toString().endsWith("/dir1/dir2/somefile.txt"));
		
		try (Stream<Path> list = Files.list(Path.of(baseDir));) {
		    List<Path> filesUnderBaseDir = list.toList();
		    assertEquals(0, filesUnderBaseDir.size());
		}
		
		try {
		    Files.readAllBytes(textFilePath);
		    fail("File is not deleted");
		} catch (NoSuchFileException e) {
            //NOP
        }
		
	}

}
