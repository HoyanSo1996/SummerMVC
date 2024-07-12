package com.omega.service;

import com.omega.entity.Monster;

import java.util.List;

/**
 * Interface MonsterService
 *
 * @author KennySo
 * @date 2024/7/11
 */
public interface MonsterService {

    List<Monster> queryList();

    List<Monster> queryListByName(String name);

    List<Monster> queryListByIdAndName(Integer id, String name);
}
