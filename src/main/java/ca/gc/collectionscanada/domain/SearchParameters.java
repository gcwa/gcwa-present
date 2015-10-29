package ca.gc.collectionscanada.domain;


public class SearchParameters
{

    String containWord;
    String notContainWord;
    String startDate;
    String endDate;
    String type;
    String fromWebsite;
    

    public SearchParameters()
    {
    }

    public String getContainWord()
    {
        return containWord;
    }

    public void setContainWord(String containWord)
    {
        this.containWord = containWord;
    }

    public String getNotContainWord()
    {
        return notContainWord;
    }

    public void setNotContainWord(String notContainWord)
    {
        this.notContainWord = notContainWord;
    }

    public String getStartDate()
    {
        return startDate;
    }

    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }

    public String getEndDate()
    {
        return endDate;
    }

    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getFromWebsite()
    {
        return fromWebsite;
    }

    public void setFromWebsite(String fromWebsite)
    {
        this.fromWebsite = fromWebsite;
    }
}
