import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.testng.annotations.Test;
import pages.ProductSearchResultPage;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;


public class HeaderSearchTests extends BaseTests{
    ProductSearchResultPage productSearchResultPage;

    private String BASE_URL="https://qa.geempower.com/geempower/product/search";
    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders headers = new HttpHeaders();
    UriComponentsBuilder builder;
   // private HttpEntity<String> requestEntity;
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
    public void searchProductByPartCatalogNumber() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException, IOException {
        String value="1000593";

//        headers.setContentType(MediaType.APPLICATION_JSON);
//       headers.set("Accept", "application/json, text/javascript, */*; q=0.01");
//        headers.set("X-Requested-With", "XMLHttpRequest");
//        headers.set("Referer", "https://qa.geempower.com/");
//        headers.set("Accept-Encoding", "gzip, deflate, br");
//        headers.set("Accept-Language", "en-US,en;q=0.8");
//        String s=getCookie().toString().replace("[","").replace("]","");
//        headers.set("Cookie", s);
//        headers.set("Content-Type", "application/x-www-form-urlencoded");
//
//        String params="/ajax?product=$prodName&onlyOneProduct=false";
//        params=params.replace("$prodName",value);
//        builder = UriComponentsBuilder.fromHttpUrl(BASE_URL+params);
//        requestEntity = new HttpEntity(headers);

//        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
//            public boolean verify(String hostname, SSLSession session) {
//                return true;
//            }
//        });

//        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
//        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
//        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
//        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
//        HttpComponentsClientHttpRequestFactory requestFactory =new HttpComponentsClientHttpRequestFactory();
//        requestFactory.setHttpClient(httpClient);
//
//        restTemplate = new RestTemplate(requestFactory);

//        responseEntity = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET,
//                requestEntity, String.class);

        ResponseHandler<String> handler = new BasicResponseHandler();

        HttpGet httpGet = new HttpGet("https://qa.geempower.com/geempower/product/search/ajax?product=15&onlyOneProduct=false&sEcho=1&iColumns=5&sColumns=%2Ccode%2Cupc%2Cdescription%2C&iDisplayStart=0&iDisplayLength=25&mDataProp_0=&sSearch_0=&bRegex_0=false&bSearchable_0=true&bSortable_0=false&mDataProp_1=&sSearch_1=&bRegex_1=false&bSearchable_1=true&bSortable_1=true&mDataProp_2=upc&sSearch_2=&bRegex_2=false&bSearchable_2=true&bSortable_2=true&mDataProp_3=&sSearch_3=&bRegex_3=false&bSearchable_3=true&bSortable_3=true&mDataProp_4=&sSearch_4=&bRegex_4=false&bSearchable_4=true&bSortable_4=false&sSearch=&bRegex=false&iSortCol_0=1&sSortDir_0=asc&iSortingCols=1&_=1504183946437");
        httpGet.addHeader("Accept", "application/json, text/javascript, */*; q=0.01");
        httpGet.addHeader("X-DevTools-Emulate-Network-Conditions-Client-Id","d4b5bb9f-c933-4ad6-b7fb-0cff587c3b17");
        httpGet.addHeader("X-Requested-With", "XMLHttpRequest");
        httpGet.addHeader("Referer", "https://qa.geempower.com/");
        httpGet.addHeader("Accept-Encoding", "gzip, deflate, br");
        httpGet.addHeader("Accept-Language", "en-US,en;q=0.8");
        String s=getCookie().toString();
        s=s.substring(1,s.length()-1);
        httpGet.addHeader("Cookie", s);

//        SSLContextBuilder builder = new SSLContextBuilder();
//        builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
//        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
//                builder.build());
//         CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(
//                sslsf).build();
        CloseableHttpClient httpclient = new DefaultHttpClient();
        CloseableHttpResponse response = httpclient.execute(httpGet);
        String body = handler.handleResponse(response);
        int i=0;

//        productSearchResultPage=getBasePage().searchFor(value);
//        for(String catalogNumber:productSearchResultPage.getColumnValues("Catalog No.")) {
//            Assert.assertEquals("Product number "+value+ "in search results isn't contains " + value, value, catalogNumber);
//        }

    }

}
