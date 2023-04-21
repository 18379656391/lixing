package com.lixing.lixingdemo.mybatis.po;


import java.math.BigDecimal;
import java.util.Date;


/**
 * @Version: 1.0
 * @author codeGenerator
 * @date Jul 1, 2022 11:14:01 AM
 */
public class PposBaseManginstPo {

	private static final long serialVersionUID = 1L;
	/**
	 * alias
	 */
	public static final String TABLE_ALIAS = "ppos_base_manginst";
	/**
	 * column alias
	 */
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_INST_ID = "INST_ID";
	public static final String ALIAS_INST_NAME = "INST_NAME";
	public static final String ALIAS_ORG_CODE = "ORG_CODE";
	public static final String ALIAS_MANAGER_TYPE = "MANAGER_TYPE";
	public static final String ALIAS_MANAGER_PRI_CODE = "MANAGER_PRI_CODE";
	public static final String ALIAS_MANAGER_TA_CODE = "MANAGER_TA_CODE";
	public static final String ALIAS_MANAGER_DUTY = "MANAGER_DUTY";
	public static final String ALIAS_MANAGER_PROMISE = "MANAGER_PROMISE";
	public static final String ALIAS_MANAGE_FUND_DUTY = "MANAGE_FUND_DUTY";
	public static final String ALIAS_MANAGE_OTHER_TYPE = "MANAGE_OTHER_TYPE";
	public static final String ALIAS_MANAGE_ASSET_SCALE = "MANAGE_ASSET_SCALE";
	public static final String ALIAS_VIP_TYPE = "VIP_TYPE";
	public static final String ALIAS_PRIVATE_MANAGER_TYPE = "PRIVATE_MANAGER_TYPE";
	public static final String ALIAS_INVESTMENT_SCOPE = "INVESTMENT_SCOPE";
	public static final String ALIAS_TAXPAYER_IDNO = "TAXPAYER_IDNO";
	public static final String ALIAS_TAXPAY_FREQUENCY = "TAXPAY_FREQUENCY";
	public static final String ALIAS_TAXPAYER_TYPE = "TAXPAYER_TYPE";
	public static final String ALIAS_TAXOFFICE_LOCATION = "TAXOFFICE_LOCATION";
	public static final String ALIAS_AMAC_MEMBER = "AMAC_MEMBER";
	public static final String ALIAS_SPECIAL_INFO = "SPECIAL_INFO";
	public static final String ALIAS_OTHER_SPECIAL_REAMRK = "OTHER_SPECIAL_REAMRK";
	public static final String ALIAS_MEMBER_NO_SHSE = "MEMBER_NO_SHSE";
	public static final String ALIAS_MEMBER_NAME_SHSE = "MEMBER_NAME_SHSE";
	public static final String ALIAS_MEMBER_NO_SZSE = "MEMBER_NO_SZSE";
	public static final String ALIAS_MEMBER_NAME_SZSE = "MEMBER_NAME_SZSE";
	public static final String ALIAS_FRONT_END_RISK = "FRONT_END_RISK";
	public static final String ALIAS_NATURE_ISSUER = "NATURE_ISSUER";
	public static final String ALIAS_MANAGER_RAISE_NO = "MANAGER_RAISE_NO";
	public static final String ALIAS_ACCESS_LEVEL = "ACCESS_LEVEL";
	public static final String ALIAS_ACCESS_LEVEL_DETAIL = "ACCESS_LEVEL_DETAIL";
	public static final String ALIAS_DATA_SOURCE = "DATA_SOURCE";
	public static final String ALIAS_CREATOR = "CREATOR";
	public static final String ALIAS_MODIFIER = "MODIFIER";
	public static final String ALIAS_GMT_CREATE = "GMT_CREATE";
	public static final String ALIAS_GMT_MODIFIED = "GMT_MODIFIED";
	public static final String ALIAS_INST_SHORT_NAME = "INST_SHORT_NAME";
	public static final String ALIAS_BUSINESS_LICENSE = "BUSINESS_LICENSE";
	public static final String ALIAS_LICENSE_EFFECTIVE_DATE_TYPE = "LICENSE_EFFECTIVE_DATE_TYPE";
	public static final String ALIAS_LICENSE_EFFECTIVE_DATE = "LICENSE_EFFECTIVE_DATE";
	public static final String ALIAS_REG_ADDRESS = "REG_ADDRESS";
	public static final String ALIAS_MANAGER_CODE = "MANAGER_CODE";
	public static final String ALIAS_CITIZENSHIP = "CITIZENSHIP";
	public static final String ALIAS_CONTACT = "CONTACT";
	public static final String ALIAS_CERTIFICATE_TYPE = "CERTIFICATE_TYPE";
	public static final String ALIAS_CERTIFICATE_NO = "CERTIFICATE_NO";
	public static final String ALIAS_EMAIL = "EMAIL";
	public static final String ALIAS_MOBILE = "MOBILE";
	public static final String ALIAS_TELEPHONE = "TELEPHONE";
	public static final String ALIAS_FAX = "FAX";
	public static final String ALIAS_WEBSITE = "WEBSITE";
	public static final String ALIAS_POSTAL_CODE = "POSTAL_CODE";
	public static final String ALIAS_ADDRESS = "ADDRESS";
	public static final String ALIAS_REMARK = "REMARK";
	public static final String ALIAS_OTHER_CERTIFICATE_TYPE = "OTHER_CERTIFICATE_TYPE";
	public static final String ALIAS_REGISTER_DATE = "成立日期";
	public static final String ALIAS_LICENSE_TYPE = "证件类型（营业执照、其他）";
	public static final String ALIAS_LICENSE_OTHER_NAME = "其他机构执照名称";
	public static final String ALIAS_LICENSE_DATE_START = "营业执照起始日期";
	public static final String ALIAS_ORG_DATE_START = "组织机构代码证起始日期";
	public static final String ALIAS_ORG_DATE_END = "组织机构代码证截止日期";
	public static final String ALIAS_TAXPAYER_DATE_START = "税务登记证号码有效起始日期";
	public static final String ALIAS_TAXPAYER_DATE_END = "税务登记证号码有效截止日期";
	public static final String ALIAS_INDUSTRY = "所属行业(营业执照)";
	public static final String ALIAS_BUSINESS_SCOPE = "经营范围(营业执照)";
	public static final String ALIAS_REGISTERED_CURRENCY = "注册币种";
	public static final String ALIAS_REGISTERED_CAPITAL = "注册资本/认缴资金";
	public static final String ALIAS_PROVINCE = "省";
	public static final String ALIAS_CITY = "市";
	public static final String ALIAS_DISTRICT = "区";
	public static final String ALIAS_ASS_REGISTER_FLAG = "是否在基金业协会完成登记";
	public static final String ALIAS_ARBITRATION_FLAG = "是否在最近三年存在涉诉或仲裁的情况";
	public static final String ALIAS_PUNISH_FLAG = "是否受到过刑事处罚、行业协会纪律处分、金融监管部门行政处罚或被采取行政监管措施";
	public static final String ALIAS_ASS_SATISFY_FLAG = "是否满足基金业协会相关规定";
	public static final String ALIAS_EFFECTIVE_DATE = "EFFECTIVE_DATE";
	public static final String ALIAS_VAT_FILE_ID = "VAT_FILE_ID";
	public static final String ALIAS_BUSINESS_LEGAL_PERSON_TYPE = "BUSINESS_LEGAL_PERSON_TYPE";
	public static final String ALIAS_BUSINESS_LEGAL_REPRESENTATIVE = "BUSINESS_LEGAL_REPRESENTATIVE";
	public static final String ALIAS_BUSINESS_REGISTERED_CAPITAL = "BUSINESS_REGISTERED_CAPITAL";
	public static final String ALIAS_BUSINESS_DATE_OF_ESTABLISH = "BUSINESS_DATE_OF_ESTABLISH";
	public static final String ALIAS_LICENSE_EFFECTIVE_START_DATE = "LICENSE_EFFECTIVE_START_DATE";
	public static final String ALIAS_BUSINESS_LICENSE_NAME = "营业执照名称";
	public static final String ALIAS_BUSINESS_LICENSE_TYPE = "营业执照类型";
	public static final String ALIAS_IS_BUSINESS_LICENSE = "是否是营业执照";
	public static final String ALIAS_MAJOR_BUSINESS = "主要营业";

	/**
	 * ID
	 */
	private Integer id;

	/**
	 * INST_ID
	 */
	private String instId;

	/**
	 * INST_NAME
	 */
	private String instName;

	/**
	 * ORG_CODE
	 */
	private String orgCode;

	/**
	 * MANAGER_TYPE
	 */
	private String managerType;

	/**
	 * MANAGER_PRI_CODE
	 */
	private String managerPriCode;

	/**
	 * MANAGER_TA_CODE
	 */
	private String managerTaCode;

	/**
	 * MANAGER_DUTY
	 */
	private String managerDuty;

	/**
	 * MANAGER_PROMISE
	 */
	private String managerPromise;

	/**
	 * MANAGE_FUND_DUTY
	 */
	private String manageFundDuty;

	/**
	 * MANAGE_OTHER_TYPE
	 */
	private String manageOtherType;

	/**
	 * MANAGE_ASSET_SCALE
	 */
	private BigDecimal manageAssetScale;

	/**
	 * VIP_TYPE
	 */
	private String vipType;

	/**
	 * PRIVATE_MANAGER_TYPE
	 */
	private String privateManagerType;

	/**
	 * INVESTMENT_SCOPE
	 */
	private String investmentScope;

	/**
	 * TAXPAYER_IDNO
	 */
	private String taxpayerIdno;

	/**
	 * TAXPAY_FREQUENCY
	 */
	private String taxpayFrequency;

	/**
	 * TAXPAYER_TYPE
	 */
	private String taxpayerType;

	/**
	 * TAXOFFICE_LOCATION
	 */
	private String taxofficeLocation;

	/**
	 * AMAC_MEMBER
	 */
	private String amacMember;

	/**
	 * SPECIAL_INFO
	 */
	private String specialInfo;

	/**
	 * OTHER_SPECIAL_REAMRK
	 */
	private String otherSpecialReamrk;

	/**
	 * MEMBER_NO_SHSE
	 */
	private String memberNoShse;

	/**
	 * MEMBER_NAME_SHSE
	 */
	private String memberNameShse;

	/**
	 * MEMBER_NO_SZSE
	 */
	private String memberNoSzse;

	/**
	 * MEMBER_NAME_SZSE
	 */
	private String memberNameSzse;

	/**
	 * FRONT_END_RISK
	 */
	private Integer frontEndRisk;

	/**
	 * NATURE_ISSUER
	 */
	private Integer natureIssuer;

	/**
	 * MANAGER_RAISE_NO
	 */
	private Integer managerRaiseNo;

	/**
	 * ACCESS_LEVEL
	 */
	private String accessLevel;

	/**
	 * ACCESS_LEVEL_DETAIL
	 */
	private String accessLevelDetail;

	/**
	 * DATA_SOURCE
	 */
	private String dataSource;

	/**
	 * CREATOR
	 */
	private String creator;

	/**
	 * MODIFIER
	 */
	private String modifier;

	/**
	 * GMT_CREATE
	 */
	private Date gmtCreate;

	/**
	 * GMT_MODIFIED
	 */
	private Date gmtModified;

	/**
	 * INST_SHORT_NAME
	 */
	private String instShortName;

	/**
	 * BUSINESS_LICENSE
	 */
	private String businessLicense;

	/**
	 * LICENSE_EFFECTIVE_DATE_TYPE
	 */
	private String licenseEffectiveDateType;

	/**
	 * LICENSE_EFFECTIVE_DATE
	 */
	private Date licenseEffectiveDate;

	/**
	 * REG_ADDRESS
	 */
	private String regAddress;

	/**
	 * MANAGER_CODE
	 */
	private String managerCode;

	/**
	 * CITIZENSHIP
	 */
	private String citizenship;

	/**
	 * CONTACT
	 */
	private String contact;

	/**
	 * CERTIFICATE_TYPE
	 */
	private String certificateType;

	/**
	 * CERTIFICATE_NO
	 */
	private String certificateNo;

	/**
	 * EMAIL
	 */
	private String email;

	/**
	 * MOBILE
	 */
	private String mobile;

	/**
	 * TELEPHONE
	 */
	private String telephone;

	/**
	 * FAX
	 */
	private String fax;

	/**
	 * WEBSITE
	 */
	private String website;

	/**
	 * POSTAL_CODE
	 */
	private String postalCode;

	/**
	 * ADDRESS
	 */
	private String address;

	/**
	 * REMARK
	 */
	private String remark;

	/**
	 * OTHER_CERTIFICATE_TYPE
	 */
	private String otherCertificateType;

	/**
	 * 成立日期
	 */
	private Date registerDate;

	/**
	 * 证件类型（营业执照、其他）
	 */
	private String licenseType;

	/**
	 * 其他机构执照名称
	 */
	private String licenseOtherName;

	/**
	 * 营业执照起始日期
	 */
	private Date licenseDateStart;

	/**
	 * 组织机构代码证起始日期
	 */
	private Date orgDateStart;

	/**
	 * 组织机构代码证截止日期
	 */
	private Date orgDateEnd;

	/**
	 * 税务登记证号码有效起始日期
	 */
	private Date taxpayerDateStart;

	/**
	 * 税务登记证号码有效截止日期
	 */
	private Date taxpayerDateEnd;

	/**
	 * 所属行业(营业执照)
	 */
	private String industry;

	/**
	 * 经营范围(营业执照)
	 */
	private String businessScope;

	/**
	 * 注册币种
	 */
	private String registeredCurrency;

	/**
	 * 注册资本/认缴资金
	 */
	private BigDecimal registeredCapital;

	/**
	 * 省
	 */
	private String province;

	/**
	 * 市
	 */
	private String city;

	/**
	 * 区
	 */
	private String district;

	/**
	 * 是否在基金业协会完成登记
	 */
	private String assRegisterFlag;

	/**
	 * 是否在最近三年存在涉诉或仲裁的情况
	 */
	private String arbitrationFlag;

	/**
	 * 是否受到过刑事处罚、行业协会纪律处分、金融监管部门行政处罚或被采取行政监管措施
	 */
	private String punishFlag;

	/**
	 * 是否满足基金业协会相关规定
	 */
	private String assSatisfyFlag;

	/**
	 * EFFECTIVE_DATE
	 */
	private Date effectiveDate;

	/**
	 * VAT_FILE_ID
	 */
	private String vatFileId;

	/**
	 * BUSINESS_LEGAL_PERSON_TYPE
	 */
	private String businessLegalPersonType;

	/**
	 * BUSINESS_LEGAL_REPRESENTATIVE
	 */
	private String businessLegalRepresentative;

	/**
	 * BUSINESS_REGISTERED_CAPITAL
	 */
	private String businessRegisteredCapital;

	/**
	 * BUSINESS_DATE_OF_ESTABLISH
	 */
	private Date businessDateOfEstablish;

	/**
	 * LICENSE_EFFECTIVE_START_DATE
	 */
	private Date licenseEffectiveStartDate;

	/**
	 * 营业执照名称
	 */
	private String businessLicenseName;

	/**
	 * 营业执照类型
	 */
	private String businessLicenseType;

	/**
	 * 是否是营业执照
	 */
	private String isBusinessLicense;

	/**
	 * 主要营业
	 */
	private String majorBusiness;

	/**
	 * 营业执照地址
	 */
	private String businessAddress;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInstId() {
		return instId;
	}

	public void setInstId(String instId) {
		this.instId = instId;
	}

	public String getInstName() {
		return instName;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getManagerType() {
		return managerType;
	}

	public void setManagerType(String managerType) {
		this.managerType = managerType;
	}

	public String getManagerPriCode() {
		return managerPriCode;
	}

	public void setManagerPriCode(String managerPriCode) {
		this.managerPriCode = managerPriCode;
	}

	public String getManagerTaCode() {
		return managerTaCode;
	}

	public void setManagerTaCode(String managerTaCode) {
		this.managerTaCode = managerTaCode;
	}

	public String getManagerDuty() {
		return managerDuty;
	}

	public void setManagerDuty(String managerDuty) {
		this.managerDuty = managerDuty;
	}

	public String getManagerPromise() {
		return managerPromise;
	}

	public void setManagerPromise(String managerPromise) {
		this.managerPromise = managerPromise;
	}

	public String getManageFundDuty() {
		return manageFundDuty;
	}

	public void setManageFundDuty(String manageFundDuty) {
		this.manageFundDuty = manageFundDuty;
	}

	public String getManageOtherType() {
		return manageOtherType;
	}

	public void setManageOtherType(String manageOtherType) {
		this.manageOtherType = manageOtherType;
	}

	public BigDecimal getManageAssetScale() {
		return manageAssetScale;
	}

	public void setManageAssetScale(BigDecimal manageAssetScale) {
		this.manageAssetScale = manageAssetScale;
	}

	public String getVipType() {
		return vipType;
	}

	public void setVipType(String vipType) {
		this.vipType = vipType;
	}

	public String getPrivateManagerType() {
		return privateManagerType;
	}

	public void setPrivateManagerType(String privateManagerType) {
		this.privateManagerType = privateManagerType;
	}

	public String getInvestmentScope() {
		return investmentScope;
	}

	public void setInvestmentScope(String investmentScope) {
		this.investmentScope = investmentScope;
	}

	public String getTaxpayerIdno() {
		return taxpayerIdno;
	}

	public void setTaxpayerIdno(String taxpayerIdno) {
		this.taxpayerIdno = taxpayerIdno;
	}

	public String getTaxpayFrequency() {
		return taxpayFrequency;
	}

	public void setTaxpayFrequency(String taxpayFrequency) {
		this.taxpayFrequency = taxpayFrequency;
	}

	public String getTaxpayerType() {
		return taxpayerType;
	}

	public void setTaxpayerType(String taxpayerType) {
		this.taxpayerType = taxpayerType;
	}

	public String getTaxofficeLocation() {
		return taxofficeLocation;
	}

	public void setTaxofficeLocation(String taxofficeLocation) {
		this.taxofficeLocation = taxofficeLocation;
	}

	public String getAmacMember() {
		return amacMember;
	}

	public void setAmacMember(String amacMember) {
		this.amacMember = amacMember;
	}

	public String getSpecialInfo() {
		return specialInfo;
	}

	public void setSpecialInfo(String specialInfo) {
		this.specialInfo = specialInfo;
	}

	public String getOtherSpecialReamrk() {
		return otherSpecialReamrk;
	}

	public void setOtherSpecialReamrk(String otherSpecialReamrk) {
		this.otherSpecialReamrk = otherSpecialReamrk;
	}

	public String getMemberNoShse() {
		return memberNoShse;
	}

	public void setMemberNoShse(String memberNoShse) {
		this.memberNoShse = memberNoShse;
	}

	public String getMemberNameShse() {
		return memberNameShse;
	}

	public void setMemberNameShse(String memberNameShse) {
		this.memberNameShse = memberNameShse;
	}

	public String getMemberNoSzse() {
		return memberNoSzse;
	}

	public void setMemberNoSzse(String memberNoSzse) {
		this.memberNoSzse = memberNoSzse;
	}

	public String getMemberNameSzse() {
		return memberNameSzse;
	}

	public void setMemberNameSzse(String memberNameSzse) {
		this.memberNameSzse = memberNameSzse;
	}

	public Integer getFrontEndRisk() {
		return frontEndRisk;
	}

	public void setFrontEndRisk(Integer frontEndRisk) {
		this.frontEndRisk = frontEndRisk;
	}

	public Integer getNatureIssuer() {
		return natureIssuer;
	}

	public void setNatureIssuer(Integer natureIssuer) {
		this.natureIssuer = natureIssuer;
	}

	public Integer getManagerRaiseNo() {
		return managerRaiseNo;
	}

	public void setManagerRaiseNo(Integer managerRaiseNo) {
		this.managerRaiseNo = managerRaiseNo;
	}

	public String getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}

	public String getAccessLevelDetail() {
		return accessLevelDetail;
	}

	public void setAccessLevelDetail(String accessLevelDetail) {
		this.accessLevelDetail = accessLevelDetail;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public String getInstShortName() {
		return instShortName;
	}

	public void setInstShortName(String instShortName) {
		this.instShortName = instShortName;
	}

	public String getBusinessLicense() {
		return businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}

	public String getLicenseEffectiveDateType() {
		return licenseEffectiveDateType;
	}

	public void setLicenseEffectiveDateType(String licenseEffectiveDateType) {
		this.licenseEffectiveDateType = licenseEffectiveDateType;
	}

	public Date getLicenseEffectiveDate() {
		return licenseEffectiveDate;
	}

	public void setLicenseEffectiveDate(Date licenseEffectiveDate) {
		this.licenseEffectiveDate = licenseEffectiveDate;
	}

	public String getRegAddress() {
		return regAddress;
	}

	public void setRegAddress(String regAddress) {
		this.regAddress = regAddress;
	}

	public String getManagerCode() {
		return managerCode;
	}

	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}

	public String getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}

	public String getCertificateNo() {
		return certificateNo;
	}

	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOtherCertificateType() {
		return otherCertificateType;
	}

	public void setOtherCertificateType(String otherCertificateType) {
		this.otherCertificateType = otherCertificateType;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getLicenseType() {
		return licenseType;
	}

	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}

	public String getLicenseOtherName() {
		return licenseOtherName;
	}

	public void setLicenseOtherName(String licenseOtherName) {
		this.licenseOtherName = licenseOtherName;
	}

	public Date getLicenseDateStart() {
		return licenseDateStart;
	}

	public void setLicenseDateStart(Date licenseDateStart) {
		this.licenseDateStart = licenseDateStart;
	}

	public Date getOrgDateStart() {
		return orgDateStart;
	}

	public void setOrgDateStart(Date orgDateStart) {
		this.orgDateStart = orgDateStart;
	}

	public Date getOrgDateEnd() {
		return orgDateEnd;
	}

	public void setOrgDateEnd(Date orgDateEnd) {
		this.orgDateEnd = orgDateEnd;
	}

	public Date getTaxpayerDateStart() {
		return taxpayerDateStart;
	}

	public void setTaxpayerDateStart(Date taxpayerDateStart) {
		this.taxpayerDateStart = taxpayerDateStart;
	}

	public Date getTaxpayerDateEnd() {
		return taxpayerDateEnd;
	}

	public void setTaxpayerDateEnd(Date taxpayerDateEnd) {
		this.taxpayerDateEnd = taxpayerDateEnd;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getBusinessScope() {
		return businessScope;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}

	public String getRegisteredCurrency() {
		return registeredCurrency;
	}

	public void setRegisteredCurrency(String registeredCurrency) {
		this.registeredCurrency = registeredCurrency;
	}

	public BigDecimal getRegisteredCapital() {
		return registeredCapital;
	}

	public void setRegisteredCapital(BigDecimal registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAssRegisterFlag() {
		return assRegisterFlag;
	}

	public void setAssRegisterFlag(String assRegisterFlag) {
		this.assRegisterFlag = assRegisterFlag;
	}

	public String getArbitrationFlag() {
		return arbitrationFlag;
	}

	public void setArbitrationFlag(String arbitrationFlag) {
		this.arbitrationFlag = arbitrationFlag;
	}

	public String getPunishFlag() {
		return punishFlag;
	}

	public void setPunishFlag(String punishFlag) {
		this.punishFlag = punishFlag;
	}

	public String getAssSatisfyFlag() {
		return assSatisfyFlag;
	}

	public void setAssSatisfyFlag(String assSatisfyFlag) {
		this.assSatisfyFlag = assSatisfyFlag;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getVatFileId() {
		return vatFileId;
	}

	public void setVatFileId(String vatFileId) {
		this.vatFileId = vatFileId;
	}

	public String getBusinessLegalPersonType() {
		return businessLegalPersonType;
	}

	public void setBusinessLegalPersonType(String businessLegalPersonType) {
		this.businessLegalPersonType = businessLegalPersonType;
	}

	public String getBusinessLegalRepresentative() {
		return businessLegalRepresentative;
	}

	public void setBusinessLegalRepresentative(String businessLegalRepresentative) {
		this.businessLegalRepresentative = businessLegalRepresentative;
	}

	public String getBusinessRegisteredCapital() {
		return businessRegisteredCapital;
	}

	public void setBusinessRegisteredCapital(String businessRegisteredCapital) {
		this.businessRegisteredCapital = businessRegisteredCapital;
	}

	public Date getBusinessDateOfEstablish() {
		return businessDateOfEstablish;
	}

	public void setBusinessDateOfEstablish(Date businessDateOfEstablish) {
		this.businessDateOfEstablish = businessDateOfEstablish;
	}

	public Date getLicenseEffectiveStartDate() {
		return licenseEffectiveStartDate;
	}

	public void setLicenseEffectiveStartDate(Date licenseEffectiveStartDate) {
		this.licenseEffectiveStartDate = licenseEffectiveStartDate;
	}

	public String getBusinessLicenseName() {
		return businessLicenseName;
	}

	public void setBusinessLicenseName(String businessLicenseName) {
		this.businessLicenseName = businessLicenseName;
	}

	public String getBusinessLicenseType() {
		return businessLicenseType;
	}

	public void setBusinessLicenseType(String businessLicenseType) {
		this.businessLicenseType = businessLicenseType;
	}

	public String getIsBusinessLicense() {
		return isBusinessLicense;
	}

	public void setIsBusinessLicense(String isBusinessLicense) {
		this.isBusinessLicense = isBusinessLicense;
	}

	public String getMajorBusiness() {
		return majorBusiness;
	}

	public void setMajorBusiness(String majorBusiness) {
		this.majorBusiness = majorBusiness;
	}

	public String getBusinessAddress() {
		return businessAddress;
	}

	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}
}

