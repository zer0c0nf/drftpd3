/*
 * This file is part of DrFTPD, Distributed FTP Daemon.
 * 
 * DrFTPD is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * DrFTPD is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with DrFTPD; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package org.drftpd.sections;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Collections;
import java.util.Properties;

import net.sf.drftpd.master.config.FtpConfig;
import net.sf.drftpd.remotefile.LinkedRemoteFile;

/**
 * @author mog
 * @version $Id: PlainSection.java,v 1.1 2004/02/16 22:39:42 mog Exp $
 */
public class PlainSection implements Section {

	private SectionManager _mgr;
	private String _dir;
	private String _name;

	public PlainSection(SectionManager mgr, int i, Properties p) {
		this(mgr, FtpConfig.getProperty(p, i + ".name"), FtpConfig.getProperty(p, i + ".path"));
	}

	public PlainSection(SectionManager mgr, String name, String path) {
		_mgr = mgr;
		_name = name;
		_dir = path;
		getFile();
	}

	public LinkedRemoteFile getFile() {
		try {
			return _mgr.getConnectionManager().getRoot().lookupFile(_dir);
		} catch (FileNotFoundException e) {
			return _mgr.getConnectionManager().getRoot().createDirectories(_dir);
		}
	}

	public Collection getFiles() {
		return Collections.singletonList(getFile());
	}

	public String getName() {
		return _name;
	}

	public String getPath() {
		return _dir;
	}
}