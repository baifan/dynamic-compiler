package tech.weiyi.dynamic.bytecode.analysis;

import java.util.Objects;

public class ClazzElement {

    /**
     * 类的名称
     */
    private String clazz;

    /**
     * @see ClazzElementType#id
     */
    private Integer type;

    /**
     * Method.name
     * Field.name
     */
    private String name;


    /**
     * 完整描述符
     */
    private String desc;

    /**
     * 公共构造函数
     */
    public ClazzElement() {
        super();
    }


    public ClazzElement(String clazz, Integer type, String name, String desc) {
        this.clazz = clazz;
        this.type = type;
        this.name = name;
        this.desc = desc;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClazzElement that = (ClazzElement) o;
        return Objects.equals(clazz, that.clazz) &&
                Objects.equals(type, that.type) &&
                Objects.equals(name, that.name) &&
                Objects.equals(desc, that.desc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clazz, type, name, desc);
    }

    @Override
    public String toString() {
        return "ClazzElement{" +
                "clazz='" + clazz + '\'' +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
