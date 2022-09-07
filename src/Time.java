
public class Time {
	// get start time in nanosecond
	public static double time_started = System.nanoTime();
	public static double get_time() {
		return (System.nanoTime() - time_started) * 1E-9;
	}
}
