package com.example.musogame;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    private static final String[][] questions = {
            {"What is the capital city of Lesotho?", "A) Maseru", "B) Leribe", "C) Mokhotlong", "D) Mohale's Hoek", "A"},
            {"What is the highest point in Lesotho?", "A) Thabana Ntlenyana", "B) Mount Qiloane", "C) Sani Pass", "D) Qacha's Nek", "A"},
            {"Who is the founder of Basotho Nation ?", "A) Masotho", "B) Makhabane", "C) Moshoeshoe", "D) Letsie", "C"},
            {"How many districts are there in Lesotho ?", "A) 10", "B) 6", "C) 6", "D) 8", "B"},
            {"What is the official language of Lesotho?", "A) English", "B) Sesotho", "C) French", "D) Portuguese", "B"},
            {"Which animal is featured on the Lesotho coat of arms?", "A) Lion", "B) Elephant", "C) Ram", "D) Antelope", "C"},
            {"What is the currency of Lesotho?", "A) Euro", "B) Dollar", "C) Rand", "D) Pula", "C"},
            {"Which sport is the most popular in Lesotho?", "A) Soccer", "B) Rugby", "C) Cricket", "D) Basketball", "A"},
            {"In which year did Lesotho gain independence?", "A) 1966", "B) 1975", "C) 1980", "D) 1994", "A"}
    };

    private int currentQuestionIndex = 0;
    private int score = 0;

    private Label questionLabel;
    private Button[] optionButtons;
    private Label feedbackLabel;
    private Label scoreLabel;

    private BorderPane root; // Declare root as a class-level field

    @Override
    public void start(Stage primaryStage) {
        root = new BorderPane(); // Initialize root here
        root.setPadding(new Insets(20));

        // Set background image from resources directory
        root.setStyle("-fx-background-image: url('/com/example/musogame/resources/background_image.jpg'); -fx-background-size: cover;");

        VBox centerBox = new VBox(20);
        centerBox.setAlignment(Pos.CENTER);

        Button startButton = new Button("Start Game");
        startButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 16px;");
        startButton.setOnAction(e -> showQuestions(primaryStage));
        centerBox.getChildren().add(startButton);

        root.setCenter(centerBox);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Lesotho Trivia Game");
        primaryStage.show();
    }

    private void showQuestions(Stage primaryStage) {
        BorderPane questionRoot = new BorderPane();
        questionRoot.setPadding(new Insets(20));

        // Set background image from resources directory
        questionRoot.setStyle("-fx-background-image: url('/com/example/musogame/resources/background_image.jpg'); -fx-background-size: cover;");

        questionLabel = new Label();
        feedbackLabel = new Label();
        scoreLabel = new Label();

        GridPane optionsGrid = new GridPane();
        optionsGrid.setHgap(10);
        optionsGrid.setVgap(10);

        optionButtons = new Button[4];
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new Button();
            optionButtons[i].setPrefWidth(200);
            optionButtons[i].setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 16px;");
            optionButtons[i].setOnAction(e -> checkAnswer(((Button) e.getSource()).getText().substring(0, 1)));
            optionsGrid.add(optionButtons[i], 0, i);
        }

        VBox centerBox = new VBox(20);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.getChildren().addAll(questionLabel, optionsGrid, feedbackLabel, scoreLabel);

        HBox bottomBox = new HBox(20);
        bottomBox.setAlignment(Pos.CENTER);
        Button nextButton = new Button("Next Question");
        nextButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 16px;");
        nextButton.setOnAction(e -> nextQuestion());
        bottomBox.getChildren().add(nextButton);

        questionRoot.setCenter(centerBox);
        questionRoot.setBottom(bottomBox);

        Scene scene = new Scene(questionRoot, 600, 400);
        primaryStage.setScene(scene);

        loadQuestion();
    }

    private void loadQuestion() {
        String[] question = questions[currentQuestionIndex];
        questionLabel.setText(question[0]);

        // Load images and videos here
        loadImages();
        loadVideos();

        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText(question[i + 1]);
        }

        feedbackLabel.setText("");
        scoreLabel.setText("Score: " + score + "/" + questions.length);
    }

    private void loadImages() {
        // Load images based on current question index
        // Example: String imagePath = questions[currentQuestionIndex][6];
        // Load image using JavaFX Image class and display it in appropriate UI component
    }

    private void loadVideos() {
        // Load videos based on current question index
        // Example: String videoPath = questions[currentQuestionIndex][7];
        // Load video using JavaFX MediaPlayer class and display it in appropriate UI component
    }

    private void checkAnswer(String selectedOption) {
        String correctAnswer = questions[currentQuestionIndex][5];
        if (selectedOption.equals(correctAnswer)) {
            feedbackLabel.setText("Correct!");
            score++;
        } else {
            feedbackLabel.setText("Incorrect. The correct answer is: " + correctAnswer);
        }
        disableOptions();
    }

    private void disableOptions() {
        for (Button button : optionButtons) {
            button.setDisable(true);
        }
    }

    private void nextQuestion() {
        currentQuestionIndex++;
        if (currentQuestionIndex < questions.length) {
            loadQuestion();
            enableOptions();
        } else {
            endGame();
        }
    }

    private void enableOptions() {
        for (Button button : optionButtons) {
            button.setDisable(false);
        }
    }

    private void endGame() {
        questionLabel.setText("Quiz completed!");
        feedbackLabel.setText("Your score: " + score + "/" + questions.length);
        scoreLabel.setText(""); // Clear the score label
        Button startOverButton = new Button("Start Over");
        startOverButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 16px;");
        startOverButton.setOnAction(e -> {
            currentQuestionIndex = 0; // Reset question index
            score = 0; // Reset score
            showQuestions((Stage) startOverButton.getScene().getWindow()); // Start over
        });
        Button startGameButton = new Button("Start Game");
        startGameButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 16px;");
        startGameButton.setOnAction(e -> {
            currentQuestionIndex = 0; // Reset question index
            score = 0; // Reset score
            showQuestions((Stage) startGameButton.getScene().getWindow()); // Start game
        });
        VBox centerBox = new VBox(20);
        centerBox.setAlignment(Pos.CENTER);
        Label thankYouLabel = new Label("Thank you for playing!");
        thankYouLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: #4CAF50;");
        centerBox.getChildren().addAll(thankYouLabel, new Label("Your final score is: " + score + "/" + questions.length), startOverButton, startGameButton);
        root.setCenter(centerBox);
        root.setBottom(null); // Clear the bottom box
    }

    public static void main(String[] args) {
        launch(args);
    }
}
