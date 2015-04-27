package com.xtmit.distribution.net;

/**
 * 继承此接口实现网络判断，实现具体的网络断开和连接的操作
 * @author xumin
 *
 */
public interface INet {
	public void DoSomethingsCon();
	public void DoSomethingsDiscon();
}
