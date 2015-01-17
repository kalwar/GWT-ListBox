package com.example.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.OptionElement;
import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class Home implements EntryPoint {
	final ListBox listBox1 = new ListBox();

	TextBox txb = new TextBox();
	Label lbl = new Label();

	@Override
	public void onModuleLoad() {

		// Make a new list box, adding a few items to it.
		final ListBox lb = new ListBox() {
			@Override
			public void setSelectedIndex(int index) {
				super.setSelectedIndex(index);
				onChangeBody(this);
			}
		};

		lb.addItem("This is listbox item 1");
		lb.addItem("This is listbox item 2");
		lb.addItem("This is listbox item 3");
		lb.addItem("This is listbox item 4");
		lb.addItem("This is listbox item 5");

		// Create mouse down event, only works in Chrome (for now)
		lb.getElement().dispatchEvent(
				Document.get().createMouseDownEvent(0, 0, 0, 0, 0, false,
						false, false, false, Event.BUTTON_LEFT));

		// Add a change handler when item is clicked
		lb.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				onChangeBody(lb);
				int selectedIndex = lb.getSelectedIndex();
				GWT.log("Something got selected: " + lb.getValue(selectedIndex));
				lbl.setText(lb.getValue(selectedIndex));
				// txb.setValue(lb.getValue(selectedIndex)); You can also
				// display text in a when uncheck this comment textbox
			}
		});

		// Make enough room for all five items (setting this value to 1 turns it
		// into a drop-down list).
		lb.setVisibleItemCount(1);

		lb.setFocus(true); // This enables keyboard press down or up button in
							// focus

		// These will lets you select specific elements from a textbox
		// and iterate through each of those elements and display the items as a
		// tooltips
		SelectElement selectElement = SelectElement.as(lb.getElement());
		NodeList<OptionElement> options = selectElement.getOptions();
		for (int i = 0; i < options.getLength(); i++) {
			options.getItem(i).setTitle("Tooltip text for item #" + i);
		}

		// Add it to the root panel.
		RootPanel.get().add(lb);
		RootPanel.get().add(lbl);
		// RootPanel.get().add(txb);
	}

	public void onChangeBody(ListBox lb) {
		// System.out.println(lb.getValue(lb.getSelectedIndex()));
	}

}
