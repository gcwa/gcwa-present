package ca.gc.collectionscanada.web.model;

/**
 * This class represents a Department wise URLs
 * with one department and many URL i.e. one to many relationship
 * 
 * @author khatrz
 *
 */
public class DepartmentWiseURLs {
	String deptId;
	String engDesc;
	String frDesc;
	String urlDesc;
	String urlId;
	SingleURL [] urlArray;
	
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getEngDesc() {
		return engDesc;
	}
	public void setEngDesc(String engDesc) {
		this.engDesc = engDesc;
	}
	public String getFrDesc() {
		return frDesc;
	}
	public void setFrDesc(String frDesc) {
		this.frDesc = frDesc;
	}
	public String getUrlDesc() {
		return urlDesc;
	}
	public void setUrlDesc(String urlDesc) {
		this.urlDesc = urlDesc;
	}
	public String getUrlId() {
		return urlId;
	}
	public void setUrlId(String urlId) {
		this.urlId = urlId;
	}
/*	public List<SingleURL> getUrlList() {
		return urlList;
	}
	public void setUrlList(List<SingleURL> urlList) {
		this.urlList = urlList;
	}*/
	public SingleURL[] getUrlArray() {
		return urlArray;
	}
	public void setUrlArray(SingleURL[] urlArray) {
		this.urlArray = urlArray;
	}	
}
