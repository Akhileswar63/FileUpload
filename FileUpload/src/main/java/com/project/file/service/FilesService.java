package com.project.file.service;

import java.util.List;

import com.project.file.entity.FilesEntity;

public interface FilesService 
{	
	public FilesEntity addFiles(FilesEntity filesEntity);
	
	public FilesEntity updateFiles(Long id,FilesEntity filesEntity);
	
	List<FilesEntity> getAllFiles();
	
	FilesEntity getFilesById(Long id);
	
	void deleteFiles(Long id);
}
