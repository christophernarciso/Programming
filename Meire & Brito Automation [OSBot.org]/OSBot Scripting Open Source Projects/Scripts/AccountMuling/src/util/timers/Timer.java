package util.timers;

public class Timer {
    private final long start;
    private final long period;
    private long end;

    public Timer(long period) {
        this.period = period;
        this.start = System.currentTimeMillis();
        this.end = (this.start + period);
    }

    private long getElapsed() {
        return System.currentTimeMillis() - this.start;
    }

    private long getRemaining() {
        if (isRunning()) {
            return this.end - System.currentTimeMillis();
        }
        return 0L;
    }

    public boolean isRunning() {
        return System.currentTimeMillis() < this.end;
    }

    public void reset() {
        this.end = (System.currentTimeMillis() + this.period);
    }

    public long setEndIn(long ms) {
        this.end = (System.currentTimeMillis() + ms);
        return this.end;
    }

    public String toElapsedString() {
        return Time.format(getElapsed());
    }

    public String toRemainingString() {
        return Time.format(getRemaining());
    }
}