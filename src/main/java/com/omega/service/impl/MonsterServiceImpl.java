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

    private final List<Monster> monsterList = new ArrayList<>();

    {
        monsterList.add(new Monster(100, "蜘蛛精", "吐丝", 20));
        monsterList.add(new Monster(200, "牛魔王", "芭蕉扇", 40));
        monsterList.add(new Monster(300, "红孩儿", "三味之火", 9));
        monsterList.add(new Monster(400, "白骨精", "美人计", 28));
    }

    @Override
    public List<Monster> queryList() {
        return monsterList;
    }

    @Override
    public List<Monster> queryListByName(String name) {
        List<Monster> monsterListResult = new ArrayList<>();
        for (Monster monster : monsterList) {
            if (monster.getName().contains(name)) {
                monsterListResult.add(monster);
            }
        }
        return monsterListResult;
    }

    @Override
    public List<Monster> queryListByIdAndName(Integer id, String name) {
        List<Monster> monsterListResult = new ArrayList<>();
        for (Monster monster : monsterList) {
            if (monster.getId().equals(id) && monster.getName().contains(name)) {
                monsterListResult.add(monster);
            }
        }
        return monsterListResult;
    }
}
