package me.spring.boot.biz;

public class WsResponse {

    private String answer;

    public WsResponse(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
