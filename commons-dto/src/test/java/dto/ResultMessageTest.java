package dto;

import gy.commons.dto.ResultMessage;
import org.junit.jupiter.api.Test;

/**
 * @ClassName ResultMessageTest.java
 * @Author guofeng
 * @Description
 * @Version 1.0.0
 * @Date 2020年05月08日 16:14:00
 */
class ResultMessageTest {

    @Test
    void consoleResultMessage() {
        ResultMessage<String> resultMessage1=new ResultMessage<>(ResultMessage.ResultType.SUCCESS,"my message");
        ResultMessage<String> resultMessage2=new ResultMessage<>(ResultMessage.ResultType.FAIL);
        ResultMessage<String> resultMessage3=new ResultMessage<>(ResultMessage.ResultType.EXCEPTION,"my data");

        System.out.println(resultMessage1.toString());
        System.out.println(resultMessage2.toString());
        System.out.println(resultMessage3.toString());
    }
}