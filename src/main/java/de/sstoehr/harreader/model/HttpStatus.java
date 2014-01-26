package de.sstoehr.harreader.model;

public enum HttpStatus {

    OK(200, "OK"), NO_CONTENT(204, "No Content"),

    MOVED_PERMANENTLY(301, "Moved Permanently"), FOUND(302, "Found"), SEE_OTHER(303, "See Other"), NOT_MODIFIED(304, "Not Modified");



    private int code;
    private String text;

    private HttpStatus(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public int getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    public static HttpStatus getByCode(int code) {
        for (HttpStatus httpStatus : HttpStatus.values()) {
            if (httpStatus.getCode() == code) {
                return httpStatus;
            }
        }
        throw new IllegalArgumentException("Unknown HttpStatus: " + code + ".");
    }

    public static HttpStatus getByText(String text) {
        for (HttpStatus httpStatus : HttpStatus.values()) {
            if (httpStatus.getText().equalsIgnoreCase(text)) {
                return httpStatus;
            }
        }
        throw new IllegalArgumentException("Unknown HttpStatus: " + text + ".");
    }
}
