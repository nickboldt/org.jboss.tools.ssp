/******************************************************************************* 
 * Copyright (c) 2012-2014 Red Hat, Inc. 
 * Distributed under license by Red Hat, Inc. All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 ******************************************************************************/ 

package org.jboss.tools.ssp.server.discovery.serverbeans;

import java.io.File;

import org.jboss.tools.ssp.api.beans.ServerBean;
import org.jboss.tools.ssp.server.model.ServerManagementModel;
import org.jboss.tools.ssp.server.spi.discovery.ServerBeanType;


/**
 * @author eskimo
 *
 */
public class ServerBeanLoader {
	private ServerBean bean = null;
	private ServerBeanType type = null;
	private File rootLocation = null;

	public ServerBeanLoader(File location) {
		rootLocation = location;
	}
	
	public ServerBean getServerBean() {
		if( bean == null )
			loadBeanInternal();
		return bean;
	}

	public ServerBeanType getServerBeanType() {
		if( bean == null )
			loadBeanInternal();
		return type;
	}

	
	private void loadBeanInternal() {
		this.type = loadTypeInternal(rootLocation);
		String version = type.getFullVersion(rootLocation);
		ServerBean server = new ServerBean(rootLocation.getPath(),type.getServerBeanName(rootLocation),
				type.getId(), type.getUnderlyingTypeId(rootLocation), version, getMajorMinorVersion(version), type.getServerAdapterTypeId(version));
		this.bean = server;
	}
	
	private ServerBeanType loadTypeInternal(File location) {
		ServerBeanType[] all = ServerManagementModel.getDefault()
				.getServerBeanTypeManager().getAllRegisteredTypes();
		for( int i = 0; i < all.length; i++ ) {
			if( all[i].isServerRoot(location))
				return all[i];
		}
		return new ServerBeanTypeUnknown();
		
	}

	public String getFullServerVersion() {
		if( bean == null )
			loadBeanInternal();
		return bean.getFullVersion();
	}
	
	/**
	 * Get a string representation of this bean's 
	 * server type. This will usually be equivalent to 
	 * getServerType().getId(),  but may be overridden 
	 * in some cases that require additional differentiation. 
	 * 
	 * @return an org.eclipse.wst.server.core.IServerType's type id
	 * @since 3.0 (actually 2.4.101)
	 */
	public String getUnderlyingTypeId() {
		if( bean == null )
			loadBeanInternal();
		return bean.getSpecificType();
	}
	
	
	/**
	 * Get a server type id corresponding to an org.eclipse.wst.server.core.IServerType
	 * that matches with this server bean's root location. 
	 * 
	 * @return an org.eclipse.wst.server.core.IServerType's type id
	 */
	public String getServerAdapterId() {
		if( bean == null )
			loadBeanInternal();
		return bean.getServerAdapterTypeId();
	}
	

	/**
	 * Turn a version string into a major.minor version string. 
	 * Example:
	 *    getMajorMinorVersion("4.1.3.Alpha3") -> "4.1"
	 *    
	 * @param version
	 * @return
	 */
	public static String getMajorMinorVersion(String version) {
		if(version==null) 
			return "";//$NON-NLS-1$

		int firstDot = version.indexOf(".");
		int secondDot = firstDot == -1 ? -1 : version.indexOf(".", firstDot + 1);
		if( secondDot != -1) {
			String currentVersion = version.substring(0, secondDot);
			return currentVersion;
		}
		if( firstDot != -1)
			// String only has one ".", and is assumed to be already in "x.y" form
			return version;
		return "";
	}
}
