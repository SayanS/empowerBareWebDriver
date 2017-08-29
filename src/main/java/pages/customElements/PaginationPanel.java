package pages.customElements;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PaginationPanel {
    private WebElement paginationPanel;

    public  PaginationPanel(WebElement paginationPanel){
        this.paginationPanel=paginationPanel;
    }

    public String getTotal(){
        String total=paginationPanel.findElement(By.xpath("//*[@class='paginate_of']")).getText();
        total=total.replace("of ","");
        return total;
    }

    public void last(){
        try {
            paginationPanel.findElement(By.xpath("//*[@id='DataTables_Table_0_last']")).click();
        }catch(Exception e){
        }
    }

    public void first(){
        try {
            paginationPanel.findElement(By.xpath("//*[@id='DataTables_Table_0_first']")).click();
        }catch(Exception e){
        }
    }

    public void next(){
        try {
            paginationPanel.findElement(By.xpath("//*[@id='DataTables_Table_0_next']")).click();
        }catch(Exception e){
        }
    }

    public void previous(){
        try {
            paginationPanel.findElement(By.xpath("//*[@id='DataTables_Table_0_previous']")).click();
        }catch(Exception e){
        }
    }

    public boolean isDisplayed(){
        return paginationPanel.isDisplayed();
    }

}
