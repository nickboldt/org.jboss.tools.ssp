/******************************************************************************* 
 * Copyright (c) 2013 Red Hat, Inc. 
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

public class ServerBeanTypeWildfly80 extends JBossServerBeanType {
	public ServerBeanTypeWildfly80() {
		super(ID_WILDFLY, NAME_WILDFLY, AS7_MODULE_LAYERED_SERVER_MAIN);
	}
	
	protected String getServerTypeBaseName() {
		return getId();
	}
	
	public boolean isServerRoot(File location) {
		return JBossManifestUtility.scanManifestPropFromJBossModules(
				new File[]{new File(location, MODULES)}, 
				"org.jboss.as.server", null, JBAS7_RELEASE_VERSION, "8.");
	}
	public String getServerAdapterTypeId(String version) {	
		// Just return adapter type wf8 until we discover incompatibility. 
		return IServerConstants.SERVER_WILDFLY_80;
	}
}
