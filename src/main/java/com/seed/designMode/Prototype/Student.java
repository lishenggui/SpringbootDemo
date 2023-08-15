package com.seed.designMode.Prototype;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable,Cloneable{
    private String name;
    private String sex;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
