package pages.customElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;


public class Table {
    private WebElement table;
    private List<Map<String,String>> row;

    public Table(WebElement table){
        this.table=table;
    }

    public Integer getColumnSize(){
        return table.findElements(By.xpath(".//thead/tr/th[@hidden='hidden']")).size();
    }

    public Integer getRowSize(){
        return table.findElements(By.xpath(".//tbody/tr")).size();
    }

    public String getColumnName(Integer index){
        return table.findElement(By.xpath("(.//thead/tr/th)["+index+"]")).getText();
    }


}
