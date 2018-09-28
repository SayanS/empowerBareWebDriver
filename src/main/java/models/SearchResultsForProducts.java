package models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchResultsForProducts {
    public Integer iTotalRecords;
    public Integer iTotalDisplayRecords;
    public Object sEcho;
    public Object sColumns;
    public List<AAData> aaData = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
