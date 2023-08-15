package com.seed.designMode.Prototype;

import lombok.Data;

@Data
public class QianClone  implements  Cloneable{
    String desc;
    Student student;
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        QianClone qianClone = new QianClone();
        qianClone.setDesc("学校");
        Student student = new Student();
        student.setName("张三");
        student.setSex("18");
        qianClone.setStudent(student);
        QianClone qianClone2 = (QianClone) qianClone.clone();
        qianClone2.setDesc("医院");
        qianClone2.getStudent().setName("李四");
        qianClone2.getStudent().setSex("30");
        System.out.println(qianClone.toString());
        System.out.println(qianClone2.toString());

    }
}
