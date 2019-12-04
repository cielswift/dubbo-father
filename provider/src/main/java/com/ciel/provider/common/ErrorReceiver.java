//package com.ciel.provider.common;
//
//import org.springframework.amqp.core.ExchangeTypes;
//import org.springframework.amqp.rabbit.annotation.*;
//import org.springframework.stereotype.Component;
//
///**
// * 消息接收者 消息接收者
// *
// * @RabbitListener bindings:绑定队列
// *
// * @QueueBinding value:绑定队列的名称
// *               exchange:配置交换器
// *               key:路由键
// * @Queue value:配置队列名称
// *        autoDelete:是否是一个可删除的临时队列
// *
// * @Exchange value:为交换器起个名称
// *           type:指定具体的交换器类型
// */
//@Component
//@RabbitListener(bindings = @QueueBinding(
//		exchange = @Exchange(value = "${mq.config.exchange}", type = ExchangeTypes.DIRECT),
//
//		value = @Queue(value = "${mq.config.queue.error}", autoDelete = "false"),
//
//		key = "${mq.config.queue.error.routing.key}")
//)
//public class ErrorReceiver {
//
//	/**
//	 * 监听指定的消息队列
//	 *
//	 * @param msg
//	 * @throws Exception
//	 */
//	@RabbitHandler
//	public void receiveMsg(String msg) throws Exception {
//		System.out.println("Error------"+msg);
//		// 手动抛出异常
//		//throw new Exception();
//	}
//}
//
