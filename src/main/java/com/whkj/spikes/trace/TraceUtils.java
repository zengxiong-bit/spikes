package com.whkj.spikes.trace;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.MDC;

/**
 *
 * 系统运行时打印方便调试与追踪信息的工具类.
 * 使用MDC存储traceID, 一次trace中所有日志都自动带有该ID,
 * 可以方便的用grep命令在日志文件中提取该trace的所有日志.
 */
public class TraceUtils {

	private static final String TRACE_ID_KEY = "traceId";
	private static final int TRACE_ID_LENGTH = 8;

	/**
	 * 开始Trace, 默认生成本次Trace的ID(8字符长)并放入MDC.
	 */
	public static void beginTrace() {
		String traceId = RandomStringUtils.randomAlphanumeric(TRACE_ID_LENGTH);
		MDC.put(TRACE_ID_KEY, traceId);
	}

	/**
	 * 开始Trace, 将traceId放入MDC.
	 */
	public static void beginTrace(String traceId) {
		MDC.put(TRACE_ID_KEY, traceId);
	}

	/**
	 * 结束一次Trace.
	 * 清除traceId.
	 */
	public static void endTrace() {
		MDC.remove(TRACE_ID_KEY);
	}
}
