package com.omega.summermvc.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;

/**
 * Class SummerHandler
 *
 * @author KennySo
 * @date 2024/7/11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SummerHandler {

    private Object controller;
    private Method method;
}
