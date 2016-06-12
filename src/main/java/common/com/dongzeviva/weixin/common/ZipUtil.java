package com.dongzeviva.weixin.common;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;


public class ZipUtil {
	public synchronized static boolean packageZip(String folder) throws IOException {
		if (StringUtils.isBlank(folder)) {
			return false;
		}
		String zipName = folder + ".zip";
		File zipFile = new File(zipName);
		if (zipFile.exists()) {
			return true;
		}
		File file = new File(folder);
		if (!file.exists()) {
			throw new IOException(file.getAbsolutePath() + " not found.");
		}
		Project prj = new Project();
		Zip zip = new Zip();
		zip.setProject(prj);
		zip.setDestFile(zipFile);
		FileSet fileSet = new FileSet();
		fileSet.setProject(prj);
		fileSet.setDir(file);
		zip.addFileset(fileSet);
		zip.execute();
		return true;
	}
}
