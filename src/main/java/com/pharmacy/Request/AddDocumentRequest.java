package com.pharmacy.Request;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class AddDocumentRequest {
	
	private String docExtension;
	private byte[] document;
	private Integer documentTypeId;
	private String remark;
	
	private boolean verified;
	
	
	public AddDocumentRequest(Map<String, Object> data) {
		this.docExtension = (String) data.get("docExtension");
		this.document =  ((String) data.get("document")).getBytes(StandardCharsets.UTF_8);
		this.documentTypeId = (Integer)data.get("documentTypeId");
		this.remark = (String)data.get("remark");
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
	public Integer getDocumentId() {
		return documentTypeId;
	}
	public void setDocumentId(Integer documentId) {
		this.documentTypeId = documentId;
	}
	public String getRemarkk() {
		return remark;
	}
	public void setRemarkk(String remarkk) {
		this.remark = remarkk;
	}
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	public Integer getDocumentTypeId() {
		return documentTypeId;
	}
	public void setDocumentTypeId(Integer documentTypeId) {
		this.documentTypeId = documentTypeId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	

}
