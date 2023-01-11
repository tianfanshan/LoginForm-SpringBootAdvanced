package com.stf.form;

public interface FormConverter<S,T> {
    T convert(S s);
}
