package com.project.file.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class FilesEntity 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Lob
    @Column(name = "aadhaar_file", columnDefinition = "LONGBLOB")
    private byte[] aadhaarFile;
	private String aadhaarPath;
	@Lob
    @Column(name = "panCard_file", columnDefinition = "LONGBLOB")
    private byte[] panCardFile;
	private String panCardPath;
	@Lob
    @Column(name = "companyForm_file", columnDefinition = "LONGBLOB")
    private byte[] companyFormFile;
	private String companyFormPath;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public byte[] getAadhaarFile() {
		return aadhaarFile;
	}
	public void setAadhaarFile(byte[] aadhaarFile) {
		this.aadhaarFile = aadhaarFile;
	}
	public String getAadhaarPath() {
		return aadhaarPath;
	}
	public void setAadhaarPath(String aadhaarPath) {
		this.aadhaarPath = aadhaarPath;
	}
	public byte[] getPanCardFile() {
		return panCardFile;
	}
	public void setPanCardFile(byte[] panCardFile) {
		this.panCardFile = panCardFile;
	}
	public String getPanCardPath() {
		return panCardPath;
	}
	public void setPanCardPath(String panCardPath) {
		this.panCardPath = panCardPath;
	}
	public byte[] getCompanyFormFile() {
		return companyFormFile;
	}
	public void setCompanyFormFile(byte[] companyFormFile) {
		this.companyFormFile = companyFormFile;
	}
	public String getCompanyFormPath() {
		return companyFormPath;
	}
	public void setCompanyFormPath(String companyFormPath) {
		this.companyFormPath = companyFormPath;
	}
	
}
