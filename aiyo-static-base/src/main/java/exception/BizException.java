package exception;

import enums.BizExceptionEnum;

public class BizException extends Exception {
    private BizExceptionEnum exceptionEnum;

    public BizException(BizExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }

    public BizExceptionEnum getExceptionEnum() {
        return exceptionEnum;
    }
}
