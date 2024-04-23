package mtyx.utils;

import cn.hutool.core.util.IdUtil;

import java.util.concurrent.*;

/**
 * @author guokun
 * @date 2024/4/21 11:05
 */
public class ExecutorUtils {
    private static ThreadPoolExecutor EXECUTOR = null;

    static {
        open();
        EXECUTOR.allowCoreThreadTimeOut(true);
    }

    public static Executor getExecutor() {
        return EXECUTOR;
    }

    public static void close() {
        EXECUTOR.shutdownNow();
    }

    public static boolean isClosed() {
        return EXECUTOR.isShutdown();
    }

    public static void open() {
        if (EXECUTOR == null || EXECUTOR.isShutdown() || EXECUTOR.isTerminated()) {
            EXECUTOR = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                    Runtime.getRuntime().availableProcessors(), 1L, TimeUnit.MINUTES,
                    new LinkedBlockingQueue<>(), r -> new Thread(r, IdUtil.fastSimpleUUID()));
        }
    }
}
