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

public class ServerBeanTypeWildfly100Web extends JBossServerBeanType {
	public ServerBeanTypeWildfly100Web() {
		super(ID_WILDFLY_WEB, NAME_WILDFLY,
				AS7_MODULE_LAYERED_SERVER_MAIN);
	}
	
	protected String getServerTypeBaseName() {
		return getId();
	}
	

	@Override
	public String getFullVersion(File location, File systemFile) {
		return ServerBeanTypeWildfly90Web.getFullVersion(location, systemFile, "10.");
	}

	
	public boolean isServerRoot(File location) {
		return getFullVersion(location, null) != null;
	}
	
	public String getServerAdapterTypeId(String version) {	
		// Just return adapter type wf8 until we discover incompatibility. 
		return IServerConstants.SERVER_WILDFLY_100;
	}
}
