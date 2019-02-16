package easytext.algorithms;

public enum Algorithm {
    FleschKincaid("Flesch-Kincaid"),
    Coleman("Coleman-Liau");
    private String value;

    Algorithm(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
