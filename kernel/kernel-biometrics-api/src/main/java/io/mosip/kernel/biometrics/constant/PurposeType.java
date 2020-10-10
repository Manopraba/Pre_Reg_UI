//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.05.13 at 03:22:53 PM IST 
//

package io.mosip.kernel.biometrics.constant;

public enum PurposeType {

	
	VERIFY("Verify"),
	IDENTIFY("Identify"),
	ENROLL("Enroll"),
	ENROLLVERIFY("EnrollVerify"),
	ENROLLIDENTIFY("EnrollIdentify"),
	AUDIT("Audit");

	private final String value;

	PurposeType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static PurposeType fromValue(String v) {
		for (PurposeType c : PurposeType.values()) {
			if (c.value.equalsIgnoreCase(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}

}