/*******************************************************************************
 * Copyright (c) 2011 Subgraph.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Subgraph - initial API and implementation
 ******************************************************************************/
package com.subgraph.vega.ui.identity.identitywizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.subgraph.vega.api.model.identity.IAuthMethod;
import com.subgraph.vega.api.model.identity.IAuthMethodNtlm;
import com.subgraph.vega.ui.identity.Activator;

public class AuthMethodControlNtlm extends Composite implements IAuthMethodControl {
	private final WizardPage page;
	private IAuthMethodNtlm authMethod;
	private Text usernameText;
	private Text passwordText;
	private Text workstationText;
	private Text domainText;
	
	public AuthMethodControlNtlm(Composite parent, WizardPage page) {
		super(parent, SWT.NONE);
		setLayout(new GridLayout(2, false));
		this.page = page;
		authMethod = Activator.getDefault().getModel().getCurrentWorkspace().getIdentityModel().createAuthMethodNtlm();
		createControls();
	}
	
	@Override
	public Control getControl() {
		return this;
	}

	@Override
	public IAuthMethod getAuthMethod() {
		authMethod.setUsername(usernameText.getText());
		authMethod.setPassword(passwordText.getText());
		authMethod.setWorkstation(workstationText.getText());
		authMethod.setDomain(domainText.getText());
		return authMethod;
	}

	private void createControls() {
		Label label = new Label(this, SWT.NONE);
		label.setText("Username:");
		usernameText = new Text(this, SWT.BORDER);
		usernameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		usernameText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				page.setPageComplete(!usernameText.getText().trim().isEmpty());
			}
		});

		label = new Label(this, SWT.NONE);
		label.setText("Password:");
		passwordText = new Text(this, SWT.BORDER);
		passwordText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		label = new Label(this, SWT.NONE);
		label.setText("Workstation:");
		workstationText = new Text(this, SWT.BORDER);
		workstationText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		label = new Label(this, SWT.NONE);
		label.setText("Domain:");
		domainText = new Text(this, SWT.BORDER);
		domainText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	}
	
}
