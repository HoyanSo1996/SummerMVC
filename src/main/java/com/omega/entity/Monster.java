package com.omega.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class Monster
 *
 * @author KennySo
 * @date 2024/7/11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Monster {

    private Integer id;
    private String name;
    private String skill;
    private Integer age;
}
