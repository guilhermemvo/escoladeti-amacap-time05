package br.unicesumar.time5.util.nanotime;

public abstract class ManageableNanotimeStrategy extends NanotimeStrategy {

	protected long initialNanotime;

	public ManageableNanotimeStrategy(long initialNanotime) {
		this.initialNanotime = initialNanotime;
	}

}
