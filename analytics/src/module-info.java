module analytics {
    exports easytext.analytics;
    exports easytext.algorithms;
    exports easytext.tools;
    requires api;
    provides easytext.api.Analyzer with easytext.analytics.FleschKincaid, easytext.analytics.Coleman;
}