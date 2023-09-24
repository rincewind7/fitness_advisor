import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GPT4Connector implements ApiConnector{

    private String baseUrl;
    private String keyAPI;

    public GPT4Connector(String baseUrl, String keyAPI) {
        this.baseUrl = baseUrl;
        this.keyAPI = keyAPI;
    }

    @Override
    public String sendRequest(String requestData)  {
        try {
            HttpClient client = HttpClient.newHttpClient(); //potrzebne nam to do metody send
            // Tworzenie żądania do API GPT-4 z danymi użytkownika
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(baseUrl + "/v1/chat/completions")) // Zgodnie z dokumentacją OpenAI
                .header("Authorization", "Bearer " + keyAPI) // Ustawienie klucza API dla autoryzacji
                .header("Content-Type", "application/json") // Określenie typu zawartości jako JSON
                .POST(HttpRequest.BodyPublishers.ofString(requestData)) // Ustawienie metody na POST i dodanie danych do ciała żądania
                .build();

             // Wysłanie żądania i oczekiwanie na odpowiedź
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
             //Sprawdzanie statusu odpowiedzi ponieważ HttpClient nie wyrzuca błędów przy nieprawidłowym kodzie odpowiedzi
            if (response.statusCode() != 200) {
                throw new IOException("Nieprawidłowy kod odpowiedzi: " + response.statusCode());
            }

            // Zwrócenie ciała odpowiedzi jako String
            return response.body();
        }
        catch (URISyntaxException e) {
            System.err.println("Błąd w adresie URI: " + e.getMessage());
            return "Błąd w adresie URI: " + e.getMessage();
        }
        catch (IOException e) {
            System.err.println("Błąd podczas wykonywania żądania: " + e.getMessage());
            return "Błąd podczas wykonywania żądania: " + e.getMessage();
        }
        //musimy dodać InterruptedException bo metoda send z HttpRequest
        catch (InterruptedException e) {
            System.err.println("Przerwanie podczas wykonywania żądania: " + e.getMessage());
            Thread.currentThread().interrupt(); //Odtwarzanie przerwanego statusu wątku
            // jeśli nie odtworzysz przerwanego statusu, inne fragmenty kodu, które sprawdzają ten status (na przykład poprzez Thread.interrupted()), nie będą wiedziały, że wątek został przerwany.
            return "Przerwanie podczas wykonywania żądania: " + e.getMessage();
        }

    }

    public static String loadApiKeyFromFile(String filePath) {
        String keyAPI = null;
        try {
            keyAPI = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return keyAPI;
    }

}
