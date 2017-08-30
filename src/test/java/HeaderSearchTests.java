import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.testng.annotations.Test;
import pages.ProductSearchResultPage;

public class HeaderSearchTests extends BaseTests{
    ProductSearchResultPage productSearchResultPage;

    private String BASE_URL="https://qa.geempower.com/geempower/product/search";
    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders headers = new HttpHeaders();
    UriComponentsBuilder builder;
    private HttpEntity<String> requestEntity;
    JSONObject requestBody = new JSONObject();
    private ResponseEntity responseEntity;

    @Test(groups = "run")
    public void searchProductByFullCatalogNumber(){
        String value="10005934";
        productSearchResultPage=getBasePage().searchFor(value);
        Assert.assertTrue("Search results isn't contained 1 product: "+productSearchResultPage.getColumnValues("Catalog No.").size(), productSearchResultPage.getColumnValues("Catalog No.").size()==1);
        Assert.assertEquals("Product in search results isn't equal to: "+value, value, productSearchResultPage.getColumnValues("Catalog No.").get(0));
    }

    @Test(groups = "run")
    public void searchProductByPartCatalogNumber(){
        String value="1000593";

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Accept", "application/json, text/javascript, */*; q=0.01");
        headers.set("X-Requested-With", "XMLHttpRequest");
        headers.set("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.78 Safari/537.36");
        headers.set("Referer", "https://qa.geempower.com/");
        headers.set("Accept-Encoding", "gzip, deflate, br");
        headers.set("Accept-Language", "en-US,en;q=0.8");
        String s=getCookie().toString().replace("[","").replace("]","");
        headers.set("Cookie", s);
        //headers.set("Content-Type", "application/x-www-form-urlencoded");
        String params="/ajax?product=$prodName&onlyOneProduct=false";
        params=params.replace("$prodName",value);
        builder = UriComponentsBuilder.fromHttpUrl(BASE_URL+params);
        requestEntity = new HttpEntity(headers);
        responseEntity = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET,
                requestEntity, Object.class);

int i=0;


//        productSearchResultPage=getBasePage().searchFor(value);
//        for(String catalogNumber:productSearchResultPage.getColumnValues("Catalog No.")) {
//            Assert.assertEquals("Product number "+value+ "in search results isn't contains " + value, value, catalogNumber);
//        }

    }

}
