/**
 * 
 */
package com.pigihi.entity;

/**
 * Enum containing the statuses a shop can have <br>
 * ADMIN_DISABLED - The shop is disabled by the admin. Only an admin can enable the shop back. <br>
 * USER_DISABLED - The shop is disabled by the shop itself. Can enable by re-verification of email and mobile. <br>
 * ENABLED - The shop is enabled after successful verification of email and mobile. <br>
 * DISABLED - The shop is created but not yet verified email or mobile. <br>
 * 
 * @author Ashish Sam T George
 *
 */
public enum StatusEnum {
	
	ADMIN_DISABLED(760),
	USER_DISABLED(761),
	ENABLED(779),
	DISABLED(766);
	
	public int shopStatusCode;
	
	StatusEnum(int shopStatusCode) {
		this.shopStatusCode = shopStatusCode;
	}

}
