package modules.be.api.common;

import lombok.Getter;

@Getter
public enum Sort {
    RECENCY("recency", "정확도"), ACCURACY("accuracy","최신순");

    private String code;
    private String value;
    Sort(String code, String value) {
        this.code = code;
        this.value = value;
    }
}
