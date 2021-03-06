//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.05.13 at 03:22:53 PM IST 
//

package io.mosip.kernel.core.cbeffutil.jaxbclasses;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.w3c.dom.Element;

import io.mosip.kernel.core.cbeffutil.common.Base64Adapter;

/**
 * <p>
 * Java class for BIRType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="BIRType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Version" type="{http://standards.iso.org/iso-iec/19785/-3/ed-2/}VersionType" minOccurs="0"/>
 *         &lt;element name="CBEFFVersion" type="{http://standards.iso.org/iso-iec/19785/-3/ed-2/}VersionType" minOccurs="0"/>
 *         &lt;any processContents='skip' namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="BIRInfo" type="{http://standards.iso.org/iso-iec/19785/-3/ed-2/}BIRInfoType"/>
 *         &lt;element name="BDBInfo" type="{http://standards.iso.org/iso-iec/19785/-3/ed-2/}BDBInfoType" minOccurs="0"/>
 *         &lt;element name="SBInfo" type="{http://standards.iso.org/iso-iec/19785/-3/ed-2/}SBInfoType" minOccurs="0"/>
 *         &lt;element name="BIR" type="{http://standards.iso.org/iso-iec/19785/-3/ed-2/}BIRType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="BDB" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="SB" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BIRType", propOrder = { "version", "cbeffVersion", "any", "birInfo", "bdbInfo", "sbInfo", "bir", "bdb",
		"sb" })
@XmlRootElement(name = "BIR")
public class BIRType {

	@XmlElement(name = "Version")
	protected VersionType version;
	@XmlElement(name = "CBEFFVersion")
	protected VersionType cbeffVersion;
	@XmlAnyElement
	protected List<JAXBElement<String>> any;
	@XmlElement(name = "BIRInfo", required = true)
	protected BIRInfoType birInfo;
	@XmlElement(name = "BDBInfo")
	protected BDBInfoType bdbInfo;
	@XmlElement(name = "SBInfo")
	protected SBInfoType sbInfo;
	@XmlElement(name = "BIR")
	protected List<BIRType> bir;
	@XmlElement(name = "BDB")
	@XmlJavaTypeAdapter(Base64Adapter.class)
	protected byte[] bdb;
	@XmlElement(name = "SB")
	protected byte[] sb;

	/**
	 * Gets the value of the version property.
	 * 
	 * @return possible object is {@link VersionType }
	 * 
	 */
	public VersionType getVersion() {
		return version;
	}

	/**
	 * @param bir the bir to set
	 */
	public void setBir(List<BIRType> bir) {
		this.bir = bir;
	}

	/**
	 * @param any the any to set
	 */
	public void setAny(List<JAXBElement<String>> any) {
		this.any = any;
	}

	/**
	 * Sets the value of the version property.
	 * 
	 * @param value allowed object is {@link VersionType }
	 * 
	 */
	public void setVersion(VersionType value) {
		this.version = value;
	}

	/**
	 * Gets the value of the cbeffVersion property.
	 * 
	 * @return possible object is {@link VersionType }
	 * 
	 */
	public VersionType getCBEFFVersion() {
		return cbeffVersion;
	}

	/**
	 * Sets the value of the cbeffVersion property.
	 * 
	 * @param value allowed object is {@link VersionType }
	 * 
	 */
	public void setCBEFFVersion(VersionType value) {
		this.cbeffVersion = value;
	}

	/**
	 * Gets the value of the any property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot.
	 * Therefore any modification you make to the returned list will be present
	 * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
	 * for the any property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getAny().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link Element }
	 * 
	 * 
	 */
	public List<JAXBElement<String>> getAny() {
		if (any == null) {
			any = new ArrayList<JAXBElement<String>>();
		}
		return this.any;
	}

	/**
	 * Gets the value of the birInfo property.
	 * 
	 * @return possible object is {@link BIRInfoType }
	 * 
	 */
	public BIRInfoType getBIRInfo() {
		return birInfo;
	}

	/**
	 * Sets the value of the birInfo property.
	 * 
	 * @param value allowed object is {@link BIRInfoType }
	 * 
	 */
	public void setBIRInfo(BIRInfoType value) {
		this.birInfo = value;
	}

	/**
	 * Gets the value of the bdbInfo property.
	 * 
	 * @return possible object is {@link BDBInfoType }
	 * 
	 */
	public BDBInfoType getBDBInfo() {
		return bdbInfo;
	}

	/**
	 * Sets the value of the bdbInfo property.
	 * 
	 * @param value allowed object is {@link BDBInfoType }
	 * 
	 */
	public void setBDBInfo(BDBInfoType value) {
		this.bdbInfo = value;
	}

	/**
	 * Gets the value of the sbInfo property.
	 * 
	 * @return possible object is {@link SBInfoType }
	 * 
	 */
	public SBInfoType getSBInfo() {
		return sbInfo;
	}

	/**
	 * Sets the value of the sbInfo property.
	 * 
	 * @param value allowed object is {@link SBInfoType }
	 * 
	 */
	public void setSBInfo(SBInfoType value) {
		this.sbInfo = value;
	}

	/**
	 * Gets the value of the bir property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot.
	 * Therefore any modification you make to the returned list will be present
	 * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
	 * for the bir property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getBIR().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link BIRType }
	 * 
	 * 
	 */
	public List<BIRType> getBIR() {
		if (bir == null) {
			bir = new ArrayList<BIRType>();
		}
		return this.bir;
	}

	/**
	 * Gets the value of the bdb property.
	 * 
	 * @return possible object is byte[]
	 */
	public byte[] getBDB() {
		return bdb;
	}

	/**
	 * Sets the value of the bdb property.
	 * 
	 * @param value allowed object is byte[]
	 */
	public void setBDB(byte[] value) {
		this.bdb = value;
	}

	/**
	 * Gets the value of the sb property.
	 * 
	 * @return possible object is byte[]
	 */
	public byte[] getSB() {
		return sb;
	}

	/**
	 * Sets the value of the sb property.
	 * 
	 * @param value allowed object is byte[]
	 */
	public void setSB(byte[] value) {
		this.sb = value;
	}

}
