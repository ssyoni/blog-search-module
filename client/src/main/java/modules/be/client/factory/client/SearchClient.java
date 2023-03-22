package modules.be.client.factory.client;

import modules.be.client.dto.SearchBaseResponse;
import modules.be.client.dto.SearchRequest;
import org.springframework.http.HttpHeaders;

import java.net.URI;

public interface SearchClient {
    SearchBaseResponse request(SearchRequest requestParam);
    //URI uriBuilder(String query, String sort, String page, String siz);
    HttpHeaders createHeader();
}
