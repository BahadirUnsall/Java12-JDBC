package com.bahadir.repository;

import java.util.List;

public interface ICrud  <T>{
    void saveAll(List<T> t);
}
