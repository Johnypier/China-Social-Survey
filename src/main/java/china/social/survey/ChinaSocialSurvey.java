package china.social.survey;

import java.util.List;
import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ChinaSocialSurvey extends Application {
        private int actionCase = 0;
        private int correctAnswers = 0;
        private static final String WHO = "Who is this person?";
        private static final String QUESTION_CSS = "-fx-fill: white;-fx-font-family: 'Fira Code';-fx-font-size: 25;-fx-font-weight: bold;-fx-alignment: center;";
        private static final int IMAGE_WIDTH = 600;
        private static final int IMAGE_HEIGHT = 400;
        private static final double SCENE_WIDTH = 1280;
        private static final double SCENE_HEIGHT = 720;
        private static final double HBOX_SPACING = 10;
        private static final double VBOX_SPACING = 20;

        // Images with different social credits.
        private Image m30Credits = createImage("-30-credits.jpg");
        private Image m100MCredits = createImage("-100m-credits.jpg");
        private Image m69420Credits = createImage("-69420-credits.jpg");
        private Image mCursedCredits = createImage("cursed-credits.jpeg");
        private Image mInfiniteCredits = createImage("minus-infinite-social-credit.gif");
        private Image p15Credits = createImage("15-credits.jpg");
        private Image p50Credits = createImage("50-credits.png");
        private Image p100Credits = createImage("100-credits.jpg");
        private Image p420Credits = createImage("420-credits.jpg");
        private Image p69420Credits = createImage("69420-credits.jpeg");

        // To run manually, use gradle run or ./gradlew run
        public static void main(String[] args) {
                launch(args);
        }

        @Override
        public void start(Stage primaryStage) throws Exception {
                // Initialize media plyer and play the music.
                var media = new Media(getClass().getClassLoader().getResource("Red Sun In The Sky.mp3").toString());
                var mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setAutoPlay(true);
                mediaPlayer.setVolume(0.05);
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

                // Initial image to display.
                var imageView = new ImageView(createImage("Wok.jpg"));

                // Initialize list for random credits display.
                var plusCredits = List.of(p15Credits, p100Credits, p420Credits, p50Credits, p69420Credits);
                var minusCredits = List.of(m100MCredits, m30Credits, m69420Credits, mCursedCredits, mInfiniteCredits);

                // Initialize question.
                var question = new Text(WHO);
                question.setStyle(QUESTION_CSS);

                // Initialize buttons and apply custom css.
                var buttonsCSSPath = getClass().getClassLoader().getResource("Buttons.css").toString();
                var mute = new Button("Turn OFF the audio");
                mute.getStylesheets().add(buttonsCSSPath);
                var unmute = new Button("Turn ON the audio");
                unmute.getStylesheets().add(buttonsCSSPath);
                unmute.setDisable(true);
                var firstQuestion = new Button("Wok");
                firstQuestion.getStylesheets().add(buttonsCSSPath);
                var secondQuestion = new Button("American Hero");
                secondQuestion.getStylesheets().add(buttonsCSSPath);
                var thirdQuestion = new Button("The Rock");
                thirdQuestion.getStylesheets().add(buttonsCSSPath);
                var restart = new Button("Restart");
                restart.getStylesheets().add(buttonsCSSPath);

                // Initialize buttons' actions.
                // After any answer actionCase should be increased by 1 for correct display of
                // the next question.
                // First answer is always correct, can be changed.
                mute.setOnAction(action -> {
                        mediaPlayer.setMute(true);
                        unmute.setDisable(false);
                        mute.setDisable(true);
                });
                unmute.setOnAction(action -> {
                        mediaPlayer.setMute(false);
                        unmute.setDisable(true);
                        mute.setDisable(false);
                });
                firstQuestion.setOnAction(action -> {
                        evaluateAnswer(question, firstQuestion, secondQuestion, thirdQuestion, imageView, plusCredits);
                        correctAnswers++;
                        actionCase++;
                });
                secondQuestion.setOnAction(action -> {
                        evaluateAnswer(question, firstQuestion, secondQuestion, thirdQuestion, imageView, minusCredits);
                        actionCase++;
                });
                thirdQuestion.setOnAction(action -> {
                        evaluateAnswer(question, firstQuestion, secondQuestion, thirdQuestion, imageView, minusCredits);
                        actionCase++;
                });
                restart.setOnAction(action -> {
                        try {
                                actionCase = 0;
                                correctAnswers = 0;
                                mediaPlayer.stop();
                                start(primaryStage);
                        } catch (Exception e) {
                                e.printStackTrace();
                        }
                });

                // Initialize and configure containers.
                var upperButtonsHBox = new HBox(mute, unmute);
                upperButtonsHBox.setAlignment(Pos.CENTER);
                upperButtonsHBox.setSpacing(HBOX_SPACING);
                var bottomButtonsHBox = new HBox(firstQuestion, secondQuestion, thirdQuestion);
                bottomButtonsHBox.setAlignment(Pos.CENTER);
                bottomButtonsHBox.setSpacing(HBOX_SPACING);
                var mainVBox = new VBox(upperButtonsHBox, imageView, question, bottomButtonsHBox, restart);
                mainVBox.setAlignment(Pos.CENTER);
                mainVBox.setSpacing(VBOX_SPACING);
                mainVBox.setBackground(new Background(new BackgroundImage(new Image(getClass().getClassLoader()
                                .getResourceAsStream("china-flag.gif")),
                                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                                new BackgroundSize(0, 0, false, false, false, true))));
                var scene = new Scene(mainVBox, SCENE_WIDTH, SCENE_HEIGHT);

                // Stage setup.
                primaryStage.setTitle("Social Survey");
                primaryStage.getIcons()
                                .add(new Image(getClass().getClassLoader().getResourceAsStream("china-icon.png")));
                primaryStage.setScene(scene);
                primaryStage.show();
        }

        /**
         * Applies changes to the scene according to the current action counter.
         * At the end shows user the results of the survey.
         * 
         * @param question       - Link to text of the question.
         * @param firstQuestion  - Link to the first button.
         * @param secondQuestion - Link to the second button.
         * @param thirdQuestion  - Link to the third button.
         * @param imageView      - Link to the main ImageView.
         * @param listCredits    - List of images for pop-ups.
         */
        private void evaluateAnswer(Text question, Button firstQuestion, Button secondQuestion, Button thirdQuestion,
                        ImageView imageView, List<Image> listCredits) {
                switch (actionCase) {
                        case (0) -> applyChanges(question, firstQuestion, secondQuestion, thirdQuestion, imageView,
                                        listCredits);
                        case (1) -> applyChanges(question, firstQuestion, secondQuestion, thirdQuestion, imageView,
                                        listCredits);
                        case (2) -> applyChanges(question, firstQuestion, secondQuestion, thirdQuestion, imageView,
                                        listCredits);
                        case (3) -> applyChanges(question, firstQuestion, secondQuestion, thirdQuestion, imageView,
                                        listCredits);
                        case (4) -> applyChanges(question, firstQuestion, secondQuestion, thirdQuestion, imageView,
                                        listCredits);
                        case (5) -> {
                                firstQuestion.setDisable(true);
                                secondQuestion.setDisable(true);
                                thirdQuestion.setDisable(true);
                                question.setText("Thanks for your personal data!");
                                imageView.setImage(correctAnswers < 3 ? createImage("shame.jpg")
                                                : createImage("proud.jpeg"));
                        }
                        default -> throw new IllegalArgumentException("Unsupported option detected!");
                }
        }

        /**
         * Applies changes to the scene according to the current action counter.
         * 
         * @param question       - Link to text of the question.
         * @param firstQuestion  - Link to the first button.
         * @param secondQuestion - Link to the second button.
         * @param thirdQuestion  - Link to the third button.
         * @param imageView      - Link to the main ImageView.
         * @param listCredits    - List of images for pop-ups.
         */
        private void applyChanges(Text question, Button firstQuestion, Button secondQuestion, Button thirdQuestion,
                        ImageView imageView, List<Image> listCredits) {
                var random = new Random();
                popUp(listCredits.get(random.nextInt(0, 4)));
                update(question, firstQuestion, secondQuestion, thirdQuestion, imageView);
        }

        /**
         * Creates a pop-up window with the given image.
         * 
         * @param img - Image for the pop-up window.
         */
        private void popUp(Image img) {
                var imageView = new ImageView(img);
                VBox vBox = new VBox(imageView);
                vBox.setAlignment(Pos.CENTER);
                vBox.setStyle("-fx-background-color: black");
                var stage = new Stage();
                var popUpScene = new Scene(vBox, 600, 400);
                popUpScene.setFill(Color.BLACK);
                stage.setScene(popUpScene);
                stage.setResizable(false);
                stage.show();
        }

        /**
         * Updates the scene according to current action counter.
         * 
         * @param t  Link to the text of the question.
         * @param b1 Link to the first button.
         * @param b2 Link to the second button.
         * @param b3 Link to the third button.
         * @param iw Link to the main ImageView.
         */
        private void update(Text t, Button b1, Button b2, Button b3, ImageView iw) {
                switch (actionCase) {
                        case (0) -> changeData(t, b1, b2, b3, iw,
                                        List.of("Is this funny?", "No", "Yes", "我要他死", "Xi the Pooh.jpg"));
                        case (1) ->
                                changeData(t, b1, b2, b3, iw, List.of(WHO, "Eggman", "退化", "Eggplant", "Eggman.jpg"));
                        case (2) -> changeData(t, b1, b2, b3, iw,
                                        List.of(WHO, "John Xina", "Johnny Depp", "John Cena", "John Xina.jpg"));
                        case (3) -> changeData(t, b1, b2, b3, iw, List.of("What is this?", "Part of China",
                                        "Independent Taiwan", "Chinese village", "taiwan-flag.jpg"));
                        case (4) ->
                                changeData(t, b1, b2, b3, iw, List.of("Do you support Super Idol?", "Yes", "No",
                                                "It is trash", "super-idol.jpg"));
                        default -> throw new IllegalArgumentException("Unsupported option detected!");
                }
        }

        /**
         * Shows the next part of the survey.
         * Changes buttons, questions text and replaces old image.
         * 
         * @param t  Link to text of the question.
         * @param b1 Link to first button.
         * @param b2 Link to second button.
         * @param b3 Link to third button.
         * @param iw Link to main ImageView.
         * @param l  List of text to be applied for given elements.
         */
        private void changeData(Text t, Button b1, Button b2, Button b3, ImageView iw, List<String> l) {
                t.setText(l.get(0));
                b1.setText(l.get(1));
                b2.setText(l.get(2));
                b3.setText(l.get(3));
                iw.setImage(createImage(l.get(4)));
        }

        /**
         * Creates image with specific size and parameters.
         * 
         * @param fileName Name of the image file.
         * @return Configured image.
         */
        private Image createImage(String fileName) {
                return new Image(getClass().getClassLoader().getResourceAsStream(fileName), IMAGE_WIDTH, IMAGE_HEIGHT,
                                true, true);
        }
}
