/**
 * 
 */
package ru.dz.vita2d.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.Node;

/**
 * @author dmilut created on Nov 2, 2016
 */

public abstract class AbstractController implements Controller {
	private Node view;
	protected ResourceBundle resourceBundle;

	public Node getView() {
		return view;
	}

	public void setView(Node view) {
		this.view = view;
	}

	@Override
	public void refresh() {
		// do nothing by default
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		this.resourceBundle = resourceBundle;
	}
}