# 工程简介
 这只是个简单的Spring Boot  Web项目 来复现问题

 只要使用zulu jdk 11.35.13 运行该工程，即可复现问题。

 [详细分析](../zulu-jdk-memory-leak/zulu-jdk-memory-leak.md)


# 目录结构

```
│  .gitignore
│  java_pid884.hprof                                                           // 分析时的堆转储文件
│  pom.xml
│  README.md
│  zulu 11.35.13内存泄漏.jmx                                                    // jmeter 测试案例
│  zulu-jdk-memory-leak.md                                                    //  具体分析
│  
├─src                                                                         // demo src
│  ├─main
│  │  ├─java
│  │  │  └─com
│  │  │      └─paimonx
│  │  │          └─zulu
│  │  │              │  ZuluJdkMemoryLeakApplication.java
│  │  │              │  
│  │  │              ├─controller
│  │  │              │      DemoController.java
│  │  │              │      
│  │  │              └─util
│  │  │                      AesUtils.java
│  │  │                      
│  │  └─resources
│  │          application.properties
│  │          
│  └─test
│      └─java
│          └─com
│              └─paimonx
│                  └─zulu
│                          ZuluJdkMemoryLeakApplicationTests.java
│                          
└─zulu-jdk-memory-leak.assets                                                // md images
        image-20220824153146644.png
        image-20220824153452456.png
        image-20220824153704616.png
        image-20220824154415306.png
        image-20220824155500024.png
        
```


