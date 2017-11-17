package enums;

public enum BizExceptionEnum {
    FOO(0, "FOO");

    private int errorCode;
    private String errorMsg;

    BizExceptionEnum(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
