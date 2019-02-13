module gui {
    exports easytext.ui;
    requires javafx.graphics;
    requires javafx.controls;
    requires api;
    requires factory;
    requires analytics;
    uses easytext.api.Analyzer;
}