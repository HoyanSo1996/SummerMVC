package com.omega.service.impl;

import com.omega.entity.Monster;
import com.omega.service.MonsterService;
import com.omega.summermvc.annotation.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Class MonsterServiceImpl
 *
 * @author KennySo
 * @date 2024/7/11
 */
@Service
public class MonsterServiceImpl implements MonsterService {

    @Override
    public List<Monster> queryList() {
        List<Monster> monsterList = new ArrayList<>();
        monsterList.add(new Monster(100, "蜘蛛精", "吐丝", 20));
        monsterList.add(new Monster(200, "牛魔王", "芭蕉扇", 40));
        return monsterList;
    }
}
