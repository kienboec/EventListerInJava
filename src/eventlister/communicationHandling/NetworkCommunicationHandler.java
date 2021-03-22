package eventlister.communicationHandling;

import eventlister.argumentHandling.ArgumentHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class NetworkCommunicationHandler implements CommunicationHandler {

    @Autowired
    private ArgumentHandler _argumentHandler;

    private String _cachedContent = null;

    @Override
    public String getContent() {
        if (_cachedContent != null) {
            return _cachedContent;
        }

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(_argumentHandler.getDataSourceAddress()))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return _cachedContent = response.body();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "";
    }
}
