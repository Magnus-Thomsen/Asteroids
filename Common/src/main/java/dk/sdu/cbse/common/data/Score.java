package dk.sdu.cbse.common.data;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public final class Score {

    /**
     * RestTemplate for adding score using HTTP requests.
     */
    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Adds score by sending a request to the score service.
     *
     * @param amount Score amount to add.
     */
    public void add(final int amount) {
        try {
            restTemplate.getForObject("http://localhost:8080/score?points=" + amount, String.class);
        } catch (RestClientException e) {
            System.err.println("Failed to add score: " + e.getMessage());
        }
    }

    /**
     * Retrieves the current score.
     *
     * @return the current score as a string, or null if the request fails.
     */
    public String getScore() {
        try {
            return restTemplate.getForObject("http://localhost:8080/current", String.class);
        } catch (RestClientException e) {
            System.err.println("Failed to get score: " + e.getMessage());
            return null;
        }
    }

    /**
     * Resets the score.
     */
    public void reset() {
        try {
            restTemplate.getForObject("http://localhost:8080/reset", String.class);
        } catch (RestClientException e) {
            System.err.println("Failed to reset score: " + e.getMessage());
        }
    }
}
