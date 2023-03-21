package modules.be.client.entity;

import lombok.Getter;

@Getter
public enum Sort {
    RECENCY("recency", "date"), ACCURACY("accuracy","sim");

    private String kakao;
    private String naver;
    Sort(String kakao, String naver) {
        this.kakao = kakao;
        this.naver = naver;
    }
}
