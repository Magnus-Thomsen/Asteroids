package dk.sdu.cbse.main;

import dk.sdu.cbse.common.entities.bullet.IBullet;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.entities.player.IPlayer;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.cbse.common.util.ServiceLocator;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class Main extends Application {

    /**
     * Gamedata for the game.
     */
    private final GameData gameData = new GameData();

    /**
     * World for having all entities.
     */
    private final World world = new World();

    /**
     * Map of all polygons added to the game.
     */
    private final Map<Entity, Polygon> polygons = new ConcurrentHashMap<>();

    /**
     * GameWindow pane.
     */
    private final Pane gameWindow = new Pane();

    /**
     * Label for displaying score.
     */
    private final Label scoreLabel = new Label("Score: 0");

    /**
     * Background for displaying health.
     */
    private final Rectangle healthBarBackground =
            new Rectangle(200, 20, Color.DARKRED);

    /**
     * Healthbar.
     */
    private final Rectangle healthBar = new Rectangle(200, 20, Color.LIMEGREEN);

    /**
     * Label for displaying game over.
     */
    private final Label gameOverLabel = new Label("GAME OVER");

    /**
     * Restart button for restarting the game.
     */
    private final Button restartButton = new Button("Restart");

    /**
     * Flag for when the game over screen has been shown.
     */
    private boolean gameOverShown = false;



    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) {
        gameWindow.setPrefSize(gameData.getDisplayWidth(),
                gameData.getDisplayHeight());
        gameWindow.setStyle("-fx-background-color: black;");
        Scene scene = new Scene(gameWindow);

        InputProcessor.setupInputHandling(scene, gameData);

        for (IGamePluginService iGamePluginService : getPluginServices()) {
            iGamePluginService.start(gameData, world);
        }

        for (Entity entity : world.getEntities()) {
            Polygon polygon = new Polygon(entity.getPolygonCoordinates());
            polygon.setFill(entity.getColor());
            polygon.setStroke(entity.getColor().brighter());
            polygons.put(entity, polygon);
            gameWindow.getChildren().add(polygon);
        }

        addStars(200);

        scoreLabel.setTextFill(Color.WHITE);
        scoreLabel.setStyle("-fx-font-size: 18;");
        scoreLabel.setTranslateX(10);
        scoreLabel.setTranslateY(10);
        gameWindow.getChildren().add(scoreLabel);

        healthBarBackground.setTranslateX(10);
        healthBarBackground.setTranslateY(40);
        healthBar.setTranslateX(10);
        healthBar.setTranslateY(40);

        gameWindow.getChildren().addAll(healthBarBackground, healthBar);

        gameOverLabel.setTextFill(Color.RED);
        gameOverLabel.setStyle("-fx-font-size: 48; -fx-font-weight: bold;");
        gameOverLabel.setVisible(false);
        gameOverLabel.setTranslateX(gameData.getDisplayWidth() / 2.0 - 140);
        gameOverLabel.setTranslateY(gameData.getDisplayHeight() / 2.0 - 100);

        restartButton.setStyle("-fx-font-size: 18;");
        restartButton.setVisible(false);
        restartButton.setTranslateX(gameData.getDisplayWidth() / 2.0 - 50);
        restartButton.setTranslateY(gameData.getDisplayHeight() / 2.0);
        restartButton.setOnAction(e -> restartGame());

        gameWindow.getChildren().addAll(gameOverLabel, restartButton);


        render();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Asteroids");
        primaryStage.show();
    }

    private void render() {
        final long[] lastTime = {System.nanoTime()};

        new AnimationTimer() {
            @Override
            public void handle(final long now) {
                float delta = (now - lastTime[0]) / 1_000_000_000f;
                lastTime[0] = now;

                gameData.setDelta(delta);

                update();
                draw();
                gameData.getKeys().update();
            }
        }.start();
    }


    private void update() {

        for (IEntityProcessingService entityProcessorService
                : getEntityProcessingServices()) {
            entityProcessorService.process(gameData, world);
        }

        for (IPostEntityProcessingService postEntityProcessorService
                : getPostEntityProcessingServices()) {
            postEntityProcessorService.process(gameData, world);
        }
    }

    private void draw() {
        for (Entity polygonEntity : polygons.keySet()) {
            if (!world.getEntities().contains(polygonEntity)) {
                Polygon removedPolygon = polygons.get(polygonEntity);
                polygons.remove(polygonEntity);
                gameWindow.getChildren().remove(removedPolygon);
            }
        }

        for (Entity entity : world.getEntities()) {
            Polygon polygon = polygons.get(entity);

            // Polygon is created
            if (polygon == null) {
                polygon = new Polygon(entity.getPolygonCoordinates());
                if (entity instanceof IBullet) {
                    polygon.setEffect(new DropShadow(10, Color.RED));
                }


                // Use entity color if available
                if (entity.getColor() != null) {
                    polygon.setFill(entity.getColor());
                    polygon.setStroke(entity.getColor().darker());
                }

                polygons.put(entity, polygon);
                gameWindow.getChildren().add(polygon);
            }

            // Entity color is changed when hit
            if (entity.isInvincible()) {
                polygon.setOpacity(0.5);
                float alpha = entity.getInvincibilityTimer() / 0.5f;
                Color color = new Color(1.0f, 1.0f - alpha, 1.0f - alpha, 1.0f);
                polygon.setFill(color);
            } else {
                polygon.setOpacity(1.0);
                polygon.setFill(entity.getColor());
            }

            polygon.setTranslateX(entity.getX());
            polygon.setTranslateY(entity.getY());
            polygon.setRotate(entity.getRotation());

            entity.updateInvincibility(gameData.getDelta());
        }

        Entity player = world.getEntities().stream()
                .filter(e -> e instanceof IPlayer)
                .findFirst()
                .orElse(null);

        if (player != null) {
            float healthRatio = player.getHealth() / 100f;
            healthBar.setWidth(200 * healthRatio);
            if (healthRatio > 0.5) {
                healthBar.setFill(Color.LIMEGREEN);
            } else if (healthRatio > 0.25) {
                healthBar.setFill(Color.GOLD);
            } else {
                healthBar.setFill(Color.RED);
            }
        } else if (!gameOverShown) {
            showGameOver();
        }

        scoreLabel.setText("Score: " + gameData.getScore().getScore());

    }

    private void showGameOver() {
        gameOverShown = true;
        gameOverLabel.setVisible(true);
        restartButton.setVisible(true);
    }

    private Collection<? extends IGamePluginService> getPluginServices() {
        return ServiceLocator.INSTANCE.locateAll(IGamePluginService.class);
    }

    private Collection<? extends IEntityProcessingService>
    getEntityProcessingServices() {
        return ServiceLocator.INSTANCE.locateAll(
                IEntityProcessingService.class);
    }

    private Collection<? extends IPostEntityProcessingService>
    getPostEntityProcessingServices() {
        return ServiceLocator.INSTANCE.locateAll(
                IPostEntityProcessingService.class);
    }

    private void addStars(final int count) {
        for (int i = 0; i < count; i++) {
            // random radius
            javafx.scene.shape.Circle star =
                    new javafx.scene.shape.Circle(Math.random() * 2 + 1);
            star.setCenterX(Math.random() * gameData.getDisplayWidth());
            star.setCenterY(Math.random() * gameData.getDisplayHeight());
            star.setFill(javafx.scene.paint.Color.WHITE);
            star.setOpacity(Math.random() * 0.8 + 0.2); // twinkling effect
            gameWindow.getChildren().add(star);
        }
    }


    private void restartGame() {
        gameOverShown = false;
        gameOverLabel.setVisible(false);
        restartButton.setVisible(false);

        world.getEntities().clear();
        polygons.clear();
        gameWindow.getChildren().removeIf(node -> node instanceof Polygon);

        gameData.getScore().reset();

        for (IGamePluginService plugin : getPluginServices()) {
            plugin.stop(gameData, world);
            plugin.start(gameData, world);
        }
    }



}
