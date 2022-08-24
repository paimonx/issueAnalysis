package com.paimonx;

import java.lang.instrument.Instrumentation;

/**
 * @author xu
 * @date 2022/7/26
 */
public class Agent {


    public static void premain(String agentArgs, Instrumentation instrumentation) {

        instrumentation.addTransformer(new Patch_Zulu11_35_13());
    }
}
