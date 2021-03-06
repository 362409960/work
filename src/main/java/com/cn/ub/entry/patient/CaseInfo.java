package com.cn.ub.entry.patient;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CaseInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private  String id;
	/**
	 * 病人姓名
	 */
	private String patientName;
	/**
	 * 病人号
	 */
	private String patinetNo;
	/**
	 * 医院
	 */
	private String hospital;
	/**
	 * 住院号
	 */
	private String hospitalNo;
	/**
	 * 检查部位
	 */
	private String checkParts;
	/**
	 * 检查类型
	 */
	private String checkType;
	/**
	 * 门诊号
	 */
	private String clinicNumber;
	/**
	 * 报告医生
	 */
	private String reportDoctor;
	/**
	 * 报告状态
	 */
	private String reportState;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 年龄
	 */
	private Integer age;
	/**
	 * 检查时间
	 */
	private Date checkTime; 
	/**
	 * 报告时间
	 */
	private Date reportTime;
	/**
	 * 申请时间
	 */
	private Date applicationTime;
	
	
	private String startTime;
	
	private String endDate;
	/**
	 * 图片总数
	 */
	private Integer countPic;
	
	private PatientPicture [] picId;
	
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private String checkTimeIng;
	
	private String reportTimeIng;
	
	private String applicationTimeIng;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getPatinetNo() {
		return patinetNo;
	}
	public void setPatinetNo(String patinetNo) {
		this.patinetNo = patinetNo;
	}
	public String getHospital() {
		return hospital;
	}
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	public String getHospitalNo() {
		return hospitalNo;
	}
	public void setHospitalNo(String hospitalNo) {
		this.hospitalNo = hospitalNo;
	}
	public String getCheckParts() {
		return checkParts;
	}
	public void setCheckParts(String checkParts) {
		this.checkParts = checkParts;
	}
	public String getCheckType() {
		return checkType;
	}
	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	public String getClinicNumber() {
		return clinicNumber;
	}
	public void setClinicNumber(String clinicNumber) {
		this.clinicNumber = clinicNumber;
	}
	public String getReportDoctor() {
		return reportDoctor;
	}
	public void setReportDoctor(String reportDoctor) {
		this.reportDoctor = reportDoctor;
	}
	public String getReportState() {
		return reportState;
	}
	public void setReportState(String reportState) {
		this.reportState = reportState;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Date getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Date checkTime) {
		this.checkTimeIng = dateFormat.format(checkTimeIng);
		this.checkTime = checkTime;
	}
	public Date getReportTime() {
		return reportTime;
	}
	public void setReportTime(Date reportTime) {
		this.reportTimeIng = dateFormat.format(reportTime);
		this.reportTime = reportTime;
	}
	public Date getApplicationTime() {
		return applicationTime;
	}
	public void setApplicationTime(Date applicationTime) {
		this.applicationTimeIng = dateFormat.format(applicationTime);
		this.applicationTime = applicationTime;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Integer getCountPic() {
		return countPic;
	}
	public void setCountPic(Integer countPic) {
		this.countPic = countPic;
	}
	public PatientPicture[] getPicId() {
		return picId;
	}
	public void setPicId(PatientPicture[] picId) {
		this.picId = picId;
	}
	public DateFormat getDateFormat() {
		return dateFormat;
	}
	public void setDateFormat(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}
	public String getCheckTimeIng() {
		return checkTimeIng;
	}
	public void setCheckTimeIng(String checkTimeIng) {
		this.checkTimeIng = checkTimeIng;
	}
	public String getReportTimeIng() {
		return reportTimeIng;
	}
	public void setReportTimeIng(String reportTimeIng) {
		this.reportTimeIng = reportTimeIng;
	}
	public String getApplicationTimeIng() {
		return applicationTimeIng;
	}
	public void setApplicationTimeIng(String applicationTimeIng) {
		this.applicationTimeIng = applicationTimeIng;
	}
	
	
	

}
