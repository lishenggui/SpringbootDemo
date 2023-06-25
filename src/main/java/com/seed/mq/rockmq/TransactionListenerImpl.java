package com.seed.mq.rockmq;

import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.messaging.Message;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
/*事务消息监听实现*/
@RocketMQTransactionListener
class TransactionListenerImpl implements RocketMQLocalTransactionListener {
    private AtomicInteger transactionIndex = new AtomicInteger(0);

    private ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<String, Integer>();

    //Broker预提交成功后回调Producer的executeLocalTransaction方法
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        String transId = (String) msg.getHeaders().get(RocketMQHeaders.TRANSACTION_ID);
        System.out.printf("#### executeLocalTransaction is executed, msg TransactionId=%s %n",
                transId);
        int value = transactionIndex.getAndIncrement();
        int status = value % 3;
        localTrans.put(transId, status);
        if (status == 0) {
            // Return local transaction with success(commit), in this case,
            // this message will not be checked in checkLocalTransaction()
            System.out.printf("    # COMMIT # Simulating msg %s related local transaction exec succeeded! ### %n", msg.getPayload());
            return RocketMQLocalTransactionState.COMMIT;
        }

        if (status == 1) {
            // Return local transaction with failure(rollback) , in this case,
            // this message will not be checked in checkLocalTransaction()
            System.out.printf("    # ROLLBACK # Simulating %s related local transaction exec failed! %n", msg.getPayload());
            return RocketMQLocalTransactionState.ROLLBACK;
        }

        System.out.printf("    # UNKNOW # Simulating %s related local transaction exec UNKNOWN!");
        return RocketMQLocalTransactionState.UNKNOWN;
    }

    //Broker超时未接受到Producer的反馈，会定时重试调用Producer.checkLocalTransaction，Producer会根据自己的执行情况Ack给Broker
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(org.springframework.messaging.Message msg) {
        String transId = (String) msg.getHeaders().get(RocketMQHeaders.TRANSACTION_ID);
        RocketMQLocalTransactionState retState = RocketMQLocalTransactionState.COMMIT;
        Integer status = localTrans.get(transId);
        if (null != status) {
            switch (status) {
                case 0:
                    retState = RocketMQLocalTransactionState.UNKNOWN;
                    break;
                case 1:
                    retState = RocketMQLocalTransactionState.COMMIT;
                    break;
                case 2:
                    retState = RocketMQLocalTransactionState.ROLLBACK;
                    break;
            }
        }
        System.out.printf("------ !!! checkLocalTransaction is executed once," +
                        " msgTransactionId=%s, TransactionState=%s status=%s %n",
                transId, retState, status);
        return retState;
    }

}