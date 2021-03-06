package com.jackson.cursomc.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.jackson.cursomc.services.exception.FileException;

@Service
public class S3Service {

	private Logger LOG = LoggerFactory.getLogger(S3Service.class);

	@Autowired
	private AmazonS3 s3cliente;

	@Value("${s3.bucket}")
	public String bucketName;

	public URI uplodFile(MultipartFile multipartFile) {
		try {
			String fileName = multipartFile.getOriginalFilename();
			InputStream is;
			is = multipartFile.getInputStream();
			String contentType = multipartFile.getContentType();
			return uplodFile(is, fileName, contentType);
		} catch (IOException e) {
			throw new FileException("erro de IO: " + e.getMessage());
		}

	}

	public URI uplodFile(InputStream is, String fileName, String contentType) {
		try {
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentType(contentType);
			LOG.info("iniciando upload");
			s3cliente.putObject(bucketName, fileName, is, meta);
			LOG.info("upload finalizado");

			return s3cliente.getUrl(bucketName, fileName).toURI();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			throw new FileException("Erro ao converter URL em URI");
		}
	}
}
