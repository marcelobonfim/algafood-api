package com.algaworks.algafood.infrastructure.service.storage;

import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;

import com.algaworks.algafood.core.storage.StorageProperties;
import com.algaworks.algafood.domain.model.dto.FotoRecuperada;
import com.algaworks.algafood.domain.model.dto.NovaFoto;
import com.algaworks.algafood.domain.repository.FotoStorageService;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class S3FotoStorageService implements FotoStorageService {
	
	@Autowired
	private AmazonS3 amazonS3;
	
	@Autowired
	private StorageProperties storageProperties;

	@Override
	public void armazenar(NovaFoto novaFoto) {
		try {
			String caminhoArquivo = getCamimhoArquivo(novaFoto.getNomeArquivo());
			
			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentType(novaFoto.getContentType());
			
			
			var putObjectRequest = new PutObjectRequest(
					storageProperties.getS3().getBucket(), 
					caminhoArquivo, 
					novaFoto.getInputStream(), 
					objectMetadata)
				.withCannedAcl(CannedAccessControlList.PublicRead);
			
			amazonS3.putObject(putObjectRequest);
		} catch (Exception e) {
			throw new StorageException("Não foi possível armazenar o arquivo no Amazon S3", e);
		}
	}


	@Override
	public void remover(String nomeArquivo) {
		try {
			String camimhoArquivo = getCamimhoArquivo(nomeArquivo);
			DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(storageProperties.getS3().getBucket(), camimhoArquivo);
			amazonS3.deleteObject(deleteObjectRequest);
		} catch (Exception e) {
			throw new StorageException("Não foi possível remover o arquivo no Amazon S3", e);
		}
		
	}

	@Override
	public FotoRecuperada recuperar(String nomeArquivo) {
		String camimhoArquivo = getCamimhoArquivo(nomeArquivo);
		URL url = amazonS3.getUrl(storageProperties.getS3().getBucket(), camimhoArquivo);
		
		return FotoRecuperada.builder().url(url.toString()).build();
	}
	
	private String getCamimhoArquivo(String nomeArquivo) {
		return String.format("%s/%s", storageProperties.getS3().getDiretorioFotos(), nomeArquivo);
	}
}
