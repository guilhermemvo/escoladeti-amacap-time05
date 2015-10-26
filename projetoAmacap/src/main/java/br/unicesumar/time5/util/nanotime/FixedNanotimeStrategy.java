package br.unicesumar.time5.util.nanotime;

public class FixedNanotimeStrategy extends ManageableNanotimeStrategy {

	public FixedNanotimeStrategy(long initialNanotime) {
		super(initialNanotime);
	}

	@Override
	public Long nanotime() {
		return initialNanotime;
	}

}
