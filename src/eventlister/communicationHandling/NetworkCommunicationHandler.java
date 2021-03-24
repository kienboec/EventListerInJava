package eventlister.communicationHandling;

import eventlister.argumentHandling.ArgumentHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * By adding the @Component, Spring is able to detect the class and register it.
 */
@Component
public class NetworkCommunicationHandler implements CommunicationHandler {

    /**
     * This is a dependency on the argument handler which stores the URI of the data source.
     *
     * Spring will automatically assign ("inject") the registered ArgumentHandler instance to the @Autowired element
     * when instantiating the NetworkCommunicationHandler. In our case this will be the single CommandLineArgumentHandler.
     *
     * Note that in this class there is no other way to provide this dependency (e.g. via a constructor)! As such this
     * class can only be properly instantiated and configured via Spring.
     */
    @Autowired
    private ArgumentHandler _argumentHandler;

    private String _cachedContent = null;

    @Override
    public String getContent() {
        if (_cachedContent != null) {
            // we have a cached result, no need for fetching it again
            // just return it
            return _cachedContent;
        }

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(_argumentHandler.getDataSourceAddress()))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // cache the result for following requests and return it
            return _cachedContent = response.body();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "";
    }
}
