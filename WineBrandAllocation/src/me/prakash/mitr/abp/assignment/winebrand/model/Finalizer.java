/**
 * 
 */
package me.prakash.mitr.abp.assignment.winebrand.model;

/**
 * @author Prakash Ayappan @ ShrewOn
 *
 *         2018
 */
public class Finalizer {

	private boolean state;

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public void setFalse() {
		this.state = false;
	}

	public void updateStateOnTrue(final boolean state) {

		if (state) {
			this.state = true;
		}

	}

}
