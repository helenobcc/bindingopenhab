package org.openhab.binding.souliss.internal.network.typicals;

import java.net.DatagramSocket;
import java.util.logging.Logger;

import org.openhab.binding.souliss.internal.network.udp.SoulissCommGate;

public class RefreshDBSTRUCTThread extends Thread {

	int REFRESH_TIME;
	DatagramSocket socket=null;
	String SoulissNodeIPAddress="";
	String soulissNodeIPAddressOnLAN="";
	final static Logger LOGGER = Logger.getLogger(Constants.LOGNAME);

	public RefreshDBSTRUCTThread(DatagramSocket datagramsocket, String soulissNodeIPAddress, String soulissNodeIPAddressOnLAN, int iRefreshTime) {
		// TODO Auto-generated constructor stub
		REFRESH_TIME=iRefreshTime;
		this.socket=datagramsocket;
		this.SoulissNodeIPAddress=soulissNodeIPAddress;
		this.soulissNodeIPAddressOnLAN=soulissNodeIPAddressOnLAN;
		LOGGER.info("Avvio RefreshDBSTRUCTThread");
	}


	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		while (true) {
			try {
				LOGGER.fine("sendDBStructFrame");
				SoulissCommGate.sendDBStructFrame(socket, SoulissNodeIPAddress, soulissNodeIPAddressOnLAN);
				Thread.sleep(REFRESH_TIME);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				LOGGER.severe(e.getMessage());
			}
			super.run();



		}
	}

}
