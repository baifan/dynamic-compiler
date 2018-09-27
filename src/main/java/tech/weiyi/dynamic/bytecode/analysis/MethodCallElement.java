package tech.weiyi.dynamic.bytecode.analysis;

import java.util.Objects;

public class MethodCallElement {

    private Integer type;

    private String owner;

    private String callName;

    private String desc;

    public MethodCallElement() {
        super();
    }

    public MethodCallElement(Integer type, String owner, String callName, String desc) {
        this.type = type;
        this.owner = owner;
        this.callName = callName;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public MethodCallElement setType(Integer type) {
        this.type = type;
        return this;
    }

    public String getOwner() {
        return owner;
    }

    public MethodCallElement setOwner(String owner) {
        this.owner = owner;
        return this;
    }

    public String getCallName() {
        return callName;
    }

    public MethodCallElement setCallName(String callName) {
        this.callName = callName;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public MethodCallElement setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MethodCallElement that = (MethodCallElement) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(owner, that.owner) &&
                Objects.equals(callName, that.callName) &&
                Objects.equals(desc, that.desc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, owner, callName, desc);
    }

    @Override
    public String toString() {
        return "MethodCallElement{" +
                "type=" + type +
                ", owner='" + owner + '\'' +
                ", callName='" + callName + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
