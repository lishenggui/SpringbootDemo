package com.seed.designMode.Builder;

public class StudentBuilder {
    private  Student student= new Student();

    public  StudentBuilder(){
    }

    public  StudentBuilder(Student student){
        this.student = student;
    }

    StudentBuilder bulidName(String name){
        student.setName(name);
        return  this;
    }

    StudentBuilder bulidAge(String age){
        student.setSex(age);
        return  this;
    }

    Student build(){
        return  student;
    }


    public static void main(String[] args) {
        Student student = new StudentBuilder().bulidAge("18").bulidName("张三").build();
        System.out.println(student.toString());
    }

}
