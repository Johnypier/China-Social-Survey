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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {
        private static final String WHO = "Who is this person?";
        private int actionCase = 0;
        private int correctAnswers = 0;
        private int imageWidth = 600;
        private int imageHeight = 400;

        /*
         * Images with different social credits.
         * m - for minus.
         * p - for plus.
         */
        private Image m30Credits = new Image(getClass().getClassLoader().getResourceAsStream("-30-credits.jpg"),
                        imageWidth,
                        imageHeight, true, true);
        private Image m100MCredits = new Image(getClass().getClassLoader().getResourceAsStream("-100m-credits.jpg"),
                        imageWidth, imageHeight, true, true);
        private Image m69420Credits = new Image(getClass().getClassLoader().getResourceAsStream("-69420-credits.jpg"),
                        imageWidth, imageHeight, true, true);
        private Image mCursedCredits = new Image(getClass().getClassLoader().getResourceAsStream("cursed-credits.jpeg"),
                        imageWidth, imageHeight, true, true);
        private Image mInfiniteCredits = new Image(
                        getClass().getClassLoader().getResourceAsStream("minus-infinite-social-credit.gif"), imageWidth,
                        imageHeight, true, true);
        private Image p15Credits = new Image(getClass().getClassLoader().getResourceAsStream("15-credits.jpg"),
                        imageWidth, imageHeight, true, true);
        private Image p50Credits = new Image(getClass().getClassLoader().getResourceAsStream("50-credits.png"),
                        imageWidth, imageHeight, true, true);
        private Image p100Credits = new Image(getClass().getClassLoader().getResourceAsStream("100-credits.jpg"),
                        imageWidth, imageHeight, true, true);
        private Image p420Credits = new Image(getClass().getClassLoader().getResourceAsStream("420-credits.jpg"),
                        imageWidth, imageHeight, true, true);
        private Image p69420Credits = new Image(getClass().getClassLoader().getResourceAsStream("69420-credits.jpeg"),
                        imageWidth, imageHeight, true, true);

        @Override
        public void start(Stage primaryStage) throws Exception {
                primaryStage.setTitle("Social Survey");
                primaryStage.getIcons()
                                .add(new Image(getClass().getClassLoader().getResourceAsStream("china-icon.png")));

                var imageView = new ImageView(
                                new Image(getClass().getClassLoader().getResourceAsStream("Wok.jpg"), imageWidth,
                                                imageHeight, true,
                                                true));

                var plusCredits = List.of(p15Credits, p100Credits, p420Credits, p50Credits, p69420Credits);
                var minusCredits = List.of(m100MCredits, m30Credits, m69420Credits, mCursedCredits, mInfiniteCredits);

                var question = new Text(WHO);
                question.setStyle("-fx-fill: white;" + "-fx-font-family: 'Fira Code';" + "-fx-font-size: 25;"
                                + "-fx-font-weight: bold;" + "-fx-alignment: center;");

                var buttonsCSSPath = getClass().getResource("Buttons.css").toString();
                var firstQuestion = new Button("Wok");
                firstQuestion.getStylesheets().add(buttonsCSSPath);
                var secondQuestion = new Button("American Hero");
                secondQuestion.getStylesheets().add(buttonsCSSPath);
                var thirdQuestion = new Button("The Rock");
                thirdQuestion.getStylesheets().add(buttonsCSSPath);
                var restart = new Button("Restart");
                restart.getStylesheets().add(buttonsCSSPath);

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
                                start(primaryStage);
                        } catch (Exception e) {
                                e.printStackTrace();
                        }
                });

                var bottomButtonsHBox = new HBox(firstQuestion, secondQuestion, thirdQuestion);
                bottomButtonsHBox.setAlignment(Pos.CENTER);
                bottomButtonsHBox.setSpacing(10);
                var mainVBox = new VBox(imageView, question, bottomButtonsHBox, restart);
                mainVBox.setAlignment(Pos.CENTER);
                mainVBox.setSpacing(20);
                mainVBox.setBackground(new Background(new BackgroundImage(new Image(getClass().getClassLoader()
                                .getResourceAsStream("china-flag.gif")),
                                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                                new BackgroundSize(0, 0, false, false, false, true))));

                Scene scene = new Scene(mainVBox, 1280, 720);
                primaryStage.setScene(scene);
                primaryStage.show();
        }

        /**
         * Applies changes to the scene according to current action counter.
         * At the final end shows user the result of the survey.
         * See its helper methods for better understanding of the logic.
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
                        case (0):
                                applyChanges(question, firstQuestion, secondQuestion, thirdQuestion, imageView,
                                                listCredits);
                                break;
                        case (1):
                                applyChanges(question, firstQuestion, secondQuestion, thirdQuestion, imageView,
                                                listCredits);
                                break;
                        case (2):
                                applyChanges(question, firstQuestion, secondQuestion, thirdQuestion, imageView,
                                                listCredits);
                                break;
                        case (3):
                                applyChanges(question, firstQuestion, secondQuestion, thirdQuestion, imageView,
                                                listCredits);
                                break;
                        case (4):
                                applyChanges(question, firstQuestion, secondQuestion, thirdQuestion, imageView,
                                                listCredits);
                                break;
                        case (5):
                                firstQuestion.setDisable(true);
                                secondQuestion.setDisable(true);
                                thirdQuestion.setDisable(true);
                                question.setText("Thanks for your personal data!");
                                if (correctAnswers < 3) {
                                        imageView.setImage(new Image(
                                                        getClass().getClassLoader().getResourceAsStream("shame.jpg"),
                                                        imageWidth, imageHeight, true, true));
                                } else {
                                        imageView.setImage(new Image(
                                                        getClass().getClassLoader().getResourceAsStream("proud.jpeg"),
                                                        imageWidth, imageHeight, true, true));
                                }
                                break;
                        default:
                                break;
                }
        }

        /**
         * Applies changes to the scene according to current action counter.
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
                Random random = new Random();
                popUp(listCredits.get(random.nextInt(0, 4)));
                update(question, firstQuestion, secondQuestion, thirdQuestion, imageView);
        }

        /**
         * Creates pop-up window with given image.
         * 
         * @param img - Image for pop-up window.
         */
        private void popUp(Image img) {
                ImageView imageView = new ImageView(img);
                VBox vBox = new VBox(imageView);
                vBox.setAlignment(Pos.CENTER);
                vBox.setStyle("-fx-background-color: black");
                var stage = new Stage();
                Scene popUpScene = new Scene(vBox, 600, 400);
                popUpScene.setFill(Color.BLACK);
                stage.setScene(popUpScene);
                stage.setResizable(false);
                stage.show();
        }

        /**
         * Updates scene according to current action counter.
         * 
         * @param t  - Link to text of the question.
         * @param b1 - Link to first button.
         * @param b2 - Link to second button.
         * @param b3 - Link to third button.
         * @param iw - Link to main ImageView.
         */
        private void update(Text t, Button b1, Button b2, Button b3, ImageView iw) {
                switch (actionCase) {
                        case (0):
                                changeData(t, b1, b2, b3, iw,
                                                List.of("Is this funny?", "No", "Yes", "我要他死", "Xi the Pooh.jpg"));
                                break;
                        case (1):
                                changeData(t, b1, b2, b3, iw, List.of(WHO, "Eggman", "退化", "Eggplant", "Eggman.jpg"));
                                break;
                        case (2):
                                changeData(t, b1, b2, b3, iw,
                                                List.of(WHO, "John Xina", "Johnny Depp", "John Cena", "John Xina.jpg"));
                                break;
                        case (3):
                                changeData(t, b1, b2, b3, iw, List.of("What is this?", "Part of China",
                                                "Independent Taiwan", "Chinese village", "taiwan-flag.jpg"));
                                break;
                        case (4):
                                changeData(t, b1, b2, b3, iw, List.of("Do you support Super Idol?", "Yes", "No",
                                                "It is trash", "super-idol.jpg"));
                                break;
                        default:
                                break;
                }
        }

        /**
         * Changes data to the next part of the survey.
         * Changes buttons/question text and replaces old image.
         * 
         * @param t  - Link to text of the question.
         * @param b1 - Link to first button.
         * @param b2 - Link to second button.
         * @param b3 - Link to third button.
         * @param iw - Link to main ImageView.
         * @param l  - List of text to be applied for given elements.
         */
        private void changeData(Text t, Button b1, Button b2, Button b3, ImageView iw, List<String> l) {
                t.setText(l.get(0));
                b1.setText(l.get(1));
                b2.setText(l.get(2));
                b3.setText(l.get(3));
                iw.setImage(new Image(
                                getClass().getClassLoader().getResourceAsStream(l.get(4)),
                                imageWidth, imageHeight, true, true));
        }
}
