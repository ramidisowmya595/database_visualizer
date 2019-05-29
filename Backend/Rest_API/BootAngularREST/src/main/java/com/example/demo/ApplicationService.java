package com.example.demo;

import java.awt.PageAttributes.MediaType;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {
	@Autowired
	private InsertdbRepository insertdbRepository;

	public ResponseEntity<byte[]> gettype() throws IOException {
		Insertdb requestData = insertdbRepository.findResource(1);
		String imagePath = requestData.getType_nn();
		RandomAccessFile f = new RandomAccessFile(imagePath, "r");
		byte[] b = new byte[(int) f.length()];
		f.readFully(b);
		final HttpHeaders headers = new HttpHeaders();
		// headers.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity<byte[]>(b, headers, HttpStatus.CREATED);
	}

	public ResponseEntity<byte[]> getschema() throws IOException {
		Insertdb requestData = insertdbRepository.findResource(1);
		String s = requestData.getSchema_nn();
		RandomAccessFile f = new RandomAccessFile(s, "r");
		byte[] b = new byte[(int) f.length()];
		f.readFully(b);
		final HttpHeaders headers = new HttpHeaders();
		// headers.setContentType(MediaType.TEXT_PLAIN);
		return new ResponseEntity<byte[]>(b, headers, HttpStatus.CREATED);
	}
	public ResponseEntity<byte[]> gettable() throws IOException {
		Insertdb requestData = insertdbRepository.findResource(1);
		String imagePath = requestData.getTable_nn();
		RandomAccessFile f = new RandomAccessFile(imagePath, "r");
		byte[] b = new byte[(int) f.length()];
		f.readFully(b);
		final HttpHeaders headers = new HttpHeaders();
		// headers.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity<byte[]>(b, headers, HttpStatus.CREATED);
	}

	public ResponseEntity<byte[]> gecolumn() throws IOException {
		Insertdb requestData = insertdbRepository.findResource(1);
		String s = requestData.getColumn_nn();
		RandomAccessFile f = new RandomAccessFile(s, "r");
		byte[] b = new byte[(int) f.length()];
		f.readFully(b);
		final HttpHeaders headers = new HttpHeaders();
		// headers.setContentType(MediaType.TEXT_PLAIN);
		return new ResponseEntity<byte[]>(b, headers, HttpStatus.CREATED);
	}

	public ResponseEntity<byte[]> getImageURL() {
		// TODO Auto-generated method stub
		return new ResponseEntity("",HttpStatus.ACCEPTED);
	}
}