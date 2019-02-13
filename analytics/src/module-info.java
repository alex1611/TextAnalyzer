module analytics {
    exports easytext.analytics;
    exports easytext.algorithms;
    requires api;
    provides easytext.api.Analyzer with
            easytext.analytics.FleschKincaid;
}