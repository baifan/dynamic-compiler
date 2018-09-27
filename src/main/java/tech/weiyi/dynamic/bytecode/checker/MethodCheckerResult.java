package tech.weiyi.dynamic.bytecode.checker;

import tech.weiyi.dynamic.bytecode.analysis.MethodCallElement;

import java.util.HashSet;
import java.util.Set;

public class MethodCheckerResult {

    private Boolean success;

    private Set<MethodCallElement> unsupportMethods;

    private String errorMessage;

    public MethodCheckerResult() {
        super();
        unsupportMethods = new HashSet<>();
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void addUnsupportMethod(MethodCallElement methodCallElement) {
        this.unsupportMethods.add(methodCallElement);
    }

    public Set<MethodCallElement> getUnsupportMethods() {
        return unsupportMethods;
    }

    public void setUnsupportMethods(Set<MethodCallElement> unsupportMethods) {
        this.unsupportMethods = unsupportMethods;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
