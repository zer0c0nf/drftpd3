package net.sf.drftpd.slave;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;

/**
 * @author mog
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public abstract class Connection implements Serializable {

	public abstract Socket connect() throws IOException;
	void setSockOpts(Socket sock) throws IOException {
		/*
		 * IPTOS_LOWCOST (0x02)
		 * IPTOS_RELIABILITY (0x04)
		 * IPTOS_THROUGHPUT (0x08)
		 * IPTOS_LOWDELAY (0x10)
		 */
		sock.setTrafficClass(0x08);
		sock.setSoTimeout(60000); // 1 minute timeout
	}
}
