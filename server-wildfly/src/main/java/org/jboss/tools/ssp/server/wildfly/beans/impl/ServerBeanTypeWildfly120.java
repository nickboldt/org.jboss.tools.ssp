/******************************************************************************* 
 * Copyright (c) 2015 Red Hat, Inc. 
 * Distributed under license by Red Hat, Inc. All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 ******************************************************************************/ 
package org.jboss.tools.ssp.server.wildfly.beans.impl;

import java.io.File;

import org.jboss.tools.ssp.server.wildfly.impl.util.JBossManifestUtility;

public class ServerBeanTypeWildfly120 extends JBossServerBeanType {
	public ServerBeanTypeWildfly120() {
		super(ID_WILDFLY, NAME_WILDFLY, AS7_MODULE_LAYERED_SERVER_MAIN);
	}
	
	protected String getServerTypeBaseName() {
		return getId();
	}

	@Override
	public String getFullVersion(File location, File systemFile) {
		return ServerBeanTypeWildfly90.getFullVersion(location, systemFile, "12.");
	}

	
	public boolean isServerRoot(File location) {
		return getFullVersion(location, null) != null;
	}
	
	public String getServerAdapterTypeId(String version) {	
		return IServerConstants.SERVER_WILDFLY_120;
	}
}
