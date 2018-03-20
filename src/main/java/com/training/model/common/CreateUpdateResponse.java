package com.training.model.common;

import java.io.Serializable;

public class CreateUpdateResponse implements Serializable {
    private AbstractResponse abstractResponse;
    private boolean success;

    public AbstractResponse getAbstractResponse() {
        return abstractResponse;
    }

    public void setAbstractResponse(AbstractResponse abstractResponse) {
        this.abstractResponse = abstractResponse;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
