module ChinaSocialSurvey {
    exports china.social.survey;

    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.media;

    opens china.social.survey to javafx.controls, javafx.graphics, javafx.media;
}
