import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // Fazendo uma conexão HTTP e buscar os top 250 filmes
        // String str = "https://imdb-api.com/en/API/Top250Movies/k_se6ojs72";
        String mock = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        URI address = URI.create(mock);
        var client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        // System.out.println(body);

        // extrair só os dados que interessam (titulo, poster e classificação)
        var parser = new JsonParser();
        List<Map<String, String>> movieList = parser.parse(body);
        // System.out.println(movieList);
        
        // exibir e manipular os dados
        for (Map<String, String> movie : movieList) {
            System.out.println(ConsoleColors.CYAN_BACKGROUND + "Título:" + ConsoleColors.RESET);
            System.out.println(movie.get("title"));
            System.out.println("--------------------------------------------------------------------");
            System.out.println(ConsoleColors.PURPLE_BACKGROUND_BRIGHT + "Rating:" + ConsoleColors.RESET);
            System.out.println(movie.get("image"));
            System.out.println("--------------------------------------------------------------------");
            System.out.println(ConsoleColors.YELLOW_BACKGROUND_BRIGHT + "Rating:" + ConsoleColors.RESET);
            String rateStars = "";
            final float rateF = Float.parseFloat(movie.get("imDbRating"));
            int rate = (int) rateF;
            for (int i = 0; i < rate; i++) {
                rateStars = rateStars.concat("⭐");
            }
            System.out.println(rateStars);
            System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        }

    }
}
