package com.user.booking.model;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * For Upload file
 *
 */
public class File {

	private MultipartFile file;

	/**
	 * @return the file
	 */
	public MultipartFile getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
