package com.pharmacy.entity;


import com.pharmacy.Request.AddDocumentRequest;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class Document {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String docExtension;
	@Lob
	private byte document[];
	@ManyToOne
    @JoinColumn(name = "document_type_id")
    private DocumentType documentType;
	private String remark;
	private boolean verified;
	
	public Document() {
	}


	public Document(AddDocumentRequest docRequest, DocumentType docType) {
		this.docExtension = docRequest.getDocExtension();
		this.document = docRequest.getDocument();
		this.documentType = docType;
		this.remark = docRequest.getRemarkk();
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getDocExtension() {
		return docExtension;
	}


	public void setDocExtension(String docExtension) {
		this.docExtension = docExtension;
	}


	public byte[] getDocument() {
		return document;
	}


	public void setDocument(byte[] document) {
		this.document = document;
	}


	public DocumentType getDocumentType() {
		return documentType;
	}


	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

}
