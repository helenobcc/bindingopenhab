package org.openhab.binding.souliss.internal.network.typicals;

import java.net.DatagramSocket;
import java.util.logging.Logger;

import org.openhab.binding.souliss.internal.network.udp.SoulissCommGate;

public class SoulissGenericTypical {
	
	//*************************************
	//*************************************
	private int iSlot;
	private String sSoulissNodeIPAddress;
	private String sIPAddressOnLAN;
	//private String sSoulissNodeVNetAddress;
	private int iSoulissNodeID;
	private short sType;
	private float fState;
	private String sName;
	private boolean isUpdated=false;
	private String sNote;
	final Logger LOGGER = Logger.getLogger(Constants.LOGNAME);
	//*************************************
	//*************************************

	
	//*************************************
	//*************************************
	//GETTER & SETTER
	//*************************************
	//*************************************
	
	
	/**
	 * @return the iSlot
	 */
	public int getSlot() {
		return iSlot;
	}
	/**
	 * @param iSlot the iSlot to set
	 */
	public void setSlot(int iSlot) {
		this.iSlot = iSlot;
	}
	/**
	 * @return the sSoulissNodeVNetAddress
	 */
//	public String getSoulissNodeVNetAddress() {
//		return sSoulissNodeVNetAddress;
//	}
	/**
	 * @param sSoulissNodeVNetAddress the sSoulissNodeVNetAddress to set
	 */
//	public void setSoulissNodeVNetAddress(String sSoulissNodeVNetAddress) {
//		this.sSoulissNodeVNetAddress = sSoulissNodeVNetAddress;
//	}
	/**
	 * @return the sSoulissNode
	 */
	public String getSoulissNodeIPAddress() {
		return sSoulissNodeIPAddress;
	}
	/**
	 * @param sSoulissNode the sSoulissNode to set
	 */
	public void setSoulissNodeIPAddress(String sSoulissNodeIPAddress) {
		this.sSoulissNodeIPAddress = sSoulissNodeIPAddress;
	}
	
	public String getSoulissNodeIPAddressOnLAN() {
		return sIPAddressOnLAN;
	}
	public void setSoulissNodeIPAddressOnLAN(String sIPAddressOnLAN) {
		this.sIPAddressOnLAN = sIPAddressOnLAN;
	}
	/**
	 * @param SoulissNode the SoulissNodeID to get
	 */
	public int getSoulissNodeID() {
		return iSoulissNodeID;
	}
	
	/**
	 * @param SoulissNode the SoulissNodeID to set
	 */
	public void setSoulissNodeID(int setSoulissNodeID) {
		this.iSoulissNodeID = setSoulissNodeID;
	}
		
	/**
	 * @return the sType
	 */
	public short getType() {
		return sType;
	}
	/**
	 * @param soulissT11 the sType to set
	 */
	protected void setType(short soulissT11) {
		this.sType = soulissT11;
	}
	/**
	 * @return the iState
	 */
	public float getState() {
		return fState;
	}
	/**
	 * @param iState the iState to set
	 */
	public void setState(float iState) {
		LOGGER.finer("Update State. Name: "+ getName() +", Typ: " + getType() + ", Node: "+ getSoulissNodeID() + ", Slot: " + getSlot() + ". New State: "+ iState);
		this.fState = iState;
		setUpdatedTrue();
	}
	
	/**
	 * @return the nodeName
	 */
	public String getName() {
		return sName;
	}
	/**
	 * @param nodeName the nodeName to set
	 */
	public void setName(String nodeName) {
		this.sName = nodeName;
	}
	/**
	 * @return the isUpdated
	 */
	public boolean isUpdated() {
		return isUpdated;
	}
	/**
	 */
	public void resetUpdate() {
		isUpdated=false;
	}
	public void setUpdatedTrue() {
		this.isUpdated = true;
	}
	
	public String getNote() {
		return sNote;
	}
	public void setNote(String sNote) {
		this.sNote = sNote;
	}
	void CommandMulticast( short command){
		LOGGER.fine("Typ: " + getType() + ", Name: " + getName()  +" - CommandMulticast: " + command);
		SoulissCommGate.sendMULTICASTFORCEFrame(this.getSoulissNodeIPAddress(),  getSoulissNodeIPAddressOnLAN(), getType(), command );
	}
	
	public void sendDBStructFrame(DatagramSocket datagramSocket){
		LOGGER.fine("Typ: " + getType() + ", Name: " + getName()  +" - sendDBStructFrame ");
		SoulissCommGate.sendDBStructFrame(datagramSocket, getSoulissNodeIPAddress(), getSoulissNodeIPAddressOnLAN());
	}
	
	public void ping(DatagramSocket datagramSocket, short putIn_1, short punIn_2){
		LOGGER.fine("Typ: " + getType() + ", Name: " + getName()  +" - ping");
		SoulissCommGate.sendPing(datagramSocket, getSoulissNodeIPAddress(), getSoulissNodeIPAddressOnLAN(), putIn_1, punIn_2);
	}

	public org.openhab.core.types.State getOHState() {
		
		return null;
	}

	
}
