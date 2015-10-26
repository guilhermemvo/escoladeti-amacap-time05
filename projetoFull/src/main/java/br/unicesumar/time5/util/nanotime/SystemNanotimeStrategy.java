package br.unicesumar.time5.util.nanotime;

public class SystemNanotimeStrategy extends NanotimeStrategy {

	@Override
	public Long nanotime() {
		return System.nanoTime();
	}

}
