package dk.sdu.cbse.scoringservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;


@SpringBootApplication
@RestController
public class ScoringService {

    private final AtomicInteger score = new AtomicInteger(0);

    public static void main(String[] args) {
        SpringApplication.run(ScoringService.class, args);
    }

    @GetMapping("/score")
    public int addScore(@RequestParam(value = "points") int points) {
        return score.addAndGet(points);
    }

    @GetMapping("/current")
    public int getCurrentScore() {
        return score.get();
    }

    @GetMapping("/reset")
    public void resetScore() {
        score.set(0);
    }
}