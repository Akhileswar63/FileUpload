package com.project.file.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.file.entity.FilesEntity;
import com.project.file.repo.FilesRepo;

@Service
public class FilesServiceImp implements FilesService 
{
	@Autowired
	private FilesRepo filesRepo;

	@Override
	public FilesEntity addFiles(FilesEntity filesEntity) 
	{
		return filesRepo.save(filesEntity);
	}

	@Override
	public FilesEntity updateFiles(Long id, FilesEntity filesEntity) 
	{
		Optional<FilesEntity> createOptional=filesRepo.findById(id);
		if(createOptional.isPresent())
		{
			filesEntity.setId(id);
			return filesRepo.save(filesEntity);
		}
		 throw new RuntimeException("Files not found with id: " + id);
	}

	@Override
	public List<FilesEntity> getAllFiles() 
	{
		return filesRepo.findAll();
	}

	@Override
	public FilesEntity getFilesById(Long id) 
	{
		Optional<FilesEntity> createOptional=filesRepo.findById(id);
		return createOptional.orElse(null);
	}

	@Override
	public void deleteFiles(Long id) 
	{
		filesRepo.deleteById(id);
	}

}
