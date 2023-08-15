package com.seed.designMode.Prototype;

import lombok.Data;

@Data
public class ShenClone implements  Cloneable{
    String desc;
    Student student;
    @Override
    protected Object clone() throws CloneNotSupportedException {
        ShenClone shenClone = (ShenClone) super.clone();
        shenClone.setStudent((Student) this.student.clone());
        return shenClone;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        ShenClone qianClone = new ShenClone();
        qianClone.setDesc("学校");
        Student student = new Student();
        student.setName("张三");
        student.setSex("18");
        qianClone.setStudent(student);
        ShenClone qianClone2 = (ShenClone) qianClone.clone();
        qianClone2.setDesc("医院");
        qianClone2.getStudent().setName("李四");
        qianClone2.getStudent().setSex("30");
        System.out.println(qianClone.toString());
        System.out.println(qianClone2.toString());

    }
}
