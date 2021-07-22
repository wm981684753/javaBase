package com.junly.mybatis.controller.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class Producer {

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        //设置 RabbitMQ 地址
        factory.setHost("localhost");
        //建立到代理服务器到连接
        Connection conn = factory.newConnection();
        //获得信道
        Channel channel = conn.createChannel();
        //声明交换器
        String exchangeName = "hello-exchange";
        //声明路由
        String routingKey = "hola";
        //发布消息
        byte[] messageBodyBytes = "message-content".getBytes();
        Map<String, String> msg = new HashMap<String, String>();
        msg.put("type", "message");
        msg.put("msg", "消息数据");
        String msgString = JSON.toJSONString(msg);//使用自带JSON处理
//        JSONObject jsonobj = (JSONObject) JSONObject.toJSON(msg);//使用fastJson处理
//        String msgString = jsonobj.toString();
        channel.basicPublish(exchangeName, routingKey, null, msgString.getBytes());

        //设置消息优先级发送
        //在消费端速度大于生产端速度，且broker中没有消息堆积的话，对发送的消息设置优先级也没什么实际意义，
        // 因为发送端刚发送完一条消息就被消费端消费了，那么就相当于broker至多只有一条消息，那么对于单条消息来说优先级是没有什么意义的
        Map<String, Object> max = new HashMap<String, Object>();
        max.put("x-max-priority", 10);
        channel.queueDeclare("queue_priority", true, false, false, max);
        for (int i = 0; i < 10; i++) {
            AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
            if (i % 2 != 0) builder.priority(5);
            AMQP.BasicProperties properties = builder.build();
            channel.basicPublish(exchangeName, routingKey, properties, ("messages-" + i).getBytes());
        }

        //关闭频道
        channel.close();
        conn.close();
    }
}
