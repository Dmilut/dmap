/**
 * 
 */
package ru.dz.vita2d.ui.controller;

import javafx.fxml.Initializable;
import javafx.scene.Node;

/**
 * @author dmilut created on Nov 2, 2016
 */

public interface Controller extends Initializable {

	Node getView();

	void setView(Node view);

	void refresh();

}