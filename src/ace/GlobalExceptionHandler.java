/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace;

import ace.interfaces.ExceptionsHandler;
import ace.interfaces.ExceptionsMonitor;
import java.util.ArrayList;

/**
 * Global exception handler.
 */
public class GlobalExceptionHandler extends Ace implements ExceptionsHandler {

	private Thread.UncaughtExceptionHandler _lastUncaughtExceptionHandler = null;
	private boolean _printUncaughtExceptionsStackTrace = true;
	private int _exceptionsHistoryMaximum = 100;
	private final ArrayList<Throwable> _exceptionsThrown = new ArrayList();
	private boolean _hadException = false;
	private Throwable _lastException = null;
	private Thread _lastExceptionThread;
	private long _lastExceptionTimestamp = -1;
	private ExceptionsMonitor _exceptionsMonitor;

	public GlobalExceptionHandler(final boolean handleUncaughtExceptions) {
		if (handleUncaughtExceptions) {
			registerAsDefaultUncaughtExceptionHandler();
		}
	}

	@SuppressWarnings("ThrowableResultIgnored")
	private boolean addException(final Throwable throwable) {
		if (_exceptionsThrown.size() >= _exceptionsHistoryMaximum) {
			_exceptionsThrown.remove(0);
		}
		return _exceptionsThrown.add(throwable);
	}

	private void catchThrowable(final Thread thread, final Throwable throwable) {
		_hadException = (_lastException = throwable) != null ? addException(throwable) : false;
		_lastExceptionTimestamp = _hadException ? System.currentTimeMillis() : -1;
		_lastExceptionThread = thread;
		if (assigned(_exceptionsMonitor)) {
			_exceptionsMonitor.onExceptionCatched(_lastExceptionThread, _lastException, _lastExceptionTimestamp);
		}
	}

	public int getExceptionsHistoryMaximum() {
		return _exceptionsHistoryMaximum;
	}

	public void setExceptionsHistoryMaximum(final int value) {
		_exceptionsHistoryMaximum = value;
	}

	private boolean setDefaultUncaughtExceptionHandler(final Thread.UncaughtExceptionHandler handler) {
		try {
			Thread.setDefaultUncaughtExceptionHandler(handler);
			return true;
		} catch (final Exception e) {
			catchThrowable(Thread.currentThread(), e);
			return false;
		}
	}

	/*
	* Registers as the default uncaught exception handler.
	*/
	public final boolean registerAsDefaultUncaughtExceptionHandler() {
		_lastUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
		return setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			@SuppressWarnings("CallToPrintStackTrace")
			public void uncaughtException(final Thread thread, final Throwable throwable) {
				catchThrowable(thread, throwable);
				if (_printUncaughtExceptionsStackTrace) {
					throwable.printStackTrace();
				}
			}
		});
	}

	/*
	* Unregisters as the default uncaught exception handler by registering again the backup of the previous handler (which commonly will be null).
	*/
	public final boolean unregisterAsDefaultUncaughtExceptionHandler() {
		return setDefaultUncaughtExceptionHandler(_lastUncaughtExceptionHandler);
	}

	/**
	 * Prevents the GEH from printing the stack trace of uncaught exceptions.
	 * 
	 * If you need this, use it via:
	 * <code>((ace.GlobalExceptionHandler) ace.Ace.GEH).disableUncaughtExceptionsStackTracePrinting()</code>
	 */
	public void disableUncaughtExceptionsStackTracePrinting() {
		_printUncaughtExceptionsStackTrace = false;
	}

	/*@Override*/ public final void forgetExceptionsThrown() {
		_exceptionsThrown.clear();
	}

	/*@Override*/ public final ArrayList<Throwable> getExceptionsThrown() {
		return _exceptionsThrown;
	}

	/*@Override*/ public final boolean hadException() {
		return _hadException;
	}

	/*@Override*/ public final void forgetLastException() {
		_exceptionsThrown.remove(_lastException);
		_lastException = null;
		_hadException = false;
	}

	/*@Override*/ public final Throwable getLastException() {
		return _lastException;
	}

	/*@Override*/ public final long getLastExceptionTimestamp() {
		return _lastExceptionTimestamp;
	}

	/*@Override*/ public final Thread getLastExceptionThread() {
		return _lastExceptionThread;
	}

	/*@Override*/ public int getExceptionsCount() {
		return _exceptionsThrown.size();
	}

	/*@Override*/ public final void setLastException(final Throwable throwable) {
		catchThrowable(Thread.currentThread(), throwable);
	}

	/*@Override*/ public ExceptionsMonitor getExceptionsMonitor() {
		return _exceptionsMonitor;
	}

	/*@Override*/ public void setExceptionsMonitor(final ExceptionsMonitor exceptionsMonitor) {
		_exceptionsMonitor = exceptionsMonitor;
	}

}
