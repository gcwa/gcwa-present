package ca.gc.collectionscanada.model;

/**
 * This class represents a row in DEPT database table of schema,
 * it is just a bean which is initialized as needed
 * @author khatrz
 *
 */
public class Department
{
    String deptID;
    String deptNameEng;
    String deptNameFr;
    
	/**
	 * Default constructor
	 */
    public Department()
    {
    }

    public String getDeptID()
    {
        return deptID;
    }

    public void setDeptID(String deptID)
    {
        this.deptID = deptID;
    }

    public String getDeptNameEng()
    {
        return deptNameEng;
    }

    public void setDeptNameEng(String deptNameEng)
    {
        this.deptNameEng = deptNameEng;
    }

    public String getDeptNameFr()
    {
        return deptNameFr;
    }

    public void setDeptNameFr(String deptNameFr)
    {
        this.deptNameFr = deptNameFr;
    }
}
