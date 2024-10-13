
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class UserSimulation implements Runnable {
    private final String origin;
    private final String destination;
    private final int passengerCount;

    public UserSimulation(String origin, String destination, int passengerCount) {
        this.origin = origin;
        this.destination = destination;
        this.passengerCount = passengerCount;
    }

    @Override
    public void run() {
        try {
            String apiUrl = "http://localhost:8080/travel-app-1.0.0/reserve-ticket?origin="
                    + origin + "&destination=" + destination + "&passengers=" + passengerCount;

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Cookie", "JSESSIONID=143053D1D4697003F608D62E5FD1934C");

            connection.setDoOutput(true);

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Ticket reservation successful.");
            } else {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println("Failed to reserve ticket. " + "Error Response Body: " + response);
            }

            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
