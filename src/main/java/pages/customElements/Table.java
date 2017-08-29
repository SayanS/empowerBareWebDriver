package pages.customElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Table {
    private WebElement table;
    private List<Map<String,String>> row;

    public Table(WebElement table){
        this.table=table;
    }

    public Integer getColumnSize(){
        return table.findElements(By.xpath(".//thead/tr/th[not(contains(@hidden,'hidden'))]")).size();
    }

    public Integer getRowSize(){//.//tbody/tr/td[.='No matching records found']
        if (table.findElement(By.xpath(".//tbody/tr[1]/td[1]")).getText().equals("No matching records found")){
            return 0;
        }
        return table.findElements(By.xpath(".//tbody/tr")).size();
    }

    public String getColumnName(Integer index){
        return table.findElement(By.xpath("(.//thead/tr/th[not(contains(@hidden,'hidden'))])["+index+"]")).getText();
    }

    public List<String> getAllColumnName(){
        List<String> allColumnHeader=new ArrayList<>();
        for(WebElement columnHeader:table.findElements(By.xpath("(.//thead/tr/th[not(contains(@hidden,'hidden'))])"))){
            allColumnHeader.add(columnHeader.getText());
        }
        return allColumnHeader;
    }

    public List<String> getRowByLine(Integer index){
        List<String> rowValues=new ArrayList<>();
        for(WebElement rowValue:table.findElements(By.xpath("(.//tbody/tr["+index+"])/td[contains(@class,'table-visible-lg-block')]"))){
            rowValues.add(rowValue.getText());
        }
        return rowValues;
    }

    public Integer getColumnIndex(String columnName){
        Integer columnIndex=1;
        for(WebElement columnHeader:table.findElements(By.xpath(".//thead/tr/th"))){
            if(columnHeader.getText().equals(columnName)){
                return columnIndex;
            }else {
                columnIndex++;
            }
        }
        return null;
    }

    public List<String> getColumnValues(String columnName){
        List<String> columnValues=new ArrayList<>();
        for(WebElement columnRow:table.findElements(By.xpath(".//tbody/tr/td["+getColumnIndex(columnName)+"]"))){
            columnValues.add(columnRow.getText());
        }
        return columnValues;
    }

    public List<List<String>> getAllRowsValues(){
        List<List<String>> allRowsValues=new ArrayList<>();
        for(int i=1; i<=getRowSize();i++){
            allRowsValues.add(getRowByLine(i));
        }
        return allRowsValues;
    }



}
