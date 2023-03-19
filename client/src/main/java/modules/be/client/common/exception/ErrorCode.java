package modules.be.client.common.exception;

public enum ErrorCode {
    UNKNOWN("UNKNOWN","알수 없는 에러"),
    INVALID_PARAM("INVALID_PARAM","파라미터 입력 오류");




    private String code;
    private String message;

    ErrorCode(String code, String message) {
    }

    public String getCode(){return this.code;}
    public String getMessage(){return this.message;}

}
