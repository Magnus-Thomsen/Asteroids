package dk.sdu.cbse.common.data;

import org.springframework.web.client.RestTemplate;

public final class Score {

    /**
     * RestTemplate for adding score using http requests.
     */
    RestTemplate restTemplate = new RestTemplate();

    /**
     * Add score using .
     * @param amount Score amount to add.
     */
    public void add(final int amount) {
        restTemplate.getForObject("http://localhost:8080/score?points=" + amount, String.class);
    }

    public String getScore() {
        return restTemplate.getForObject("http://localhost:8080/current", String.class);
    }

    /**
     * Resets the score.
     */
    public void reset() {
        restTemplate.getForObject("http://localhost:8080/reset", String.class);
    }

}
